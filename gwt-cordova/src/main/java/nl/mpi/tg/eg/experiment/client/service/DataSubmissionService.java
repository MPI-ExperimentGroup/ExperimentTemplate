/*
 * Copyright (C) 2015 
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
package nl.mpi.tg.eg.experiment.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.shared.DateTimeFormat;
import java.util.Date;
import java.util.logging.Logger;
import nl.mpi.tg.eg.experiment.client.Messages;
import nl.mpi.tg.eg.experiment.client.model.UserId;

/**
 * @since Jul 2, 2015 5:17:27 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class DataSubmissionService {

    private static final Logger logger = Logger.getLogger(HighScoreService.class.getName());
    final private ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);
    protected final Messages messages = GWT.create(Messages.class);
    
    private final LocalStorage localStorage;

    public DataSubmissionService(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    public void submitScreenChange(UserId userId, String applicationState) {
        // todo: sumbit this to http://localhost:8080/frinex/addscreenview

        final DateTimeFormat format = DateTimeFormat.getFormat(messages.jsonDateFormat());

        localStorage.addStoredScreenData(userId, "{\"viewDate\" :\"" + format.format(new Date()) + "\",\n"
                + "\"experimentName\": \"experiment name\",\n"
                + "\"screenName\": \"" + applicationState + "\" ");
        final String storedScreenData = localStorage.getStoredScreenData(userId);

        // todo: optionally include the analytics call also
        trackView(applicationState);
    }

    private static native void trackView(String applicationState) /*-{
     if($wnd.analytics) $wnd.analytics.trackView(applicationState);
     }-*/;

    private static native void trackEvent(String applicationState, String label, String value) /*-{
     if($wnd.analytics) $wnd.analytics.trackEvent(applicationState, "view", label, value);
     }-*/;

}
