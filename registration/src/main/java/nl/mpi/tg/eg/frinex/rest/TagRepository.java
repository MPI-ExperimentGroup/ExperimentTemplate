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
import nl.mpi.tg.eg.frinex.model.AssignedValue;
import nl.mpi.tg.eg.frinex.model.TagData;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "select min(submitDate) as firstAccess, max(submitDate) as lastAccess from TagData group by userId order by firstAccess asc")
    Date[][] findFirstAndLastSessionAccess();
    
    @Query(value = "select min(submitDate) from TagData where userId = :userId")
    Date findFirstSessionAccess(@Param("userId") String userId);
    
    @Query(value = "select max(submitDate) from TagData where userId = :userId")
    Date findLastSessionAccess(@Param("userId") String userId);

//    AssignedValue(int assignedCount, int completedCount, Date lastChange, String value)
//    assignedValue
//    completedValue
//    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
//    @Query(value = "select new AssignedValue(min(submitDate)) from TagData group by to_char(submitDate,'YYYY-MM-DD')")
//    AssignedValue findAssignedValues(String[] valueOptions);
          
    @Query("select new AssignedValue(1, 1, max(submitDate), tagValue) from TagData group by TagValue where TagValue in :valueOptions and eventTag = :eventTag")
    List<AssignedValue> findAssignedValues(@Param("eventTag") String eventTag, @Param("valueOptions") Set<String> valueOptions);
    
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
