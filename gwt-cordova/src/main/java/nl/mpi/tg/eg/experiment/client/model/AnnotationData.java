/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experiment.client.model;

/**
 * @since Jan 31, 2014 10:04 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AnnotationData {

    private double inTime = 0;
    private double outTime = 0;
    private String annotationHtml = "";
    private Stimulus stimulus;

    public AnnotationData() {
    }

    public AnnotationData(double inTime, double outTime, String annotationHtml, Stimulus stimulus) {
        this.inTime = inTime;
        this.outTime = outTime;
        this.annotationHtml = annotationHtml;
        this.stimulus = stimulus;
    }

    public double getInTime() {
        return inTime;
    }

    public void setInTime(double inTime) {
        this.inTime = inTime;
    }

    public double getOutTime() {
        return outTime;
    }

    public void setOutTime(double outTime) {
        this.outTime = outTime;
    }

    public String getAnnotationHtml() {
        return annotationHtml;
    }

    public void setAnnotationHtml(String annotationHtml) {
        this.annotationHtml = annotationHtml;
    }

    public Stimulus getStimulus() {
        return stimulus;
    }

    public boolean intersectsTime(final double currentTime) {
        return (currentTime >= inTime && currentTime <= outTime);
    }
}
