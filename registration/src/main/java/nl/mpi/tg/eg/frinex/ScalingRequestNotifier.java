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

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Value;

/**
 * @since 27 Oct 2025 10:32 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class ScalingRequestNotifier {
    @Value("${nl.mpi.tg.eg.frinex.requestScalingUrl:#{null}}")
    protected static String requestScalingUrl;
    @Value("${nl.mpi.tg.eg.frinex.serviceName:#{null}}")
    protected static String serviceName;

    private static final AtomicReference<Double> avgLatency = new AtomicReference<>(0.0);
    private static volatile long lastScaleTime = 0;

    public static void recordRequestTime(long durationMs) {
        if (requestScalingUrl != null) {
            final double alpha = 0.2;
            avgLatency.updateAndGet(avg -> (1 - alpha) * avg + alpha * durationMs);
            double avg = avgLatency.get();
            final long cooldownMs = 60000;
            if (System.currentTimeMillis() - lastScaleTime > cooldownMs) {
                if (avg > 800) {
                    requestScaling("up", avgMs);
                } else if (avg < 300) {
                    requestScaling("down", avgMs);
                }
            }
        }
    }

    private static void requestScaling(String status, double avgMs) {
        try {
            String url = requestScalingUrl + "?service=" + serviceName + "&status=" + status + "&avgMs=" + avgMs;
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            int responseCode = conn.getResponseCode();
            System.out.println("requestScaling: " + responseCode);
            conn.disconnect();
            lastScaleTime = System.currentTimeMillis();
        } catch (Exception e) {
            System.err.println("requestScaling failed: ");
            e.printStackTrace();
        }
    }
}
