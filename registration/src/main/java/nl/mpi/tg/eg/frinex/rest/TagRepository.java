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
import nl.mpi.tg.eg.frinex.model.TagData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @since Jul 21, 2015 4:42:51 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RepositoryRestResource(collectionResourceRel = "tagevents", path = "tagevents")
public interface TagRepository extends PagingAndSortingRepository<TagData, Long> {

    @Override
    public Page<TagData> findAll(Pageable pageable);

    @Query("select distinct new TagData(userId, eventTag, tagValue, eventMs, tagDate) from TagData order by tagDate asc")
    List<TagData> findAllDistinctRecords();

    TagData findById(@Param("id") long id);

    List<TagData> findByUserIdOrderByTagDateAsc(@Param("userId") String userId);

    List<TagData> findByUserIdAndEventTagOrderByTagDateAsc(@Param("userId") String userId, @Param("eventTag") String eventTag);

    int countByUserIdAndTagValue(@Param("userId") String userId, @Param("tagValue") String tagValue);
}
