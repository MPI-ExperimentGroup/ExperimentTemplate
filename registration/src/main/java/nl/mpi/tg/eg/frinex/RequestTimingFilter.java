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

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @since 27 Oct 2025 10:32 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Component
public class RequestTimingFilter implements Filter {

    @Value("${nl.mpi.tg.eg.frinex.requestScalingUrl:#{null}}")
    protected String requestScalingUrl;

    @Value("${nl.mpi.tg.eg.frinex.serviceName:#{null}}")
    protected String serviceName;

    private static final ExecutorService EXECUTOR =
            new ThreadPoolExecutor(
                    2,
                    2,
                    0L,
                    TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<>(1000),
                    new ThreadPoolExecutor.DiscardPolicy()
            );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("requestScalingFilter initialized");
        ScalingRequestNotifier.showSettings(requestScalingUrl, serviceName);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        long start = System.nanoTime();

        try {
            chain.doFilter(request, response);
        } finally {
            long durationMs = TimeUnit.NANOSECONDS
                    .toMillis(System.nanoTime() - start);

            EXECUTOR.execute(() ->
                    ScalingRequestNotifier.recordRequestTime(
                            durationMs,
                            requestScalingUrl,
                            serviceName
                    )
            );
        }
    }

    @Override
    public void destroy() {
        EXECUTOR.shutdown();
        System.out.println("requestScalingFilter destroyed");
    }
}
