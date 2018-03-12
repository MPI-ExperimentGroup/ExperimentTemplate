/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experimentdesigner.model.wizard;

/**
 * @since Mar 8, 2018 12:18:58 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardUtilStimuliData {

    protected String stimuliName;
    protected String instructions = null;
    protected String[] stimuliArray;
    protected String[] randomStimuliTags;

    public String getStimuliName() {
        return stimuliName;
    }

    public void setStimuliName(String stimuliName) {
        this.stimuliName = stimuliName;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String[] getStimuliArray() {
        return stimuliArray;
    }

    public void setStimuliArray(String[] stimuliArray) {
        this.stimuliArray = stimuliArray;
    }

    public String[] getRandomStimuliTags() {
        return randomStimuliTags;
    }

    public void setRandomStimuliTags(String[] randomStimuliTags) {
        this.randomStimuliTags = randomStimuliTags;
    }
}
