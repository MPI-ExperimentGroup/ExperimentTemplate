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

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.QueryHint;
import nl.mpi.tg.eg.frinex.model.AudioData;
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
@RepositoryRestResource(collectionResourceRel = "audiodata", path = "audiodata")
public interface AudioDataRepository extends PagingAndSortingRepository<AudioData, Long>, AudioDataStreamer {

    @Override
    @RestResource(exported = false)
    public abstract <S extends AudioData> S save(S entity);

    @Override
    @RestResource(exported = false)
    public abstract void delete(AudioData entity);

    @Override
    @RestResource(exported = false)
    public void deleteAll(Iterable<? extends AudioData> arg0);

    @Override
    @RestResource(exported = false)
    public void deleteById(Long arg0);

    @Transactional
    @RestResource(exported = false)
    public void deleteByUserId(@Param("userId") String userId);

    public int countByUserId(@Param("userId") String userId);

    @Override
    @RestResource(exported = false)
    public <S extends AudioData> Iterable<S> saveAll(Iterable<S> arg0);

    @Override
    @RestResource(exported = false)
    public abstract void deleteAll();

    @Override
    @RestResource(exported = false)
    public void deleteAllById(Iterable<? extends Long> ids);
    
    @Query("SELECT p FROM AudioData p WHERE "
        + "(:userId IS NULL OR p.userId like :userId) AND "
        + "(:screenName IS NULL OR p.screenName like :screenName) AND "
        + "(:stimulusId IS NULL OR p.stimulusId like :stimulusId)")
    @Transactional
    Page<AudioData> findByUserIdLikeAndScreenNameLikeAndStimulusIdLike(Pageable pageable, 
    @Param("userId") String userId,
    @Param("screenName") String screenName,
    @Param("stimulusId") String stimulusId);

    @Transactional
    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    @Query(value = "select new AudioData(min(submitDate)) from AudioData group by to_char(submitDate,'YYYY-MM-DD')")
    public List<AudioData> findSubmitDateDistinctByGroupBySubmitDay();
    
    @Transactional
//    string list is not cachable and the query seems to be fairly quick anyway
    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    @Query(value = "select distinct to_char(submitDate,'YYYY-MM-DD') as resultString from AudioData order by resultString asc")
    public String[] findSubmitDateDistinctByOrderBySubmitDateAsc();

    @Transactional
    List<AudioData> findAllBySubmitDateBetween(Date submitDateStart, Date submitDateEnd);

    @Transactional
    public List<AudioData> findByUserIdOrderBySubmitDateAsc(@Param("userId") String userId);

    @Transactional
    public List<AudioData> findBySubmitDateOrderBySubmitDateAsc(@Param("submitDate") String userId);

    @Transactional
    public List<AudioData> findByShortLivedTokenAndUserId(@Param("shortLivedToken") UUID shortLivedToken, @Param("userId") String userId);
    
}
