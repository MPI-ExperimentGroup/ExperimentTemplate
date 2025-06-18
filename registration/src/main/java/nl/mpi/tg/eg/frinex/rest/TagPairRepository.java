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

import java.util.List;
import nl.mpi.tg.eg.frinex.model.TagPairData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @since Jul 21, 2015 4:42:51 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RepositoryRestResource(collectionResourceRel = "tagpairevents", path = "tagpairevents")
public interface TagPairRepository extends PagingAndSortingRepository<TagPairData, Long> {

//    List<TagPairData> findByUserId(@Param("userId") String userId);
//    List<TagPairData> findByEventTag(@Param("eventTag") String eventTag);
    @Query("select distinct new TagPairData(userId, screenName, dataChannel, eventTag, tagValue1, tagValue2, eventMs, tagDate) from TagPairData order by tagDate asc")
    List<TagPairData> findAllDistinctRecords();

//    // TODO: This is for testing how many copies might exist and this method can be removed later
//    @Query("select count(distinct(userId, screenName, dataChannel, eventTag, tagValue1, tagValue2, eventMs, tagDate)) from TagPairData")
//    List<TagPairData> countDistinctRecords();
//    
//    // TODO: This is for testing how many copies might exist and this method can be removed later
//    @Query("select count(id) from TagPairData")
//    List<TagPairData> countAllRecords();
    
    @Query("select distinct new TagPairData(userId, screenName, dataChannel, eventTag, tagValue1, tagValue2, eventMs, tagDate) from TagPairData where userId = :userId order by tagDate asc, eventTag desc")
    List<TagPairData> findByUserIdOrderByTagDateAsc(@Param("userId") String userId);

    @Query("select distinct new TagPairData(userId, screenName, dataChannel, eventTag, tagValue1, tagValue2, eventMs, tagDate) from TagPairData where userId = :userId and eventTag = :eventTag order by tagDate asc")
    List<TagPairData> findByUserIdAndEventTagOrderByTagDateAsc(@Param("userId") String userId, @Param("eventTag") String eventTag);

    @Query("select distinct new TagPairData(userId, screenName, dataChannel, eventTag, tagValue1, tagValue2, eventMs, tagDate) from TagPairData where userId = :userId and eventTag = :eventTag and tagValue1 = :tagValue1 order by tagDate asc")
    List<TagPairData> findByUserIdAndEventTagAndTagValue1OrderByTagDateAsc(@Param("userId") String userId, @Param("eventTag") String eventTag, @Param("tagValue1") String tagValue1);

    @Query("select distinct new TagPairData(userId, screenName, dataChannel, eventTag, tagValue1, tagValue2, eventMs, tagDate) from TagPairData where eventTag = :eventTag order by tagDate asc")
    List<TagPairData> findByEventTagOrderByTagDateAsc(@Param("eventTag") String eventTag);

    @Query("select distinct new TagPairData(userId, screenName, dataChannel, eventTag, tagValue1, tagValue2, eventMs, tagDate) from TagPairData where tagValue1 = :tagValue1 order by tagDate asc")
    List<TagPairData> findByTagValue1OrderByTagDateAsc(@Param("tagValue1") String tagValue1);

    Page<TagPairData> findBydataChannel(Pageable pageable, Integer dataChannel);

    @Query("SELECT distinct new TagPairData(p.userId, p.screenName, p.dataChannel, p.eventTag, p.tagValue1, p.tagValue2, p.eventMs, p.tagDate) FROM TagPairData p WHERE "
        + "(:userId IS NULL OR p.userId like :userId) AND "
        + "(:screenName IS NULL OR p.screenName like :screenName) AND "
        + "(:dataChannel IS NULL OR p.dataChannel = :dataChannel) AND "
        + "(:eventTag IS NULL OR p.eventTag like :eventTag) AND "
        + "(:tagValue1 IS NULL OR p.tagValue1 like :tagValue1) AND "
        + "(:tagValue2 IS NULL OR p.tagValue2 like :tagValue2)")
    Page<TagPairData> findByLike(Pageable pageable, 
            @Param("userId") String userId,
            @Param("screenName") String screenName,
            @Param("dataChannel") Integer dataChannel,
            @Param("eventTag") String eventTag,
            @Param("tagValue1") String tagValue1,
            @Param("tagValue2") String tagValue2);

    @Query("SELECT count(*) "
        + "FROM (SELECT distinct p.userId, p.screenName, p.dataChannel, p.eventTag, p.tagValue1, p.tagValue2, p.eventMs, p.tagDate FROM TagPairData p WHERE "
        + "(:userId IS NULL OR p.userId like :userId) AND "
        + "(:screenName IS NULL OR p.screenName like :screenName) AND "
        + "(:dataChannel IS NULL OR p.dataChannel = :dataChannel) AND "
        + "(:eventTag IS NULL OR p.eventTag like :eventTag) AND "
        + "(:tagValue1 IS NULL OR p.tagValue1 like :tagValue1) AND "
        + "(:tagValue2 IS NULL OR p.tagValue2 like :tagValue2)"
        + ") AS distinct_rows")
    long countByLike(
            @Param("userId") String userId,
            @Param("screenName") String screenName,
            @Param("dataChannel") Integer dataChannel,
            @Param("eventTag") String eventTag,
            @Param("tagValue1") String tagValue1,
            @Param("tagValue2") String tagValue2);

    @Override
    @RestResource(exported = false)
    public abstract <S extends TagPairData> S save(S entity);

    @Override
    @RestResource(exported = false)
    public abstract void delete(TagPairData entity);

    @Override
    @RestResource(exported = false)
    public void deleteAll(Iterable<? extends TagPairData> arg0);

    @Override
    @RestResource(exported = false)
    public void deleteById(Long arg0);

    @Override
    @RestResource(exported = false)
    public <S extends TagPairData> Iterable<S> saveAll(Iterable<S> arg0);

    @Override
    @RestResource(exported = false)
    public abstract void deleteAll();

    @Override
    @RestResource(exported = false)
    public void deleteAllById(Iterable<? extends Long> ids);

    @RestResource(exported = false)
    public void deleteByUserId(@Param("userId") String userId);

    public int countByUserId(@Param("userId") String userId);
}
