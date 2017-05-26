/*
 * Copyright (C) 2017 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.frinex.common;

import java.util.ArrayList;
import java.util.List;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since May 24, 2017 11:48:44 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public interface StimuliProvider {

    void getAll();

    Stimulus getCurrentStimulus();

    int getCurrentStimulusIndex();

    String getLoadedStimulusString();

    List<Stimulus> getMatchingStimuli(final String matchingRegex, final int maxStimulusCount);

    void getSdCardSubset(final ArrayList<String> directoryTagArray, final List<String[]> directoryList, final TimedStimulusListener simulusLoadedListener, final TimedStimulusListener simulusErrorListener, final int maxStimulusCount, final boolean randomise, final int repeatCount, final int repeatRandomWindow, final String storedStimulusList, final int currentStimuliIndex);

    Stimulus getStimuliFromString(final String stimuliString);

    void getSubset(final List<Stimulus.Tag> selectionTags, final boolean randomise, final int repeatCount, final int repeatRandomWindow, final String storedStimulusList, final int currentStimuliIndex);

    void getSubset(final List<Stimulus.Tag> selectionTags, final int maxStimulusCount, final boolean randomise, final int repeatCount, final int repeatRandomWindow, final String storedStimulusList, final int currentStimuliIndex);

    void getSubset(final List<Stimulus.Tag> selectionTags, final int maxStimulusCount, final boolean randomise, final int repeatCount, final int repeatRandomWindow, final String storedStimulusList, List<Stimulus> stimulusListCopy);

    void getSubset(final int maxWordUse, final String storedStimulusList, final int currentStimuliIndex, final List<Stimulus.Tag> speakerTags, final List<Stimulus.Tag> wordTags, final int maxSpeakerWordCount);

    void getSubset(final Stimulus.Tag similarity, final int maxWordUse, final List<Stimulus.Tag> wordTags, final String storedStimulusList, final int currentStimuliIndex);

    int getTotalStimuli();

    boolean hasNextStimulus();

    void loadStoredStimulusList(String storedStimulusList);

    void nextStimulus();

    void pushCurrentStimulusToEnd();

    void setCurrentStimuliIndex(int currentStimuliIndex);
}
