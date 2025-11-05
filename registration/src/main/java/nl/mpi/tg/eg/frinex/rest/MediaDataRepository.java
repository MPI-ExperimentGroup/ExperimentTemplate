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

import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.persistence.QueryHint;
import nl.mpi.tg.eg.frinex.model.MediaData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * @since Aug 13, 2018 4:34:41 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RepositoryRestResource(collectionResourceRel = "mediadata", path = "mediadata")
public interface MediaDataRepository extends PagingAndSortingRepository<MediaData, Long>, MediaDataStreamer {

    @Override
    @RestResource(exported = false)
    public abstract <S extends MediaData> S save(S entity);

    @Override
    @RestResource(exported = false)
    public abstract void delete(MediaData entity);

    @Override
    @RestResource(exported = false)
    public void deleteAll(Iterable<? extends MediaData> arg0);

    @Override
    @RestResource(exported = false)
    public void deleteById(Long arg0);

    @Transactional
    @RestResource(exported = false)
    public void deleteByUserId(@Param("userId") String userId);

    public int countByUserId(@Param("userId") String userId);

    @Override
    @RestResource(exported = false)
    public <S extends MediaData> Iterable<S> saveAll(Iterable<S> arg0);

    @Override
    @RestResource(exported = false)
    public abstract void deleteAll();

    @Override
    @RestResource(exported = false)
    public void deleteAllById(Iterable<? extends Long> ids);
    
    @Query("SELECT new MediaData(a.id, a.submitDate, a.experimentName, a.screenName, a.userId, a.stimulusId, a.recordingFormat, a.mediaUUID, a.downloadPermittedWindowMs, a.partNumber) FROM MediaData a WHERE "
        + "(:userId IS NULL OR a.userId like :userId) AND "
        + "(:screenName IS NULL OR a.screenName like :screenName) AND "
        + "(:stimulusId IS NULL OR a.stimulusId like :stimulusId) AND "
        + "(a.partNumber = 0 OR a.partNumber IS NULL)")
    @Transactional
    Page<MediaData> findByLike(Pageable pageable, 
    @Param("userId") String userId,
    @Param("screenName") String screenName,
    @Param("stimulusId") String stimulusId);

    @Transactional
    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    @Query(value = "select new MediaData(min(submitDate)) from MediaData group by to_char(submitDate,'YYYY-MM-DD')")
    public List<MediaData> findSubmitDateDistinctByGroupBySubmitDay();
    
    @Transactional
//    string list is not cachable and the query seems to be fairly quick anyway
    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    @Query(value = "select distinct to_char(submitDate,'YYYY-MM-DD') as resultString from MediaData order by resultString asc")
    public String[] findSubmitDateDistinctByOrderBySubmitDateAsc();

    @Transactional
//    List<MediaData> findAllBySubmitDateBetween(Date submitDateStart, Date submitDateEnd);
    @Query("SELECT new MediaData(a.id, a.submitDate, a.experimentName, a.screenName, a.userId, a.stimulusId, a.recordingFormat, a.mediaUUID, a.downloadPermittedWindowMs, a.partNumber) FROM MediaData a WHERE (a.partNumber = 0 OR a.partNumber IS NULL) AND a.submitDate BETWEEN :start AND :end")
    List<MediaData> findBySubmitDateBetween(@Param("start") Date start, @Param("end") Date end);

    @Query("SELECT new MediaData(a.id, a.submitDate, a.experimentName, a.screenName, a.userId, a.stimulusId, a.recordingFormat, a.mediaUUID, a.downloadPermittedWindowMs, a.partNumber) FROM MediaData a WHERE a.userId = :userId AND (a.partNumber = 0 OR a.partNumber IS NULL) ORDER BY a.submitDate ASC")
    @Transactional
    public List<MediaData> findByUserIdOrderBySubmitDateAsc(@Param("userId") String userId);

    long countByTimestampBetween(Instant from, Instant to);
    
//    @Transactional
//    public List<MediaData> findBySubmitDateOrderBySubmitDateAsc(@Param("submitDate") String userId);

    // @Transactional
    // @Query("SELECT new MediaData(a.id, a.submitDate, a.experimentName, a.screenName, a.userId, a.stimulusId, a.recordingFormat, a.mediaUUID, a.downloadPermittedWindowMs, a.partNumber) FROM MediaData a WHERE a.mediaUUID = :mediaUUID AND a.userId = :userId AND (a.partNumber = 0 OR a.partNumber IS NULL)")
    // public List<MediaData> findByMediaUuidTokenAndUserId(@Param("mediaUUID") UUID mediaUUID, @Param("userId") String userId);
    
//    @Query("SELECT p.dataBlob FROM MediaData p WHERE p.id = :id")
//    Stream<Byte> streamDataBlob(@Param("id") long id);

}
