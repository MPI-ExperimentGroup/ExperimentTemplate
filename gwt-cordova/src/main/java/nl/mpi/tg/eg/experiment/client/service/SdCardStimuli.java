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
import nl.mpi.tg.eg.experiment.client.model.Stimulus;

/**
 * @since Jan 8, 2016 11:31:32 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SdCardStimuli {

    private final List<Stimulus> stimulusArray;

    public SdCardStimuli(List<Stimulus> stimulusArray) {
        this.stimulusArray = stimulusArray;
    }

    public final void fillStimulusList() {
        scanSdCard();
    }

    public void insertStimulus(String stimulusPath) {
        final String stimulusId = stimulusPath;
        final String stimuliLabel = stimulusPath;
        final String stimuliCode = stimulusPath;
        final boolean isMp3 = false;
        final boolean isMp4 = false;
        final boolean isOgg = false;
        final boolean isJpg = true;
        // todo: insert a relevant tag and address enum limitiation
        stimulusArray.add(new Stimulus(stimulusId, new Stimulus.Tag[]{Stimulus.Tag.tag_image}, stimuliLabel, stimuliCode, 0, isMp3, isMp4, isOgg, isJpg));
    }

    public void errorAction(String errorCode, String errorMessage) {
    }

    protected native boolean scanSdCard() /*-{
        var sdCardStimuli = this;
        var readFileEntry = function (entry) {
            var dirReader = entry.createReader();
            dirReader.readEntries(
                function (entries) {
                    var currentIndex;
                    for (currentIndex = 0; currentIndex < entries.length; currentIndex++) {
                        console.log("currentEntry: " + entries[currentIndex].fullPath);
                        if (entries[currentIndex].isDirectory === true) {
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

        var mpi_stimuli = "MPI_STIMULI";
        var potentialDirectories = [
            $wnd.cordova.file.dataDirectory + mpi_stimuli,
            $wnd.cordova.file.externalRootDirectory + mpi_stimuli,
            $wnd.cordova.file.externalDataDirectory + mpi_stimuli
        ];
        var directoryIndex
        for (directoryIndex = 0; directoryIndex < potentialDirectories.length; directoryIndex++) {
            if (potentialDirectories[directoryIndex] === null || potentialDirectories[directoryIndex].length === 0) {
                continue;
            }
            console.log(potentialDirectories[directoryIndex]);
            console.log(typeof $wnd.resolveLocalFileSystemURL);
            $wnd.resolveLocalFileSystemURL(potentialDirectories[directoryIndex], readFileEntry, function (error) {
                console.log("resolveLocalFileSystemURL error: " + error.code);
                console.log("resolveLocalFileSystemURL error: " + error.message);
                sdCardStimuli.@nl.mpi.tg.eg.experiment.client.service.SdCardStimuli::errorAction(Ljava/lang/String;Ljava/lang/String;)(error.code, error.message);
            });
        }
     }-*/;
}
