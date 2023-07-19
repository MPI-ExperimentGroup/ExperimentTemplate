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
package nl.mpi.tg.eg.frinex.rest;

import java.util.List;
import javax.persistence.QueryHint;
import nl.mpi.tg.eg.frinex.model.StimulusResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @since Sep 26, 2018 4:26:17 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RepositoryRestResource(collectionResourceRel = "stimulusresponses", path = "stimulusresponses")
public interface StimulusResponseRepository extends PagingAndSortingRepository<StimulusResponse, Long> {

    Page<StimulusResponse> findBydataChannel(Pageable pageable, Integer dataChannel);

    @Query("select distinct new StimulusResponse(tagDate, experimentName, screenName, dataChannel, responseGroup, scoreGroup, stimulusId, response, isCorrect, userId, eventMs, gamesPlayed, totalScore, totalPotentialScore, currentScore, correctStreak, errorStreak, potentialScore, maxScore, maxErrors, maxCorrectStreak, maxErrorStreak, maxPotentialScore) from StimulusResponse where userId = :userId order by tagDate asc")
    List<StimulusResponse> findByUserIdDistinctOrderByTagDateAsc(@Param("userId") String userId);

    List<StimulusResponse> findByUserIdOrderByTagDateAsc(@Param("userId") String userId);

    List<StimulusResponse> findTop1ByUserIdOrderByTotalPotentialScoreDesc(@Param("userId") String userId);

    @Query("select distinct new StimulusResponse(tagDate, experimentName, screenName, dataChannel, responseGroup, scoreGroup, stimulusId, response, isCorrect, userId, eventMs, gamesPlayed, totalScore, totalPotentialScore, currentScore, correctStreak, errorStreak, potentialScore, maxScore, maxErrors, maxCorrectStreak, maxErrorStreak, maxPotentialScore) from StimulusResponse order by tagDate asc")
    List<StimulusResponse> findAllDistinctRecords();

    // TODO: these methods might need to include distinct concat(tagDate, userId, eventMs)
    Page<StimulusResponse> findByScreenNameLikeAndScoreGroupLikeAndResponseGroupLikeAndStimulusIdLikeAndResponseLike(Pageable pageable, 
        @Param("screenName") String screenName,
//            @Param("dataChannel") Integer dataChannel,
//            @Param("isCorrect") Boolean isCorrect,
        @Param("scoreGroup") String scoreGroup,
        @Param("responseGroup") String responseGroup,
        @Param("stimulusId") String stimulusId,
        @Param("response") String response);

    // TODO: these methods might need to include distinct concat(tagDate, userId, eventMs)
    long countByScreenNameLikeAndScoreGroupLikeAndResponseGroupLikeAndStimulusIdLikeAndResponseLike(
        @Param("screenName") String screenName,
//            @Param("dataChannel") Integer dataChannel,
//            @Param("isCorrect") Boolean isCorrect,
        @Param("scoreGroup") String scoreGroup,
        @Param("responseGroup") String responseGroup,
        @Param("stimulusId") String stimulusId,
        @Param("response") String response);

    // TODO: these methods might need to include distinct concat(tagDate, userId, eventMs)
    long countByScreenNameLikeAndScoreGroupLikeAndResponseGroupLikeAndStimulusIdLikeAndResponseLikeAndIsCorrectTrue(
        @Param("screenName") String screenName,
//            @Param("dataChannel") Integer dataChannel,
//            @Param("isCorrect") Boolean isCorrect,
        @Param("scoreGroup") String scoreGroup,
        @Param("responseGroup") String responseGroup,
        @Param("stimulusId") String stimulusId,
        @Param("response") String response);

    // TODO: these methods might need to include distinct concat(tagDate, userId, eventMs)
    long countByScreenNameLikeAndScoreGroupLikeAndResponseGroupLikeAndStimulusIdLikeAndResponseLikeAndIsCorrectNotTrue(
        @Param("screenName") String screenName,
//            @Param("dataChannel") Integer dataChannel,
//            @Param("isCorrect") Boolean isCorrect,
        @Param("scoreGroup") String scoreGroup,
        @Param("responseGroup") String responseGroup,
        @Param("stimulusId") String stimulusId,
        @Param("response") String response);

    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    @Query("select count(distinct concat(tagDate, userId, eventMs)) from StimulusResponse")
    long countDistinctRecords();
    
    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    @Query("select count(distinct concat(tagDate, userId, eventMs)) from StimulusResponse where userId = :userId")
    long countDistinctUserIdRecords(@Param("userId") String userId);

    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    @Query("select count(distinct concat(tagDate, userId, eventMs)) from StimulusResponse where response like :matchingLike")
    public long countByResponseLike(@Param("matchingLike") String matchingLike);    

    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    @Query("select count(distinct concat(tagDate, userId, eventMs)) from StimulusResponse where screenName like :matchingLike")
    public long countByScreenNameLike(@Param("matchingLike") String matchingLike);

    @Override
    @RestResource(exported = false)
    public abstract <S extends StimulusResponse> S save(S entity);

    @Override
    @RestResource(exported = false)
    public abstract void delete(StimulusResponse entity);

    @Override
    @RestResource(exported = false)
    public void deleteAll(Iterable<? extends StimulusResponse> arg0);

    @Override
    @RestResource(exported = false)
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @RestResource(exported = false)
    public void deleteById(Long arg0);

    @Override
    @RestResource(exported = false)
    public <S extends StimulusResponse> Iterable<S> saveAll(Iterable<S> arg0);

    @Override
    @RestResource(exported = false)
    public abstract void deleteAll();

    @RestResource(exported = false)
    public void deleteByUserId(@Param("userId") String userId);

    public int countByUserId(@Param("userId") String userId);
}
