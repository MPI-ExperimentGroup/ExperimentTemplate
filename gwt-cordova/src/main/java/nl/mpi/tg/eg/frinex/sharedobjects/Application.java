package nl.mpi.tg.eg.frinex.sharedobjects;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.event.EventListener;
import org.springframework.web.context.WebApplicationContext;

/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics, Nijmegen
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
/**
 * @since Nov 9, 2016 1:44:47 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    @Value("${nl.mpi.tg.eg.frinex.informReadyUrl}")
    protected String informReadyUrl;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Override
    protected WebApplicationContext run(SpringApplication application) {
        return super.run(application);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void informNginxProxy() {
        if (informReadyUrl != null) {
            try ( BufferedInputStream inStream = new BufferedInputStream(new URL(informReadyUrl).openStream())) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = inStream.read(dataBuffer, 0, 1024)) > 0) {
                    System.out.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                System.err.append("informNginxProxy failed: ");
                System.err.append(e.getMessage());
            }
        }
    }
}
