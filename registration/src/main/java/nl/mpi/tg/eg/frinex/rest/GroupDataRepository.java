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
package nl.mpi.tg.eg.frinex.rest;

import java.util.Date;
import java.util.List;
import jakarta.persistence.QueryHint;
import nl.mpi.tg.eg.frinex.model.GroupData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @since Nov 25, 2016 3:13:46 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RepositoryRestResource(collectionResourceRel = "groupdata", path = "groupdata")
public interface GroupDataRepository extends PagingAndSortingRepository<GroupData, Long> {

//    @Override
//    List<GroupData> findAll();

    public Page<GroupData> findByGroupUUID(@Param("groupUUID") String groupUUID, Pageable pageable);

    @Query("select distinct new GroupData(eventDate, submitDate, experimentName, groupUUID, groupName, screenName, messageRespondentId, allMemberCodes, groupCommunicationChannels, senderMemberCode, respondentMemberCode, stimulusId, stimulusIndex, responseStimulusId, stimulusOptionIds, messageString, eventMs) from GroupData order by eventDate asc")
    List<GroupData> findAllDistinctRecords();
    
    @Query("SELECT distinct new GroupData(p.eventDate, p.submitDate, p.experimentName, p.groupUUID, p.groupName, p.screenName, p.messageRespondentId, p.allMemberCodes, p.groupCommunicationChannels, p.senderMemberCode, p.respondentMemberCode, p.stimulusId, p.stimulusIndex, p.responseStimulusId, p.stimulusOptionIds, p.messageString, p.eventMs) FROM GroupData p WHERE "
        + "(:groupUUID IS NULL OR p.groupUUID like :groupUUID) AND "
        + "(:screenName IS NULL OR p.screenName like :screenName) AND "
        + "(:groupName IS NULL OR p.groupName = :groupName) AND "
        + "(:messageRespondentId IS NULL OR p.messageRespondentId like :messageRespondentId) AND "
        + "(:allMemberCodes IS NULL OR p.allMemberCodes like :allMemberCodes) AND "
        + "(:groupCommunicationChannels IS NULL OR p.groupCommunicationChannels like :groupCommunicationChannels) AND "
        + "(:senderMemberCode IS NULL OR p.senderMemberCode like :senderMemberCode) AND "
        + "(:respondentMemberCode IS NULL OR p.respondentMemberCode like :respondentMemberCode) AND "
        + "(:stimulusId IS NULL OR p.stimulusId like :stimulusId) AND "
        + "(:stimulusIndex IS NULL OR p.stimulusIndex like :stimulusIndex) AND "
        + "(:responseStimulusId IS NULL OR p.responseStimulusId like :responseStimulusId) AND "
        + "(:stimulusOptionIds IS NULL OR p.stimulusOptionIds like :stimulusOptionIds) AND "
        + "(:messageSenderId IS NULL OR p.messageSenderId like :messageSenderId) AND "
        + "(:messageString IS NULL OR p.messageString like :messageString)")
    Page<GroupData> findByLike(Pageable pageable, 
            @Param("groupUUID") String groupUUID,
            @Param("screenName") String screenName,
            @Param("groupName") String groupName,
            @Param("messageRespondentId") String messageRespondentId,
            @Param("allMemberCodes") String allMemberCodes,
            @Param("groupCommunicationChannels") String groupCommunicationChannels,
            @Param("senderMemberCode") String senderMemberCode,
            @Param("respondentMemberCode") String respondentMemberCode,
            @Param("stimulusId") String stimulusId,
            @Param("stimulusIndex") String stimulusIndex,
            @Param("responseStimulusId") String responseStimulusId,
            @Param("stimulusOptionIds") String stimulusOptionIds,
            @Param("messageSenderId") String messageSenderId,
            @Param("messageString") String messageString);

    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    long countBySubmitDateBetween(Date from, Date to);
    
    @RestResource(exported = false)
    public <S extends GroupData> S save(S entity);

    @RestResource(exported = false)
    public void delete(GroupData entity);

    @RestResource(exported = false)
    public void deleteAll(Iterable<? extends GroupData> arg0);

    @RestResource(exported = false)
    public void deleteById(Long arg0);

    @RestResource(exported = false)
    public <S extends GroupData> Iterable<S> saveAll(Iterable<S> arg0);

    @RestResource(exported = false)
    public void deleteAll();

    @RestResource(exported = false)
    public void deleteAllById(Iterable<? extends Long> ids);
}
