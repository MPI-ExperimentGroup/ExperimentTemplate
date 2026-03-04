/*
 * Copyright (C) 2025 Max Planck Institute for Psycholinguistics
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package nl.mpi.tg.eg.frinex;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Gauge;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @since 27 Oct 2025 10:32 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class ScalingRequestNotifier {

    private static final AtomicReference<Double> avgLatency = new AtomicReference<>(0.0);
    private static final AtomicLong totalRequests = new AtomicLong(0);
    private static final AtomicLong lastScaleTime = new AtomicLong(0);

    public static void showSettings(final String requestScalingUrl, final String serviceName, final String containerId) {
        System.out.println("requestScalingUrl: " + requestScalingUrl);
        System.out.println("serviceName: " + serviceName);
        System.out.println("containerId: " + containerId);
    }

    public static void recordRequestTime(long durationMs,
                                         final String requestScalingUrl,
                                         final String serviceName,
                                         final String containerId,
                                         MeterRegistry registry) {

        if (requestScalingUrl != null) {
            final double alpha = 0.2;
            totalRequests.incrementAndGet();
            avgLatency.updateAndGet(avg ->
                    (1 - alpha) * avg + alpha * durationMs);
            double avg = avgLatency.get();
            final long cooldownMs = 60000;
            long now = System.currentTimeMillis();
            long last = lastScaleTime.get();

            if (now - last > cooldownMs) {
                if (lastScaleTime.compareAndSet(last, now)) {
                    double jvmMemoryUsed = safeGauge(registry, "jvm.memory.used");
                    double jvmMemoryMax  = safeGauge(registry, "jvm.memory.max");
                    double cpuUsage      = safeGauge(registry, "system.cpu.usage");
                    double threadsBusy   = safeGauge(registry, "jetty.threads.busy");
                    if (threadsBusy == 0.0) threadsBusy = safeGauge(registry, "jvm.threads.live");
                    double dbActive  = safeGauge(registry, "hikaricp.connections.active");
                    double dbIdle    = safeGauge(registry, "hikaricp.connections.idle");
                    double dbPending = safeGauge(registry, "hikaricp.connections.pending");
                    double dbMax     = safeGauge(registry, "hikaricp.connections.max");

                    String status;
                    if (avg > 800) {
                        status = "up";
                    } else if (now - last > cooldownMs * 20) {
                        status = "ping";
                    } else {
                        status = "ok";
                    }

                    sendScalingRequest(status, avg,
                            jvmMemoryUsed, jvmMemoryMax, cpuUsage, threadsBusy,
                            dbActive, dbIdle, dbPending, dbMax,
                            requestScalingUrl, serviceName, containerId);
                }
            }
        }
    }

    private static double safeGauge(MeterRegistry registry, String name) {
        try {
            Gauge g = registry.find(name).gauge();
            return g != null ? g.value() : 0.0;
        } catch (Exception e) {
            return 0.0;
        }
    }

    private static void sendScalingRequest(String status,
                                           double avgMs,
                                           double jvmMemoryUsed,
                                           double jvmMemoryMax,
                                           double cpuUsage,
                                           double threadsBusy,
                                           double dbActive,
                                           double dbIdle,
                                           double dbPending,
                                           double dbMax,
                                           final String requestScalingUrl,
                                           final String serviceName,
                                           final String containerId) {

        String urlStr = requestScalingUrl
                + "?service=" + serviceName
                + "&container=" + containerId
                + "&status=" + status
                + "&avgMs=" + avgMs
                + "&total=" + totalRequests.get()
                + "&jvmMemoryUsed=" + jvmMemoryUsed
                + "&jvmMemoryMax=" + jvmMemoryMax
                + "&cpuUsage=" + cpuUsage
                + "&threadsBusy=" + threadsBusy
                + "&dbActive=" + dbActive
                + "&dbIdle=" + dbIdle
                + "&dbPending=" + dbPending
                + "&dbMax=" + dbMax;

        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(5000);

            try (BufferedInputStream inStream = new BufferedInputStream(conn.getInputStream())) {
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inStream.read(dataBuffer, 0, 1024)) > 0) {
                    System.out.write(dataBuffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            System.err.println("requestScaling failed: " + urlStr);
            System.err.println(e.getMessage());
        } finally {
            if (conn != null) conn.disconnect();
        }
    }
}
