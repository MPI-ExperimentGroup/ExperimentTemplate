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
package nl.mpi.tg.eg.frinex.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 * @since Sep 26, 2018 2:15:01 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
public class StimulusResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date tagDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date submitDate;
    private String experimentName;
    private String screenName;
    private Integer dataChannel;
    private String responseGroup;
    private String scoreGroup;
    private String stimulusId;
    @Column(length = 1024)
    private String response;
    private Boolean isCorrect;
    private String userId;
    private int eventMs;

    private int gamesPlayed;
    private int totalScore;
    private int totalPotentialScore;
    private int currentScore;
    private int correctStreak;
    private int errorStreak;
    private int potentialScore;
    private double maxScore;
    private int maxErrors;
    private int maxCorrectStreak;
    private int maxErrorStreak;
    private int maxPotentialScore;
    private EventTime[] eventTimes;
    
    public StimulusResponse() {
    }

    public StimulusResponse(Date tagDate, String experimentName, String screenName, Integer dataChannel, String responseGroup, String scoreGroup, String stimulusId, String response, Boolean isCorrect, String userId, int eventMs, int gamesPlayed, int totalScore, int totalPotentialScore, int currentScore, int correctStreak, int errorStreak, int potentialScore, double maxScore, int maxErrors, int maxCorrectStreak, int maxErrorStreak, int maxPotentialScore, EventTime[] eventTimes) {
        this.tagDate = tagDate;
        this.experimentName = experimentName;
        this.screenName = screenName;
        this.dataChannel = dataChannel;
        this.responseGroup = responseGroup;
        this.scoreGroup = scoreGroup;
        this.stimulusId = stimulusId;
        this.response = response;
        this.isCorrect = isCorrect;
        this.userId = userId;
        this.eventMs = eventMs;
        this.gamesPlayed = gamesPlayed;
        this.totalScore = totalScore;
        this.totalPotentialScore = totalPotentialScore;
        this.currentScore = currentScore;
        this.correctStreak = correctStreak;
        this.errorStreak = errorStreak;
        this.potentialScore = potentialScore;
        this.maxScore = maxScore;
        this.maxErrors = maxErrors;
        this.maxCorrectStreak = maxCorrectStreak;
        this.maxErrorStreak = maxErrorStreak;
        this.maxPotentialScore = maxPotentialScore;
        this.eventTimes = eventTimes;
    }

    public long getId() {
        return id;
    }

    public Date getTagDate() {
        return tagDate;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public String getScreenName() {
        return screenName;
    }

    public Integer getDataChannel() {
        return dataChannel;
    }

    public String getResponseGroup() {
        return responseGroup;
    }

    public String getScoreGroup() {
        return scoreGroup;
    }

    public String getStimulusId() {
        return stimulusId;
    }

    public String getResponse() {
        return response;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public String getUserId() {
        return userId;
    }

    public int getEventMs() {
        return eventMs;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getTotalPotentialScore() {
        return totalPotentialScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getCorrectStreak() {
        return correctStreak;
    }

    public int getErrorStreak() {
        return errorStreak;
    }

    public int getPotentialScore() {
        return potentialScore;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public int getMaxErrors() {
        return maxErrors;
    }

    public int getMaxCorrectStreak() {
        return maxCorrectStreak;
    }

    public int getMaxErrorStreak() {
        return maxErrorStreak;
    }

    public int getMaxPotentialScore() {
        return maxPotentialScore;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public EventTime[] getEventTimes() {
        return eventTimes;
    }

    public void setEventTimes(EventTime[] eventTimes) {
        this.eventTimes = eventTimes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.tagDate);
        hash = 47 * hash + Objects.hashCode(this.experimentName);
        hash = 47 * hash + Objects.hashCode(this.screenName);
        hash = 47 * hash + Objects.hashCode(this.dataChannel);
        hash = 47 * hash + Objects.hashCode(this.responseGroup);
        hash = 47 * hash + Objects.hashCode(this.scoreGroup);
        hash = 47 * hash + Objects.hashCode(this.stimulusId);
        hash = 47 * hash + Objects.hashCode(this.response);
        hash = 47 * hash + Objects.hashCode(this.isCorrect);
        hash = 47 * hash + Objects.hashCode(this.userId);
        hash = 47 * hash + this.eventMs;
        hash = 47 * hash + this.gamesPlayed;
        hash = 47 * hash + this.totalScore;
        hash = 47 * hash + this.totalPotentialScore;
        hash = 47 * hash + this.currentScore;
        hash = 47 * hash + this.correctStreak;
        hash = 47 * hash + this.errorStreak;
        hash = 47 * hash + this.potentialScore;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.maxScore) ^ (Double.doubleToLongBits(this.maxScore) >>> 32));
        hash = 47 * hash + this.maxErrors;
        hash = 47 * hash + this.maxCorrectStreak;
        hash = 47 * hash + this.maxErrorStreak;
        hash = 47 * hash + this.maxPotentialScore;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StimulusResponse other = (StimulusResponse) obj;
        if (this.eventMs != other.eventMs) {
            return false;
        }
        if (this.gamesPlayed != other.gamesPlayed) {
            return false;
        }
        if (this.totalScore != other.totalScore) {
            return false;
        }
        if (this.totalPotentialScore != other.totalPotentialScore) {
            return false;
        }
        if (this.currentScore != other.currentScore) {
            return false;
        }
        if (this.correctStreak != other.correctStreak) {
            return false;
        }
        if (this.errorStreak != other.errorStreak) {
            return false;
        }
        if (this.potentialScore != other.potentialScore) {
            return false;
        }
        if (Double.doubleToLongBits(this.maxScore) != Double.doubleToLongBits(other.maxScore)) {
            return false;
        }
        if (this.maxErrors != other.maxErrors) {
            return false;
        }
        if (this.maxCorrectStreak != other.maxCorrectStreak) {
            return false;
        }
        if (this.maxErrorStreak != other.maxErrorStreak) {
            return false;
        }
        if (this.maxPotentialScore != other.maxPotentialScore) {
            return false;
        }
        if (!Objects.equals(this.experimentName, other.experimentName)) {
            return false;
        }
        if (!Objects.equals(this.screenName, other.screenName)) {
            return false;
        }
        if (!Objects.equals(this.responseGroup, other.responseGroup)) {
            return false;
        }
        if (!Objects.equals(this.scoreGroup, other.scoreGroup)) {
            return false;
        }
        if (!Objects.equals(this.stimulusId, other.stimulusId)) {
            return false;
        }
        if (!Objects.equals(this.response, other.response)) {
            return false;
        }
        if (!Objects.equals(this.isCorrect, other.isCorrect)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.tagDate, other.tagDate)) {
            return false;
        }
        if (!Objects.equals(this.dataChannel, other.dataChannel)) {
            return false;
        }
        return true;
    }
}
