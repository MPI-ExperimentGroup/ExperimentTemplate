/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics
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
import java.util.List;
import nl.mpi.tg.eg.frinex.model.TimeStamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @since Jul 9, 2015 2:30:54 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RepositoryRestResource(collectionResourceRel = "timestamps", path = "timestamps")
public interface TimeStampRepository extends PagingAndSortingRepository<TimeStamp, Long> {

    @Query("select distinct new TimeStamp(userId, eventTag, eventMs, tagDate) from TimeStamp order by tagDate asc, eventMs asc")
    List<TimeStamp> findAllDistinctRecords();

    @Query("select distinct new TimeStamp(userId, eventTag, eventMs, tagDate) from TimeStamp where userId = :userId order by tagDate asc, eventMs asc")
    List<TimeStamp> findByUserIdOrderByTagDateAsc(@Param("userId") String userId);

    @Query("select distinct new TimeStamp(userId, eventTag, eventMs, tagDate) from TimeStamp where userId = :userId and eventTag = :eventTag order by tagDate asc, eventMs asc")
    List<TimeStamp> findByUserIdAndEventTagOrderByTagDateAsc(@Param("userId") String userId, @Param("eventTag") String eventTag);
    
    @Query("SELECT count(p) FROM TimeStamp p WHERE "
        + "(:userId IS NULL OR p.userId like :userId) AND "
        + "(:eventTag IS NULL OR p.eventTag like :eventTag)")
    long countByUserIdLikeAndEventTagLike(
        @Param("userId") String userId,
        @Param("eventTag") String eventTag);

    @Query("SELECT distinct new TimeStamp(p.userId, p.eventTag, p.eventMs, p.tagDate) FROM TimeStamp p WHERE "
        + "(:userId IS NULL OR p.userId like :userId) AND "
        + "(:eventTag IS NULL OR p.eventTag like :eventTag)")
    Page<TimeStamp> findByLike(Pageable pageable, 
        @Param("userId") String userId,
        @Param("eventTag") String eventTag);
    
    @Query(value = "SELECT * FROM time_stamp td "
            + "WHERE (td.event_ms, td.event_tag, td.user_id, td.tag_date) IN ("
            + "  SELECT event_ms, event_tag, user_id, tag_date "
            + "  FROM tag_data "
            + "  GROUP BY event_ms, event_tag, user_id, tag_date "
            + "  HAVING COUNT(*) > 1"
            + ")", nativeQuery = true)
    List<TimeStamp> findNonUniqueCombinations();
    
    @Query("select distinct eventTag from TimeStamp order by eventTag")
    List<String> findDistinctEventTag();

    @Override
    @RestResource(exported = false)
    public abstract <S extends TimeStamp> S save(S entity);

    @Override
    @RestResource(exported = false)
    public abstract void delete(TimeStamp entity);

    @Override
    @RestResource(exported = false)
    public void deleteAll(Iterable<? extends TimeStamp> arg0);

    @Override
    @RestResource(exported = false)
    public void deleteById(Long arg0);

    @Override
    @RestResource(exported = false)
    public <S extends TimeStamp> Iterable<S> saveAll(Iterable<S> arg0);

    @Override
    @RestResource(exported = false)
    public abstract void deleteAll();

    @Override
    @RestResource(exported = false)
    public void deleteAllById(Iterable<? extends Long> ids);

    @RestResource(exported = false)
    public void deleteByUserId(@Param("userId") String userId);

    public int countByUserId(@Param("userId") String userId);
    
    long countBySubmitDateBetween(Instant from, Instant to);
}
