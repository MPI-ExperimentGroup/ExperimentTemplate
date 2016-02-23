/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics
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
package nl.mpi.tg.eg.experimentdesigner.model;

/**
 * @since Feb 23, 2016 3:00:09 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class StimuliSubAction {

    final String percentOfPage;
    final String label;
    final String button;

    public StimuliSubAction(String percentOfPage, String label, String button) {
        this.percentOfPage = percentOfPage;
        this.label = label;
        this.button = button;
    }

    public String getPercentOfPage() {
        return percentOfPage;
    }

    public String getLabel() {
        return label;
    }

    public String getButton() {
        return button;
    }
    
}
