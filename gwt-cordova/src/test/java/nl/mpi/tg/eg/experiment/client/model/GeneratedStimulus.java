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
package nl.mpi.tg.eg.experiment.client.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.List;

/**
 * @since Dec 3, 2015 11:35:06 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class GeneratedStimulus implements Stimulus {

    private static final GeneratedStimulus[] values = new GeneratedStimulus[]{
        new GeneratedStimulus("d1e286", "url", new Tag[]{Tag.tag_number, Tag.tag_interesting}, "One", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e291", "url", new Tag[]{Tag.tag_number, Tag.tag_multiple_words, Tag.tag_interesting}, "Two", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e298", "url", new Tag[]{Tag.tag_FILLER_AUDIO}, "Three", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e301", "url", new Tag[]{Tag.tag_FILLER_AUDIO}, "Four", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e304", "url", new Tag[]{Tag.tag_NOISE_AUDIO}, "Five", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e307", "url", new Tag[]{Tag.tag_NOISE_AUDIO}, "Six", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e310", "url", new Tag[]{Tag.tag_sim}, "sim_rabbit", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e313", "url", new Tag[]{Tag.tag_sim}, "sim_cat", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e316", "url", new Tag[]{Tag.tag_sim}, "sim_muffin", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e319", "url", new Tag[]{Tag.tag_sim}, "sim_you", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e322", "url", new Tag[]{Tag.tag_mid}, "mid_rabbit", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e326", "url", new Tag[]{Tag.tag_mid}, "mid_cat", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e329", "url", new Tag[]{Tag.tag_mid}, "mid_muffin", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e332", "url", new Tag[]{Tag.tag_mid}, "mid_you", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e335", "url", new Tag[]{Tag.tag_diff}, "diff_rabbit", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e338", "url", new Tag[]{Tag.tag_diff}, "diff_cat", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e341", "url", new Tag[]{Tag.tag_diff}, "diff_muffin", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e344", "url", new Tag[]{Tag.tag_diff}, "diff_you", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e347", "url", new Tag[]{Tag.tag_noise}, "noise_rabbit", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e350", "url", new Tag[]{Tag.tag_noise}, "noise_cat", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e353", "url", new Tag[]{Tag.tag_noise}, "noise_muffin", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e356", "url", new Tag[]{Tag.tag_noise}, "noise_you", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e360", "url", new Tag[]{Tag.tag_termites, Tag.tag_Rocket}, "termites_Rocket_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e365", "url", new Tag[]{Tag.tag_termites, Tag.tag_Rocket}, "termites_Rocket_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e370", "url", new Tag[]{Tag.tag_termites, Tag.tag_Rocket}, "termites_Rocket_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e375", "url", new Tag[]{Tag.tag_termites, Tag.tag_Rocket}, "termites_Rocket_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e380", "url", new Tag[]{Tag.tag_termites, Tag.tag_Rocket}, "termites_Rocket_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e385", "url", new Tag[]{Tag.tag_termites, Tag.tag_Rocket}, "termites_Rocket_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e390", "url", new Tag[]{Tag.tag_Festival, Tag.tag_termites}, "termites_Festival_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e395", "url", new Tag[]{Tag.tag_Festival, Tag.tag_termites}, "termites_Festival_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e400", "url", new Tag[]{Tag.tag_Festival, Tag.tag_termites}, "termites_Festival_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e405", "url", new Tag[]{Tag.tag_Festival, Tag.tag_termites}, "termites_Festival_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e410", "url", new Tag[]{Tag.tag_Festival, Tag.tag_termites}, "termites_Festival_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e416", "url", new Tag[]{Tag.tag_Festival, Tag.tag_termites}, "termites_Festival_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e421", "url", new Tag[]{Tag.tag_termites, Tag.tag_Thai}, "termites_Thai_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e426", "url", new Tag[]{Tag.tag_termites, Tag.tag_Thai}, "termites_Thai_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e431", "url", new Tag[]{Tag.tag_termites, Tag.tag_Thai}, "termites_Thai_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e436", "url", new Tag[]{Tag.tag_termites, Tag.tag_Thai}, "termites_Thai_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e441", "url", new Tag[]{Tag.tag_termites, Tag.tag_Thai}, "termites_Thai_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e446", "url", new Tag[]{Tag.tag_termites, Tag.tag_Thai}, "termites_Thai_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e451", "url", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_termites}, "termites_ประเพณีบุญบั้งไฟ_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e456", "url", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_termites}, "termites_ประเพณีบุญบั้งไฟ_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e461", "url", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_termites}, "termites_ประเพณีบุญบั้งไฟ_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e466", "url", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_termites}, "termites_ประเพณีบุญบั้งไฟ_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e472", "url", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_termites}, "termites_ประเพณีบุญบั้งไฟ_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e477", "url", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_termites}, "termites_ประเพณีบุญบั้งไฟ_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e482", "url", new Tag[]{Tag.tag_Lao, Tag.tag_termites}, "termites_Lao_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e487", "url", new Tag[]{Tag.tag_Lao, Tag.tag_termites}, "termites_Lao_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e492", "url", new Tag[]{Tag.tag_Lao, Tag.tag_termites}, "termites_Lao_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e497", "url", new Tag[]{Tag.tag_Lao, Tag.tag_termites}, "termites_Lao_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e502", "url", new Tag[]{Tag.tag_Lao, Tag.tag_termites}, "termites_Lao_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e507", "url", new Tag[]{Tag.tag_Lao, Tag.tag_termites}, "termites_Lao_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e512", "url", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_termites}, "termites_ບຸນບັ້ງໄຟ_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e517", "url", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_termites}, "termites_ບຸນບັ້ງໄຟ_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e522", "url", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_termites}, "termites_ບຸນບັ້ງໄຟ_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e528", "url", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_termites}, "termites_ບຸນບັ້ງໄຟ_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e533", "url", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_termites}, "termites_ບຸນບັ້ງໄຟ_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e538", "url", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_termites}, "termites_ບຸນບັ້ງໄຟ_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e543", "url", new Tag[]{Tag.tag_scorpions, Tag.tag_Rocket}, "scorpions_Rocket_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e548", "url", new Tag[]{Tag.tag_scorpions, Tag.tag_Rocket}, "scorpions_Rocket_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e553", "url", new Tag[]{Tag.tag_scorpions, Tag.tag_Rocket}, "scorpions_Rocket_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e558", "url", new Tag[]{Tag.tag_scorpions, Tag.tag_Rocket}, "scorpions_Rocket_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e563", "url", new Tag[]{Tag.tag_scorpions, Tag.tag_Rocket}, "scorpions_Rocket_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e568", "url", new Tag[]{Tag.tag_scorpions, Tag.tag_Rocket}, "scorpions_Rocket_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e573", "url", new Tag[]{Tag.tag_Festival, Tag.tag_scorpions}, "scorpions_Festival_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e578", "url", new Tag[]{Tag.tag_Festival, Tag.tag_scorpions}, "scorpions_Festival_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e584", "url", new Tag[]{Tag.tag_Festival, Tag.tag_scorpions}, "scorpions_Festival_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e589", "url", new Tag[]{Tag.tag_Festival, Tag.tag_scorpions}, "scorpions_Festival_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e594", "url", new Tag[]{Tag.tag_Festival, Tag.tag_scorpions}, "scorpions_Festival_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e599", "url", new Tag[]{Tag.tag_Festival, Tag.tag_scorpions}, "scorpions_Festival_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e604", "url", new Tag[]{Tag.tag_scorpions, Tag.tag_Thai}, "scorpions_Thai_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e609", "url", new Tag[]{Tag.tag_scorpions, Tag.tag_Thai}, "scorpions_Thai_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e614", "url", new Tag[]{Tag.tag_scorpions, Tag.tag_Thai}, "scorpions_Thai_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e619", "url", new Tag[]{Tag.tag_scorpions, Tag.tag_Thai}, "scorpions_Thai_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e624", "url", new Tag[]{Tag.tag_scorpions, Tag.tag_Thai}, "scorpions_Thai_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e629", "url", new Tag[]{Tag.tag_scorpions, Tag.tag_Thai}, "scorpions_Thai_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e634", "url", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_scorpions}, "scorpions_ประเพณีบุญบั้งไฟ_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e640", "url", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_scorpions}, "scorpions_ประเพณีบุญบั้งไฟ_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e645", "url", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_scorpions}, "scorpions_ประเพณีบุญบั้งไฟ_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e650", "url", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_scorpions}, "scorpions_ประเพณีบุญบั้งไฟ_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e655", "url", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_scorpions}, "scorpions_ประเพณีบุญบั้งไฟ_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e660", "url", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_scorpions}, "scorpions_ประเพณีบุญบั้งไฟ_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e665", "url", new Tag[]{Tag.tag_Lao, Tag.tag_scorpions}, "scorpions_Lao_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e670", "url", new Tag[]{Tag.tag_Lao, Tag.tag_scorpions}, "scorpions_Lao_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e675", "url", new Tag[]{Tag.tag_Lao, Tag.tag_scorpions}, "scorpions_Lao_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e680", "url", new Tag[]{Tag.tag_Lao, Tag.tag_scorpions}, "scorpions_Lao_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e685", "url", new Tag[]{Tag.tag_Lao, Tag.tag_scorpions}, "scorpions_Lao_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e690", "url", new Tag[]{Tag.tag_Lao, Tag.tag_scorpions}, "scorpions_Lao_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e696", "url", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_scorpions}, "scorpions_ບຸນບັ້ງໄຟ_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e701", "url", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_scorpions}, "scorpions_ບຸນບັ້ງໄຟ_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e706", "url", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_scorpions}, "scorpions_ບຸນບັ້ງໄຟ_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e711", "url", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_scorpions}, "scorpions_ບຸນບັ້ງໄຟ_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e716", "url", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_scorpions}, "scorpions_ບຸນບັ້ງໄຟ_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e721", "url", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_scorpions}, "scorpions_ບຸນບັ້ງໄຟ_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e726", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Rocket}, "centipedes_Rocket_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e731", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Rocket}, "centipedes_Rocket_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e736", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Rocket}, "centipedes_Rocket_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e741", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Rocket}, "centipedes_Rocket_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e746", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Rocket}, "centipedes_Rocket_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e752", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Rocket}, "centipedes_Rocket_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e757", "url", new Tag[]{Tag.tag_Festival, Tag.tag_centipedes}, "centipedes_Festival_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e762", "url", new Tag[]{Tag.tag_Festival, Tag.tag_centipedes}, "centipedes_Festival_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e767", "url", new Tag[]{Tag.tag_Festival, Tag.tag_centipedes}, "centipedes_Festival_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e772", "url", new Tag[]{Tag.tag_Festival, Tag.tag_centipedes}, "centipedes_Festival_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e777", "url", new Tag[]{Tag.tag_Festival, Tag.tag_centipedes}, "centipedes_Festival_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e782", "url", new Tag[]{Tag.tag_Festival, Tag.tag_centipedes}, "centipedes_Festival_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e787", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Thai}, "centipedes_Thai_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e792", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Thai}, "centipedes_Thai_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e797", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Thai}, "centipedes_Thai_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e802", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Thai}, "centipedes_Thai_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e808", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Thai}, "centipedes_Thai_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e813", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Thai}, "centipedes_Thai_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e818", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_ประเพณีบุญบั้งไฟ}, "centipedes_ประเพณีบุญบั้งไฟ_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e823", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_ประเพณีบุญบั้งไฟ}, "centipedes_ประเพณีบุญบั้งไฟ_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e828", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_ประเพณีบุญบั้งไฟ}, "centipedes_ประเพณีบุญบั้งไฟ_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e833", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_ประเพณีบุญบั้งไฟ}, "centipedes_ประเพณีบุญบั้งไฟ_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e838", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_ประเพณีบุญบั้งไฟ}, "centipedes_ประเพณีบุญบั้งไฟ_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e843", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_ประเพณีบุญบั้งไฟ}, "centipedes_ประเพณีบุญบั้งไฟ_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e848", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Lao}, "centipedes_Lao_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e853", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Lao}, "centipedes_Lao_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e858", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Lao}, "centipedes_Lao_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e864", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Lao}, "centipedes_Lao_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e869", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Lao}, "centipedes_Lao_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e874", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_Lao}, "centipedes_Lao_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e879", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_ບຸນບັ້ງໄຟ}, "centipedes_ບຸນບັ້ງໄຟ_0", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e884", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_ບຸນບັ້ງໄຟ}, "centipedes_ບຸນບັ້ງໄຟ_1", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e889", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_ບຸນບັ້ງໄຟ}, "centipedes_ບຸນບັ້ງໄຟ_2", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e894", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_ບຸນບັ້ງໄຟ}, "centipedes_ບຸນບັ້ງໄຟ_3", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e899", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_ບຸນບັ້ງໄຟ}, "centipedes_ບຸນບັ້ງໄຟ_4", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e904", "url", new Tag[]{Tag.tag_centipedes, Tag.tag_ບຸນບັ້ງໄຟ}, "centipedes_ບຸນບັ້ງໄຟ_5", "code", 0, true, true, true, true),
        new GeneratedStimulus("d1e909", "url", new Tag[]{Tag.tag_bad, Tag.tag_, Tag.tag__t_n_x0B_f_r_, Tag.tag_bad_chars, Tag.tag_this_add_, Tag.tag__, Tag.tag____, Tag.tag_chars}, "bad chars", "code", 0, true, true, true, true)};

    public enum Tag {

        tag_grammar, tag_number, tag_interesting, tag_multiple_words, tag_FILLER_AUDIO, tag_NOISE_AUDIO, tag_sim, tag_mid, tag_diff, tag_noise, tag_termites, tag_Rocket, tag_Festival, tag_Thai, tag_ประเพณีบุญบั้งไฟ, tag_Lao, tag_ບຸນບັ້ງໄຟ, tag_scorpions, tag_centipedes, tag_bad, tag_, tag__t_n_x0B_f_r_, tag_bad_chars, tag_this_add_, tag__, tag____, tag_chars
    }

    public static final void fillStimulusList(List<GeneratedStimulus> stimulusArray) {
        stimulusArray.addAll(Arrays.asList(values));
    }
    final private String uniqueId;
    final private String urlString;
    final private List<Tag> tags;
    final private String label;
    final private String code;
    final private int pauseMs;
    final private boolean mp3;
    final private boolean mp4;
    final private boolean ogg;
    final private boolean image;

    public GeneratedStimulus(String uniqueId, String urlString, Tag tags[], String label, String code, int pauseMs, boolean mp3, boolean mp4, boolean ogg, boolean image) {
        this.uniqueId = uniqueId;
        this.urlString = urlString;
        this.tags = Arrays.asList(tags);
        this.label = label;
        this.code = code;
        this.pauseMs = pauseMs;
        this.mp3 = mp3;
        this.mp4 = mp4;
        this.ogg = ogg;
        this.image = image;
    }

    @Override
    public String getUniqueId() {
        return uniqueId;
    }

    public List<Tag> getTags() {
        return tags;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public int getPauseMs() {
        return pauseMs;
    }

    @Override
    public boolean hasAudio() {
        return mp3;
    }

    @Override
    public boolean hasVideo() {
        return mp4 || ogg;
    }

    @Override
    public boolean hasRatingLabels() {
        return false;
    }

    @Override
    public boolean hasImage() {
        return image;
    }

    @Override
    public String getMp3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getImage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMp4() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOgg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getRatingLabels() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Stimulus o) {
        return this.uniqueId.compareTo(o.getUniqueId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.uniqueId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stimulus other = (Stimulus) obj;
        if (!Objects.equals(this.uniqueId, other.getUniqueId())) {
            return false;
        }
        return true;
    }
}
