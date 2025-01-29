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

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import nl.mpi.tg.eg.frinex.model.DataSubmissionResult;
import nl.mpi.tg.eg.frinex.model.TagData;
import org.junit.Test;
import static org.junit.Assert.*;
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

    /**
     * Test of completeValue method, of class AssignedValueController.
     */
    @Test
    public void testCompleteValue() {
        System.out.println("completeValue");
        TagData completedTagData = new TagData("userId", "screenName", "eventTag", "tagValue", 0, new Date());
        AssignedValueController instance = new AssignedValueController();
        ResponseEntity<DataSubmissionResult> expResult = null;
        ResponseEntity<DataSubmissionResult> result = instance.completeValue(completedTagData);
        assertEquals(expResult, result);
    }

    /**
     * Test of assignValue method, of class AssignedValueController.
     */
    @Test
    public void testAssignValue() {
        System.out.println("assignValue");
        List<String> expectedValues = Arrays.asList(targetOptionsAnimal.split(","));
        TagData tagData = new TagData("userId", "screenName", "eventTag", targetOptionsAnimal, 0, new Date());
        AssignedValueController instance = new AssignedValueController();
        final int valuesLength = expectedValues.size();
        for (int item = 0; item < valuesLength; item++) {
            ResponseEntity<DataSubmissionResult> result = instance.assignValue(tagData);
            expectedValues.remove(result.getBody().message);
        }
        assertEquals(0, expectedValues.size());
    }
}
