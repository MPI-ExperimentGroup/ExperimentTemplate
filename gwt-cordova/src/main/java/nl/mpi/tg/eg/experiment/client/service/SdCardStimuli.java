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
package nl.mpi.tg.eg.experiment.client.service;

import java.util.List;
import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus;
import nl.mpi.tg.eg.experiment.client.model.SdCardStimulus;
import nl.mpi.tg.eg.experiment.client.model.Stimulus;

/**
 * @since Jan 8, 2016 11:31:32 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SdCardStimuli {

    static final String MPI_STIMULI = "MPI_STIMULI";
    private final List<Stimulus> stimulusArray;
    private final List<GeneratedStimulus.Tag> tagArray;

    public SdCardStimuli(List<Stimulus> stimulusArray, List<GeneratedStimulus.Tag> tagArray) {
        this.stimulusArray = stimulusArray;
        this.tagArray = tagArray;
    }

    public final void fillStimulusList() {
        for (GeneratedStimulus.Tag currentTag : tagArray) {
            scanSdCard(MPI_STIMULI, currentTag.name().replaceFirst("^tag_", ""));
        }
    }

    public void insertDirectory(String directoryPath) {
        System.out.println("directoryPath: " + directoryPath);
    }

    public void insertStimulus(String stimulusPath) {
        System.out.println("stimulusPath: " + stimulusPath);
        final String stimulusId = stimulusPath.substring(MPI_STIMULI.length() + 2, stimulusPath.length() - 4);
        final String suffix = stimulusPath.toLowerCase().substring(stimulusPath.length() - 4, stimulusPath.length());
        System.out.println("suffix: " + suffix);
        final String stimuliLabel = stimulusPath;
        final String stimuliCode = stimulusPath;
        final int pause = 0;
        final boolean isMp3 = ".mp3".equals(suffix);
        final boolean isMp4 = ".mp4".equals(suffix);
        final boolean isOgg = ".ogg".equals(suffix);
        final boolean isJpg = ".jpg".equals(suffix);
        // todo: insert a relevant tag and address enum limitiation
        stimulusArray.add(new SdCardStimulus(stimulusId, stimulusPath,
                //                /* tagArray */ new Stimulus.Tag[0]/* we dont set this with the tag array because each stimulus would only have one out of many applicable from the array */,
                stimuliLabel, stimuliCode, pause, isMp3, isMp4, isOgg, isJpg));
    }

    public void errorAction(String errorCode, String errorMessage) {
        System.out.println("errorAction: " + errorCode + " " + errorMessage);
    }

    protected native boolean scanSdCard(String stimuliDirectory, String cleanedTag) /*-{
        var sdCardStimuli = this;
        var readFileEntry = function (entry) {
            var dirReader = entry.createReader();
            dirReader.readEntries(
                function (entries) {
                    var currentIndex;
                    for (currentIndex = 0; currentIndex < entries.length; currentIndex++) {
                        console.log("currentEntry: " + entries[currentIndex].fullPath);
                        if (entries[currentIndex].isDirectory === true) {
                            sdCardStimuli.@nl.mpi.tg.eg.experiment.client.service.SdCardStimuli::insertDirectory(Ljava/lang/String;)(entries[currentIndex].fullPath);
                            readFileEntry(entries[currentIndex]);
                        } else {
                            sdCardStimuli.@nl.mpi.tg.eg.experiment.client.service.SdCardStimuli::insertStimulus(Ljava/lang/String;)(entries[currentIndex].fullPath);
                        }
                    }
                },
                function (error) {
                    console.log("readEntries error: " + error.code);
                    console.log("readEntries error: " + error.message);
                    sdCardStimuli.@nl.mpi.tg.eg.experiment.client.service.SdCardStimuli::errorAction(Ljava/lang/String;Ljava/lang/String;)(error.code, error.message);
                }
            );
        };

        var potentialDirectories = [
            $wnd.cordova.file.dataDirectory,
            $wnd.cordova.file.externalRootDirectory,
            $wnd.cordova.file.externalDataDirectory
        ];
        var directoryIndex;
        for (directoryIndex = 0; directoryIndex < potentialDirectories.length; directoryIndex++) {
            if (potentialDirectories[directoryIndex] === null || potentialDirectories[directoryIndex].length === 0) {
                continue;
            }
            console.log(potentialDirectories[directoryIndex] + stimuliDirectory + "/" + cleanedTag);
            console.log(typeof $wnd.resolveLocalFileSystemURL);
            $wnd.resolveLocalFileSystemURL(potentialDirectories[directoryIndex] + stimuliDirectory + "/" + cleanedTag, readFileEntry, function (error) {
                console.log("resolveLocalFileSystemURL error: " + error.code);
                console.log("resolveLocalFileSystemURL error: " + error.message);
                sdCardStimuli.@nl.mpi.tg.eg.experiment.client.service.SdCardStimuli::errorAction(Ljava/lang/String;Ljava/lang/String;)(error.code, error.message);
            });
        }
     }-*/;
}
