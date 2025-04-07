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
import javax.persistence.QueryHint;
import nl.mpi.tg.eg.frinex.model.ScreenData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @since Jul 2, 2015 3:02:49 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RepositoryRestResource(collectionResourceRel = "screenviews", path = "screenviews")
public interface ScreenDataRepository extends PagingAndSortingRepository<ScreenData, Long> {

    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    @Query("select count(distinct concat(userId, screenName, viewDate)) from ScreenData")
    long countAllDistinctRecords();

    @Query("select distinct new ScreenData(userId, screenName, viewDate) from ScreenData order by viewDate asc")
    List<ScreenData> findAllDistinctRecords();

    @Query("select distinct new ScreenData(userId, screenName, viewDate) from ScreenData where userId = :userId order by viewDate asc")
    List<ScreenData> findByUserIdOrderByViewDateAsc(@Param("userId") String userId);

    @Query("select distinct new ScreenData(userId, screenName, viewDate) from ScreenData where userId = :userId and screenName = :screenName order by viewDate asc")
    List<ScreenData> findByUserIdAndScreenName(@Param("userId") String userId, @Param("screenName") String screenName);

    ScreenData findTop1ByUserIdOrderBySubmitDateAsc(@Param("userId") String userId);
    
    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    ScreenData findTop1ByOrderBySubmitDateAsc();

    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    ScreenData findTop1ByOrderBySubmitDateDesc();
    
    @Query("SELECT count(p) FROM TagData p WHERE "
        + "(:userId IS NULL OR p.userId like :userId) AND "
        + "(:screenName IS NULL OR p.screenName like :screenName)")
    long countByUserIdLikeAndScreenNameLike(
        @Param("userId") String userId,
        @Param("screenName") String screenName);

    @Query("SELECT p FROM TagData p WHERE "
        + "(:userId IS NULL OR p.userId like :userId) AND "
        + "(:screenName IS NULL OR p.screenName like :screenName)")
    Page<ScreenData> findByUserIdLikeScreenNameLike(Pageable pageable, 
        @Param("userId") String userId,
        @Param("screenName") String screenName);

    @Override
    @RestResource(exported = false)
    public abstract <S extends ScreenData> S save(S entity);

    @Override
    @RestResource(exported = false)
    public abstract void delete(ScreenData entity);

    @Override
    @RestResource(exported = false)
    public void deleteAll(Iterable<? extends ScreenData> arg0);

    @Override
    @RestResource(exported = false)
    public void deleteById(Long arg0);

    @Override
    @RestResource(exported = false)
    public <S extends ScreenData> Iterable<S> saveAll(Iterable<S> arg0);

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
