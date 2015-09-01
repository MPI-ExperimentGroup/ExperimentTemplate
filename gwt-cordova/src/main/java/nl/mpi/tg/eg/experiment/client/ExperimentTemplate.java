package nl.mpi.tg.eg.experiment.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ExperimentTemplate implements EntryPoint {

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        final RootPanel prerunMessage = RootPanel.get("prerunMessage");
        if (prerunMessage != null) {
            prerunMessage.getElement().removeFromParent();
        }
        final RootLayoutPanel widgetTag = RootLayoutPanel.get();
        widgetTag.getElement().setId("widgetTag");
        final AppController appController = new ApplicationController(widgetTag);
        appController.start();
    }
}
