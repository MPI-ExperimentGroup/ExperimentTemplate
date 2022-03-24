/*
 * Copyright (C) 2020 Max Planck Institute for Psycholinguistics, Nijmegen
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import nl.mpi.tg.eg.frinex.model.Participant;
import nl.mpi.tg.eg.frinex.model.ScreenData;
import nl.mpi.tg.eg.frinex.model.StimulusResponse;
import nl.mpi.tg.eg.frinex.model.TagData;
import nl.mpi.tg.eg.frinex.model.TimeStamp;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

/**
 * @since 17/08/2020 14:29 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class preventValidationOnMissingValidationControllerTest {

    public preventValidationOnMissingValidationControllerTest() {
    }

    private preventValidationOnMissingValidationController getInstance(final List<Participant> list) {

        return new preventValidationOnMissingValidationController(new ScreenDataRepository() {
            @Override
            public void deleteAllById(Iterable<? extends Long> ids) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public int countByUserId(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public ScreenData findTop1ByOrderBySubmitDateAsc() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public ScreenData findTop1ByOrderBySubmitDateDesc() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<ScreenData> findAllDistinctRecords() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<ScreenData> findByUserIdOrderByViewDateAsc(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<ScreenData> findByUserIdAndScreenName(String userId, String screenName) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public <S extends ScreenData> S save(S entity) {
                return null;
            }

            @Override
            public void delete(ScreenData entity) {
            }

            @Override
            public void deleteAll(Iterable<? extends ScreenData> arg0) {
            }

            @Override
            public void deleteById(Long arg0) {
            }

            @Override
            public void deleteByUserId(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public <S extends ScreenData> Iterable<S> saveAll(Iterable<S> arg0) {
                return null;
            }

            @Override
            public void deleteAll() {
            }

            @Override
            public Iterable<ScreenData> findAll(Sort sort) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Page<ScreenData> findAll(Pageable pageable) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Optional<ScreenData> findById(Long id) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public boolean existsById(Long id) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Iterable<ScreenData> findAll() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Iterable<ScreenData> findAllById(Iterable<Long> ids) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public long count() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }
        }, new TimeStampRepository() {
            @Override
            public void deleteAllById(Iterable<? extends Long> ids) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public int countByUserId(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<TimeStamp> findAllDistinctRecords() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<TimeStamp> findByUserIdOrderByTagDateAsc(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<TimeStamp> findByUserIdAndEventTagOrderByTagDateAsc(String userId, String eventTag) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<String> findDistinctEventTag() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public <S extends TimeStamp> S save(S entity) {
                return null;
            }

            @Override
            public void delete(TimeStamp entity) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public void deleteAll(Iterable<? extends TimeStamp> arg0) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public void deleteById(Long arg0) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public <S extends TimeStamp> Iterable<S> saveAll(Iterable<S> arg0) {
                return null;
            }

            @Override
            public void deleteAll() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Iterable<TimeStamp> findAll(Sort sort) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Page<TimeStamp> findAll(Pageable pageable) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Optional<TimeStamp> findById(Long id) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public boolean existsById(Long id) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Iterable<TimeStamp> findAll() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public void deleteByUserId(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Iterable<TimeStamp> findAllById(Iterable<Long> ids) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public long count() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }
        }, new MockParticipantColumnsRepository() {
            @Override
            public void deleteAllById(Iterable<? extends Long> ids) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public int countByUserId(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Date[][] findFirstAndLastUsersAccess() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Participant findTop1ByOrderBySubmitDateAsc() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Participant findTop1ByOrderBySubmitDateDesc() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public long countDistinctUserId() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Page<Participant> findByStaleCopy(boolean staleCopy, Pageable pageable) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<Participant> findByUserId(String userId) {
                return list;
            }

            @Override
            public List<Participant> findByStaleCopyAndUserId(boolean staleCopy, String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<Participant> findAllByOrderBySubmitDateDesc() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<String> findDistinctUserIdByOrderBySubmitDateDesc() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public void setAsStaleByUserId(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public <S extends Participant> S save(S entity) {
                return null;
            }

            @Override
            public void delete(Participant entity) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public void deleteAll(Iterable<? extends Participant> arg0) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public void deleteById(Long arg0) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public <S extends Participant> Iterable<S> saveAll(Iterable<S> arg0) {
                return null;
            }

            @Override
            public void deleteAll() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Iterable<Participant> findAll(Sort sort) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Page<Participant> findAll(Pageable pageable) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Optional<Participant> findById(Long id) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public boolean existsById(Long id) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Iterable<Participant> findAll() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public void deleteByUserId(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Iterable<Participant> findAllById(Iterable<Long> ids) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public long count() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }
        }, new TagRepository() {
            @Override
            public long countDistinctDateByEventTag(String eventTag) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public void deleteAllById(Iterable<? extends Long> ids) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public int countByUserId(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Date[][] findFirstAndLastSessionAccess() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public long countDistinctTagValueByEventTag(String eventTag) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<TagData> findAllDistinctRecords() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<TagData> findDistinctUserIdEventTagTagValueEventMsTageDateByUserIdOrderByTagDateAsc(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<TagData> findByUserIdAndEventTagOrderByTagDateAsc(String userId, String eventTag) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public int countDistinctTagDateByUserIdAndTagValue(String userId, String tagValue) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public <S extends TagData> S save(S entity) {
                return null;
            }

            @Override
            public void delete(TagData entity) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public void deleteAll(Iterable<? extends TagData> arg0) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public void deleteById(Long arg0) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public <S extends TagData> Iterable<S> saveAll(Iterable<S> arg0) {
                return null;
            }

            @Override
            public void deleteAll() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Iterable<TagData> findAll(Sort sort) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Page<TagData> findAll(Pageable pageable) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Optional<TagData> findById(Long id) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public boolean existsById(Long id) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Iterable<TagData> findAll() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public void deleteByUserId(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Iterable<TagData> findAllById(Iterable<Long> ids) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public long count() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }
        }, new StimulusResponseRepository() {
            @Override
            public long countDistinctRecords() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public void deleteAllById(Iterable<? extends Long> ids) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public int countByUserId(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Page<StimulusResponse> findBydataChannel(Pageable pageable, Integer dataChannel) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public void deleteByUserId(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<StimulusResponse> findByUserIdDistinctOrderByTagDateAsc(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<StimulusResponse> findByUserIdOrderByTagDateAsc(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<StimulusResponse> findTop1ByUserIdOrderByTotalPotentialScoreDesc(String userId) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public List<StimulusResponse> findAllDistinctRecords() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public <S extends StimulusResponse> S save(S entity) {
                return null;
            }

            @Override
            public void delete(StimulusResponse entity) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public void deleteAll(Iterable<? extends StimulusResponse> arg0) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public void deleteById(Long arg0) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public <S extends StimulusResponse> Iterable<S> saveAll(Iterable<S> arg0) {
                return null;
            }

            @Override
            public void deleteAll() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Iterable<StimulusResponse> findAll(Sort sort) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Page<StimulusResponse> findAll(Pageable pageable) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Optional<StimulusResponse> findById(Long id) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public boolean existsById(Long id) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Iterable<StimulusResponse> findAll() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public Iterable<StimulusResponse> findAllById(Iterable<Long> ids) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }

            @Override
            public long count() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }
        });
    }

    /**
     * Test of validate method, of class
     * preventValidationOnMissingValidationController.
     */
    @Test
    public void testValidate() throws Exception {
        System.out.println("validate");
        String requestingUserId = "requestingUserId";
        String invitation_id = "invitation_id";
        String token = "";
        String applicationversion = "";
        String datalog = "";
        String acceptLang = "";
        String userAgent = "";
        HttpServletRequest request = null;
        final List<Participant> participantList = new ArrayList<>();
        participantList.add(new Participant(requestingUserId));

        preventValidationOnMissingValidationController instance = getInstance(participantList);
        ResponseEntity<String> result1 = instance.validate(requestingUserId, invitation_id, token, applicationversion, datalog, acceptLang, userAgent, request);
        assertEquals(200, result1.getStatusCodeValue());
        assertEquals("<200 OK OK,{\n"
                + "\"validation_error\":\"No record for this user could be found where the provided invitation_id matches to the stored validated_invitation_id. \"\n"
                + "},[]>", result1.toString());
        final Participant participant2 = new Participant(requestingUserId);
        participant2.setValidated_invitation_id(invitation_id);
        participantList.add(participant2);
        ResponseEntity<String> result2 = instance.validate(requestingUserId, invitation_id, token, applicationversion, datalog, acceptLang, userAgent, request);
        assertEquals(200, result2.getStatusCodeValue());
        assertEquals("<200 OK OK,{\n"
                + "\"information\": \"validated user data found but the field token does not match the latest validated record in the admin system\",\n"
                + "\"information\": \"validated user data found but the field session_id does not match the validation regex\",\n"
                + "\"information\": \"validated user data found but the field copepod_done does not match the validation regex\",\n"
                + "\"metadata\": {\n"
                + "\"validation_error\":\"The participants token does not match the last stored value. session id not found or invalid[0-6]. \",\n"
                + "\"session_validation_error\":\"This session has already been completed and cannot be restarted. \"\n"
                + "}\n"
                + "},[]>", result2.toString());
    }

}
