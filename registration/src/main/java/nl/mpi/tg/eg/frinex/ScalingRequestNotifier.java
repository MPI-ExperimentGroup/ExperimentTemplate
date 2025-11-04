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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @since 27 Oct 2025 10:32 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class ScalingRequestNotifier {

    private static final AtomicReference<Double> avgLatency = new AtomicReference<>(0.0);
    private static final AtomicLong totalRequests = new AtomicLong(0);
    private static volatile long lastScaleTime = 0;

    public static void showSettings(final String requestScalingUrl, final String serviceName) {
        System.out.print("requestScaling requestScalingUrl: ");
        System.out.println(requestScalingUrl);
        System.out.print("requestScaling serviceName: ");
        System.out.println(serviceName);
    }

    public static void recordRequestTime(long durationMs, final String requestScalingUrl, final String serviceName) {
        if (requestScalingUrl != null) {
            final double alpha = 0.2;
            totalRequests.incrementAndGet();
            avgLatency.updateAndGet(avg -> (1 - alpha) * avg + alpha * durationMs);
            double avg = avgLatency.get();
            final long cooldownMs = 60000;
            if (System.currentTimeMillis() - lastScaleTime > cooldownMs) {
                if (avg > 800) {
                    requestScaling("up", avg, requestScalingUrl, serviceName);
//                } else if (avg < 300) {
//                    requestScaling("down", avg, requestScalingUrl, serviceName);
                } else if (System.currentTimeMillis() - lastScaleTime > cooldownMs * 20) {
                    requestScaling("ping", avg, requestScalingUrl, serviceName);
                }
            }
        }
    }

    private static void requestScaling(String status, double avgMs, final String requestScalingUrl, final String serviceName) {
        String url = requestScalingUrl + "?service=" + serviceName + "&status=" + status + "&avgMs=" + avgMs + "&total=" + totalRequests.get();
        try (BufferedInputStream inStream = new BufferedInputStream(new URL(url).openStream())) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = inStream.read(dataBuffer, 0, 1024)) > 0) {
                System.out.write(dataBuffer, 0, bytesRead);
            }
//            System.out.println("requestScaling done");
        } catch (IOException e) {
            System.err.println("requestScaling failed: ");
            System.err.println(url);
            System.err.println(e.getMessage());
        }
        lastScaleTime = System.currentTimeMillis();
    }
}
