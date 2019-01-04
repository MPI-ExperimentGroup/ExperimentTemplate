package nl.mpi.tg.eg.experiment.client.presenter.TestViAuStimulus;
            
            import com.google.gwt.core.client.GWT;
            import java.util.Arrays;
            import java.util.List;
            import nl.mpi.tg.eg.experiment.client.ServiceLocations;
            import nl.mpi.tg.eg.frinex.common.model.AbstractStimulus;
            import nl.mpi.tg.eg.frinex.common.model.Stimulus;

            public class ViAuStimulus extends AbstractStimulus {
            protected final ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);
        
     
            public enum Tag implements nl.mpi.tg.eg.frinex.common.model.Stimulus.Tag {

        tag_PracticeSimple, tag_TestrunSimple, tag_PracticeChoice, tag_Ster, tag_Cirkel, tag_TestrunChoice
            }

        
            
            public static final void fillStimulusList(List<Stimulus> stimulusArray) {
            stimulusArray.addAll(Arrays.asList(ViAuStimulusProvider.values));
            }
            
            public ViAuStimulus(String uniqueId, Tag[] tags, String label, String code, int pauseMs, String audioPath, String videoPath, String imagePath, String ratingLabels, String correctResponses, String ... parameters) {
            super(uniqueId, tags, label, code, pauseMs, audioPath, videoPath, imagePath, ratingLabels, correctResponses);
        
            }

            /*public TestViAuStimulus(String uniqueId, Tag[] tags, String label, String code, int pauseMs, String ratingLabels, String correctResponses) {
            super(uniqueId, tags, label, code, pauseMs, ratingLabels, correctResponses);
            }*/
            
            @Override
            public boolean isCorrect(String value) {
            return (value != null) ? value.matches(getCorrectResponses()) : false;
            }
            
            @Override
            public String getAudio() {
            return serviceLocations.staticFilesUrl() + super.getAudio();
            }

            @Override
            public String getImage() {
            return serviceLocations.staticFilesUrl() + super.getImage();
            }

            @Override
            public String getVideo() {
            return serviceLocations.staticFilesUrl() + super.getVideo();
            }            
                   
            }   
        
