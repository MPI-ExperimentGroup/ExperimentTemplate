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

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.QueryHint;
import nl.mpi.tg.eg.frinex.model.AssignedValue;
import nl.mpi.tg.eg.frinex.model.TagData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @since Jul 21, 2015 4:42:51 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RepositoryRestResource(collectionResourceRel = "tagevents", path = "tagevents")
public interface TagRepository extends PagingAndSortingRepository<TagData, Long> {

    @Query("select distinct new TagData(userId, screenName, eventTag, tagValue, eventMs, tagDate) from TagData order by tagDate asc")
    List<TagData> findAllDistinctRecords();

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select count(distinct tagValue) from TagData where eventTag = :eventTag")
    long countDistinctTagValueByEventTag(String eventTag);

    @Query("select count(distinct tagValue) from TagData where userId = :userId and eventTag = :eventTag")
    long countDistinctUserIdAndTagValueByEventTag(@Param("userId") String userId, String eventTag);

    @Query("select count(distinct tagDate) from TagData where eventTag = :eventTag")
    long countDistinctDateByEventTag(String eventTag);

    @Query("select count(distinct tagDate) from TagData where userId = :userId and eventTag = :eventTag")
    long countDistinctUserIdAndDateByEventTag(@Param("userId") String userId, String eventTag);

    @Query("select distinct new TagData(userId, screenName, eventTag, tagValue, eventMs, tagDate) from TagData where userId = :userId order by tagDate asc, eventTag desc")
    List<TagData> findDistinctUserIdEventTagTagValueEventMsTageDateByUserIdOrderByTagDateAsc(@Param("userId") String userId);

    @Query("select distinct new TagData(userId, screenName, eventTag, tagValue, eventMs, tagDate) from TagData where userId = :userId and eventTag = :eventTag order by tagDate asc")
    List<TagData> findByUserIdAndEventTagOrderByTagDateAsc(@Param("userId") String userId, @Param("eventTag") String eventTag);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query(value = "select min(submitDate) as firstAccess, max(submitDate) as lastAccess from TagData group by userId order by firstAccess asc")
    Date[][] findFirstAndLastSessionAccess();

    @Query(value = "select min(submitDate) from TagData where userId = :userId")
    Date findFirstSessionAccess(@Param("userId") String userId);

    @Query(value = "select max(submitDate) from TagData where userId = :userId")
    Date findLastSessionAccess(@Param("userId") String userId);

    @Query(value = "SELECT * FROM tag_data td "
            + "WHERE (td.event_ms, td.event_tag, td.tag_value, td.user_id, td.tag_date, td.screen_name) IN ("
            + "  SELECT event_ms, event_tag, tag_value, user_id, tag_date, screen_name "
            + "  FROM tag_data "
            + "  GROUP BY event_ms, event_tag, tag_value, user_id, tag_date, screen_name "
            + "  HAVING COUNT(*) > 1"
            + ")", nativeQuery = true)
    List<TagData> findNonUniqueCombinations();

    @Query("SELECT count(p) FROM TagData p WHERE "
            + "(:userId IS NULL OR p.userId like :userId) AND "
            + "(:screenName IS NULL OR p.screenName like :screenName) AND "
            + "(:tagValue IS NULL OR p.tagValue like :tagValue) AND "
            + "(:eventTag IS NULL OR p.eventTag like :eventTag)")
    long countByUserIdLikeAndScreenNameLikeAndEventTagLikeAndTagValueLike(
            @Param("userId") String userId,
            @Param("screenName") String screenName,
            @Param("eventTag") String eventTag,
            @Param("tagValue") String tagValue);

    @Query("SELECT distinct new TagData(p.userId, p.screenName, p.eventTag, p.tagValue, p.eventMs, p.tagDate) FROM TagData p WHERE "
            + "(:userId IS NULL OR p.userId like :userId) AND "
            + "(:screenName IS NULL OR p.screenName like :screenName) AND "
            + "(:tagValue IS NULL OR p.tagValue like :tagValue) AND "
            + "(:eventTag IS NULL OR p.eventTag like :eventTag)")
    Page<TagData> findByLike(Pageable pageable,
            @Param("userId") String userId,
            @Param("screenName") String screenName,
            @Param("eventTag") String eventTag,
            @Param("tagValue") String tagValue);

    @Query(value = "SELECT COUNT(*) FROM (SELECT DISTINCT user_id, screen_name, event_tag, tag_value, event_ms, tag_date FROM tag_data WHERE "
        + "(:userId IS NULL OR user_id LIKE :userId) AND "
        + "(:screenName IS NULL OR screen_name LIKE :screenName) AND "
        + "(:tagValue IS NULL OR tag_value LIKE :tagValue) AND "
        + "(:eventTag IS NULL OR event_tag LIKE :eventTag)) AS distinct_rows", 
       nativeQuery = true)
    long countByLike(
            @Param("userId") String userId,
            @Param("screenName") String screenName,
            @Param("eventTag") String eventTag,
            @Param("tagValue") String tagValue);

//    AssignedValue(int assignedCount, int completedCount, Date lastChange, String value)
//    assignedValue
//    completedValue
//    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
//    @Query(value = "select new AssignedValue(min(submitDate)) from TagData group by to_char(submitDate,'YYYY-MM-DD')")
//    AssignedValue findAssignedValues(String[] valueOptions);

//    @Query("select new nl.mpi.tg.eg.frinex.model.AssignedValue(count(tagValue), max(submitDate), tagValue) from TagData where TagValue in :valueOptions and eventTag = :eventTag group by TagValue")
//    List<AssignedValue> countAssignedValues(@Param("eventTag") String eventTag, @Param("valueOptions") Set<String> valueOptions);

List<TagData> findByEventTagAndTagValueInOrderByTagDateAsc(@Param("eventTag") String eventTag, @Param("tagValues") Set<String> tagValues);

    List<TagData> findFirstByUserIdAndEventTagInOrderByTagDateDesc(@Param("userId") String userId, @Param("eventTags") Set<String> eventTags);

    @Query("select new nl.mpi.tg.eg.frinex.model.AssignedValue(count(tagValue), max(submitDate), tagValue) from TagData where tagValue in :tagValues and eventTag = :eventTag and screenName = :screenName group by tagValue")
    List<AssignedValue> countByDistinctByEventTagAndScreenNameAndTagValueIn(@Param("eventTag") String eventTag, @Param("screenName") String screenName, @Param("tagValues") Set<String> tagValues);

    int countDistinctTagDateByUserIdAndTagValue(@Param("userId") String userId, @Param("tagValue") String tagValue);

    int countByUserId(@Param("userId") String userId);

    @Override
    @RestResource(exported = false)
    public abstract <S extends TagData> S save(S entity);

    @Override
    @RestResource(exported = false)
    public abstract void delete(TagData entity);

    @Override
    @RestResource(exported = false)
    public void deleteAll(Iterable<? extends TagData> arg0);

    @Override
    @RestResource(exported = false)
    public void deleteById(Long arg0);

    @Override
    @RestResource(exported = false)
    public <S extends TagData> Iterable<S> saveAll(Iterable<S> arg0);

    @Override
    @RestResource(exported = false)
    public abstract void deleteAll();

    @Override
    @RestResource(exported = false)
    public void deleteAllById(Iterable<? extends Long> ids);

    @RestResource(exported = false)
    public void deleteByUserId(@Param("userId") String userId);
}
