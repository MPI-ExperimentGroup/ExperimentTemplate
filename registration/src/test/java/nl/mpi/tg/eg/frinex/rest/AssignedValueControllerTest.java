/*
 * Copyright (C) 2025 Max Planck Institute for Psycholinguistics, Nijmegen
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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import nl.mpi.tg.eg.frinex.model.AssignedValue;
import nl.mpi.tg.eg.frinex.model.DataSubmissionResult;
import nl.mpi.tg.eg.frinex.model.TagData;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

/**
 * @since 29 January 2025 15:45 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AssignedValueControllerTest {

    final String targetOptionsAnimal = "Elephant,Tiger,Lion,Bear,Giraffe,Zebra,Kangaroo,Panda,Koala,Dolphin,Whale,Shark,Crocodile,Alligator,Hippopotamus,Rhinoceros,Cheetah,Leopard,Wolf,Fox,Deer,Moose,Bison,Gorilla,Chimpanzee,Orangutan,Sloth,Hedgehog,Rabbit,Squirrel,Raccoon,Skunk,Otter,Beaver,Bat,Owl,Eagle,Falcon,Parrot,Penguin,Swan,Crane,Flamingo,Ostrich,Peacock,Pigeon,Crow,Seagull,Turtle,Tortoise,Snake,Lizard,Frog,Salamander,Antelope,Buffalo,Camel,Horse,Donkey,Sheep";
    final String targetOptionsVerb = "Accept,Allow,Appreciate,Assist,Authorize,Beware,Celebrate,Cherish,Commend,Compliment,Concede,Confirm,Consider,Consult,Contribute,Defer,Deliver,Deny,Describe,Encourage,Engage,Enlighten,Entertain,Examine,Excuse,Explain,Express,Facilitate,Favor,Grant,Help,Honor,Identify,Imagine,Inquire,Inform,Inspire,Invite,Join,Mention,Observe,Offer,Permit,Polish,Pray,Promise,Provide,Recommend,Refine,Regard,Respect,Respond,Support,Thank,Understand,Uphold,Welcome,Wish,Advise,Encourage";

    public AssignedValueControllerTest() {
    }

    private AssignedValueController getInstance() {
        AssignedValueController instance = new AssignedValueController();
        ArrayList<TagData> mockRecords = new ArrayList<>();
        HashMap<String, Integer> countsMap = new HashMap<>();
        instance.tagRepository = new TagRepository() {
            @Override
            public long countByLike(String userId, String screenName, String eventTag, String tagValue) {
                throw new UnsupportedOperationException("Not required for this test class.");
            }
            
            @Override
            public List<TagData> findNonUniqueCombinations() {
                throw new UnsupportedOperationException("Not required for this test class.");
            }
            
            @Override
            public List<TagData> findAllDistinctRecords() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public long countDistinctTagValueByEventTag(String eventTag) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public long countDistinctUserIdAndTagValueByEventTag(String userId, String eventTag) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public long countDistinctDateByEventTag(String eventTag) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public long countDistinctUserIdAndDateByEventTag(String userId, String eventTag) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public List<TagData> findDistinctUserIdEventTagTagValueEventMsTageDateByUserIdOrderByTagDateAsc(String userId) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public List<TagData> findByUserIdAndEventTagOrderByTagDateAsc(String userId, String eventTag) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Date[][] findFirstAndLastSessionAccess() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Date findFirstSessionAccess(String userId) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Date findLastSessionAccess(String userId) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public long countByUserIdLikeAndScreenNameLikeAndEventTagLikeAndTagValueLike(String userId, String screenName, String eventTag, String tagValue) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Page<TagData> findByLike(Pageable pageable, String userId, String screenName, String eventTag, String tagValue) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public List<TagData> findByEventTagAndTagValueInOrderByTagDateAsc(String eventTag, Set<String> tagValues) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public List<TagData> findFirstByUserIdAndEventTagInOrderByTagDateDesc(String userId, Set<String> eventTags) {
                return mockRecords;
            }

            @Override
            public List<AssignedValue> countByDistinctByEventTagAndScreenNameAndTagValueIn(String eventTag, String screenName, Set<String> tagValues) {
                List<AssignedValue> mockAssignedRecords = new ArrayList();
                return mockAssignedRecords;
            }

            @Override
            public int countDistinctTagDateByUserIdAndTagValue(String userId, String tagValue) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public int countByUserId(String userId) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <S extends TagData> S save(S entity) {
                mockRecords.add(entity);
                return entity;
            }

            @Override
            public void delete(TagData entity) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void deleteAll(Iterable<? extends TagData> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void deleteById(Long arg0) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <S extends TagData> Iterable<S> saveAll(Iterable<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void deleteAll() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void deleteAllById(Iterable<? extends Long> ids) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void deleteByUserId(String userId) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Iterable<TagData> findAll(Sort sort) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Page<TagData> findAll(Pageable pageable) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Optional<TagData> findById(Long id) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public boolean existsById(Long id) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Iterable<TagData> findAll() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Iterable<TagData> findAllById(Iterable<Long> ids) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public long count() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        return instance;
    }

    /**
     * Test of completeValue method, of class AssignedValueController.
     */
    @Ignore
    @Test
    public void testCompleteValue() {
        System.out.println("completeValue");
        TagData completedTagData = new TagData("userId", "screenName", "completeValue", "tagValue", 0, new Date());
        AssignedValueController instance = getInstance();
        ResponseEntity<DataSubmissionResult> expResult = null;
        ResponseEntity<DataSubmissionResult> result = instance.completeValue(completedTagData);
        assertEquals(expResult, result);
    }

    /**
     * Test of assignValue method, of class AssignedValueController.
     */
    @Ignore
    @Test
    public void testAssignValue() {
        System.out.println("assignValue");
        List<String> expectedValues = Arrays.asList(targetOptionsAnimal.split(","));
        TagData tagData = new TagData("userId", "screenName", "assignValue", targetOptionsAnimal, 0, new Date());
        AssignedValueController instance = getInstance();
        final int valuesLength = expectedValues.size();
        for (int item = 0; item < valuesLength; item++) {
            ResponseEntity<DataSubmissionResult> result = instance.assignValue(tagData);
            expectedValues.remove(result.getBody().message);
        }
        assertEquals(0, expectedValues.size());
    }
}
