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
import java.util.List;

/**
 * @since Dec 3, 2015 11:35:06 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public enum Stimulus {

    d1e286("d1e286", new Tag[]{Tag.tag_number, Tag.tag_interesting}, "One", 0, true, true, true, true),
    d1e291("d1e291", new Tag[]{Tag.tag_number, Tag.tag_multiple_words, Tag.tag_interesting}, "Two", 0, true, true, true, true),
    d1e298("d1e298", new Tag[]{Tag.tag_FILLER_AUDIO}, "Three", 0, true, true, true, true),
    d1e301("d1e301", new Tag[]{Tag.tag_FILLER_AUDIO}, "Four", 0, true, true, true, true),
    d1e304("d1e304", new Tag[]{Tag.tag_NOISE_AUDIO}, "Five", 0, true, true, true, true),
    d1e307("d1e307", new Tag[]{Tag.tag_NOISE_AUDIO}, "Six", 0, true, true, true, true),
    d1e310("d1e310", new Tag[]{Tag.tag_sim}, "sim_rabbit", 0, true, true, true, true),
    d1e313("d1e313", new Tag[]{Tag.tag_sim}, "sim_cat", 0, true, true, true, true),
    d1e316("d1e316", new Tag[]{Tag.tag_sim}, "sim_muffin", 0, true, true, true, true),
    d1e319("d1e319", new Tag[]{Tag.tag_sim}, "sim_you", 0, true, true, true, true),
    d1e322("d1e322", new Tag[]{Tag.tag_mid}, "mid_rabbit", 0, true, true, true, true),
    d1e326("d1e326", new Tag[]{Tag.tag_mid}, "mid_cat", 0, true, true, true, true),
    d1e329("d1e329", new Tag[]{Tag.tag_mid}, "mid_muffin", 0, true, true, true, true),
    d1e332("d1e332", new Tag[]{Tag.tag_mid}, "mid_you", 0, true, true, true, true),
    d1e335("d1e335", new Tag[]{Tag.tag_diff}, "diff_rabbit", 0, true, true, true, true),
    d1e338("d1e338", new Tag[]{Tag.tag_diff}, "diff_cat", 0, true, true, true, true),
    d1e341("d1e341", new Tag[]{Tag.tag_diff}, "diff_muffin", 0, true, true, true, true),
    d1e344("d1e344", new Tag[]{Tag.tag_diff}, "diff_you", 0, true, true, true, true),
    d1e347("d1e347", new Tag[]{Tag.tag_noise}, "noise_rabbit", 0, true, true, true, true),
    d1e350("d1e350", new Tag[]{Tag.tag_noise}, "noise_cat", 0, true, true, true, true),
    d1e353("d1e353", new Tag[]{Tag.tag_noise}, "noise_muffin", 0, true, true, true, true),
    d1e356("d1e356", new Tag[]{Tag.tag_noise}, "noise_you", 0, true, true, true, true),
    d1e360("d1e360", new Tag[]{Tag.tag_termites, Tag.tag_Rocket}, "termites_Rocket_0", 0, true, true, true, true),
    d1e365("d1e365", new Tag[]{Tag.tag_termites, Tag.tag_Rocket}, "termites_Rocket_1", 0, true, true, true, true),
    d1e370("d1e370", new Tag[]{Tag.tag_termites, Tag.tag_Rocket}, "termites_Rocket_2", 0, true, true, true, true),
    d1e375("d1e375", new Tag[]{Tag.tag_termites, Tag.tag_Rocket}, "termites_Rocket_3", 0, true, true, true, true),
    d1e380("d1e380", new Tag[]{Tag.tag_termites, Tag.tag_Rocket}, "termites_Rocket_4", 0, true, true, true, true),
    d1e385("d1e385", new Tag[]{Tag.tag_termites, Tag.tag_Rocket}, "termites_Rocket_5", 0, true, true, true, true),
    d1e390("d1e390", new Tag[]{Tag.tag_Festival, Tag.tag_termites}, "termites_Festival_0", 0, true, true, true, true),
    d1e395("d1e395", new Tag[]{Tag.tag_Festival, Tag.tag_termites}, "termites_Festival_1", 0, true, true, true, true),
    d1e400("d1e400", new Tag[]{Tag.tag_Festival, Tag.tag_termites}, "termites_Festival_2", 0, true, true, true, true),
    d1e405("d1e405", new Tag[]{Tag.tag_Festival, Tag.tag_termites}, "termites_Festival_3", 0, true, true, true, true),
    d1e410("d1e410", new Tag[]{Tag.tag_Festival, Tag.tag_termites}, "termites_Festival_4", 0, true, true, true, true),
    d1e416("d1e416", new Tag[]{Tag.tag_Festival, Tag.tag_termites}, "termites_Festival_5", 0, true, true, true, true),
    d1e421("d1e421", new Tag[]{Tag.tag_termites, Tag.tag_Thai}, "termites_Thai_0", 0, true, true, true, true),
    d1e426("d1e426", new Tag[]{Tag.tag_termites, Tag.tag_Thai}, "termites_Thai_1", 0, true, true, true, true),
    d1e431("d1e431", new Tag[]{Tag.tag_termites, Tag.tag_Thai}, "termites_Thai_2", 0, true, true, true, true),
    d1e436("d1e436", new Tag[]{Tag.tag_termites, Tag.tag_Thai}, "termites_Thai_3", 0, true, true, true, true),
    d1e441("d1e441", new Tag[]{Tag.tag_termites, Tag.tag_Thai}, "termites_Thai_4", 0, true, true, true, true),
    d1e446("d1e446", new Tag[]{Tag.tag_termites, Tag.tag_Thai}, "termites_Thai_5", 0, true, true, true, true),
    d1e451("d1e451", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_termites}, "termites_ประเพณีบุญบั้งไฟ_0", 0, true, true, true, true),
    d1e456("d1e456", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_termites}, "termites_ประเพณีบุญบั้งไฟ_1", 0, true, true, true, true),
    d1e461("d1e461", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_termites}, "termites_ประเพณีบุญบั้งไฟ_2", 0, true, true, true, true),
    d1e466("d1e466", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_termites}, "termites_ประเพณีบุญบั้งไฟ_3", 0, true, true, true, true),
    d1e472("d1e472", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_termites}, "termites_ประเพณีบุญบั้งไฟ_4", 0, true, true, true, true),
    d1e477("d1e477", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_termites}, "termites_ประเพณีบุญบั้งไฟ_5", 0, true, true, true, true),
    d1e482("d1e482", new Tag[]{Tag.tag_Lao, Tag.tag_termites}, "termites_Lao_0", 0, true, true, true, true),
    d1e487("d1e487", new Tag[]{Tag.tag_Lao, Tag.tag_termites}, "termites_Lao_1", 0, true, true, true, true),
    d1e492("d1e492", new Tag[]{Tag.tag_Lao, Tag.tag_termites}, "termites_Lao_2", 0, true, true, true, true),
    d1e497("d1e497", new Tag[]{Tag.tag_Lao, Tag.tag_termites}, "termites_Lao_3", 0, true, true, true, true),
    d1e502("d1e502", new Tag[]{Tag.tag_Lao, Tag.tag_termites}, "termites_Lao_4", 0, true, true, true, true),
    d1e507("d1e507", new Tag[]{Tag.tag_Lao, Tag.tag_termites}, "termites_Lao_5", 0, true, true, true, true),
    d1e512("d1e512", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_termites}, "termites_ບຸນບັ້ງໄຟ_0", 0, true, true, true, true),
    d1e517("d1e517", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_termites}, "termites_ບຸນບັ້ງໄຟ_1", 0, true, true, true, true),
    d1e522("d1e522", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_termites}, "termites_ບຸນບັ້ງໄຟ_2", 0, true, true, true, true),
    d1e528("d1e528", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_termites}, "termites_ບຸນບັ້ງໄຟ_3", 0, true, true, true, true),
    d1e533("d1e533", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_termites}, "termites_ບຸນບັ້ງໄຟ_4", 0, true, true, true, true),
    d1e538("d1e538", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_termites}, "termites_ບຸນບັ້ງໄຟ_5", 0, true, true, true, true),
    d1e543("d1e543", new Tag[]{Tag.tag_scorpions, Tag.tag_Rocket}, "scorpions_Rocket_0", 0, true, true, true, true),
    d1e548("d1e548", new Tag[]{Tag.tag_scorpions, Tag.tag_Rocket}, "scorpions_Rocket_1", 0, true, true, true, true),
    d1e553("d1e553", new Tag[]{Tag.tag_scorpions, Tag.tag_Rocket}, "scorpions_Rocket_2", 0, true, true, true, true),
    d1e558("d1e558", new Tag[]{Tag.tag_scorpions, Tag.tag_Rocket}, "scorpions_Rocket_3", 0, true, true, true, true),
    d1e563("d1e563", new Tag[]{Tag.tag_scorpions, Tag.tag_Rocket}, "scorpions_Rocket_4", 0, true, true, true, true),
    d1e568("d1e568", new Tag[]{Tag.tag_scorpions, Tag.tag_Rocket}, "scorpions_Rocket_5", 0, true, true, true, true),
    d1e573("d1e573", new Tag[]{Tag.tag_Festival, Tag.tag_scorpions}, "scorpions_Festival_0", 0, true, true, true, true),
    d1e578("d1e578", new Tag[]{Tag.tag_Festival, Tag.tag_scorpions}, "scorpions_Festival_1", 0, true, true, true, true),
    d1e584("d1e584", new Tag[]{Tag.tag_Festival, Tag.tag_scorpions}, "scorpions_Festival_2", 0, true, true, true, true),
    d1e589("d1e589", new Tag[]{Tag.tag_Festival, Tag.tag_scorpions}, "scorpions_Festival_3", 0, true, true, true, true),
    d1e594("d1e594", new Tag[]{Tag.tag_Festival, Tag.tag_scorpions}, "scorpions_Festival_4", 0, true, true, true, true),
    d1e599("d1e599", new Tag[]{Tag.tag_Festival, Tag.tag_scorpions}, "scorpions_Festival_5", 0, true, true, true, true),
    d1e604("d1e604", new Tag[]{Tag.tag_scorpions, Tag.tag_Thai}, "scorpions_Thai_0", 0, true, true, true, true),
    d1e609("d1e609", new Tag[]{Tag.tag_scorpions, Tag.tag_Thai}, "scorpions_Thai_1", 0, true, true, true, true),
    d1e614("d1e614", new Tag[]{Tag.tag_scorpions, Tag.tag_Thai}, "scorpions_Thai_2", 0, true, true, true, true),
    d1e619("d1e619", new Tag[]{Tag.tag_scorpions, Tag.tag_Thai}, "scorpions_Thai_3", 0, true, true, true, true),
    d1e624("d1e624", new Tag[]{Tag.tag_scorpions, Tag.tag_Thai}, "scorpions_Thai_4", 0, true, true, true, true),
    d1e629("d1e629", new Tag[]{Tag.tag_scorpions, Tag.tag_Thai}, "scorpions_Thai_5", 0, true, true, true, true),
    d1e634("d1e634", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_scorpions}, "scorpions_ประเพณีบุญบั้งไฟ_0", 0, true, true, true, true),
    d1e640("d1e640", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_scorpions}, "scorpions_ประเพณีบุญบั้งไฟ_1", 0, true, true, true, true),
    d1e645("d1e645", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_scorpions}, "scorpions_ประเพณีบุญบั้งไฟ_2", 0, true, true, true, true),
    d1e650("d1e650", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_scorpions}, "scorpions_ประเพณีบุญบั้งไฟ_3", 0, true, true, true, true),
    d1e655("d1e655", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_scorpions}, "scorpions_ประเพณีบุญบั้งไฟ_4", 0, true, true, true, true),
    d1e660("d1e660", new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_scorpions}, "scorpions_ประเพณีบุญบั้งไฟ_5", 0, true, true, true, true),
    d1e665("d1e665", new Tag[]{Tag.tag_Lao, Tag.tag_scorpions}, "scorpions_Lao_0", 0, true, true, true, true),
    d1e670("d1e670", new Tag[]{Tag.tag_Lao, Tag.tag_scorpions}, "scorpions_Lao_1", 0, true, true, true, true),
    d1e675("d1e675", new Tag[]{Tag.tag_Lao, Tag.tag_scorpions}, "scorpions_Lao_2", 0, true, true, true, true),
    d1e680("d1e680", new Tag[]{Tag.tag_Lao, Tag.tag_scorpions}, "scorpions_Lao_3", 0, true, true, true, true),
    d1e685("d1e685", new Tag[]{Tag.tag_Lao, Tag.tag_scorpions}, "scorpions_Lao_4", 0, true, true, true, true),
    d1e690("d1e690", new Tag[]{Tag.tag_Lao, Tag.tag_scorpions}, "scorpions_Lao_5", 0, true, true, true, true),
    d1e696("d1e696", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_scorpions}, "scorpions_ບຸນບັ້ງໄຟ_0", 0, true, true, true, true),
    d1e701("d1e701", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_scorpions}, "scorpions_ບຸນບັ້ງໄຟ_1", 0, true, true, true, true),
    d1e706("d1e706", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_scorpions}, "scorpions_ບຸນບັ້ງໄຟ_2", 0, true, true, true, true),
    d1e711("d1e711", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_scorpions}, "scorpions_ບຸນບັ້ງໄຟ_3", 0, true, true, true, true),
    d1e716("d1e716", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_scorpions}, "scorpions_ບຸນບັ້ງໄຟ_4", 0, true, true, true, true),
    d1e721("d1e721", new Tag[]{Tag.tag_ບຸນບັ້ງໄຟ, Tag.tag_scorpions}, "scorpions_ບຸນບັ້ງໄຟ_5", 0, true, true, true, true),
    d1e726("d1e726", new Tag[]{Tag.tag_centipedes, Tag.tag_Rocket}, "centipedes_Rocket_0", 0, true, true, true, true),
    d1e731("d1e731", new Tag[]{Tag.tag_centipedes, Tag.tag_Rocket}, "centipedes_Rocket_1", 0, true, true, true, true),
    d1e736("d1e736", new Tag[]{Tag.tag_centipedes, Tag.tag_Rocket}, "centipedes_Rocket_2", 0, true, true, true, true),
    d1e741("d1e741", new Tag[]{Tag.tag_centipedes, Tag.tag_Rocket}, "centipedes_Rocket_3", 0, true, true, true, true),
    d1e746("d1e746", new Tag[]{Tag.tag_centipedes, Tag.tag_Rocket}, "centipedes_Rocket_4", 0, true, true, true, true),
    d1e752("d1e752", new Tag[]{Tag.tag_centipedes, Tag.tag_Rocket}, "centipedes_Rocket_5", 0, true, true, true, true),
    d1e757("d1e757", new Tag[]{Tag.tag_Festival, Tag.tag_centipedes}, "centipedes_Festival_0", 0, true, true, true, true),
    d1e762("d1e762", new Tag[]{Tag.tag_Festival, Tag.tag_centipedes}, "centipedes_Festival_1", 0, true, true, true, true),
    d1e767("d1e767", new Tag[]{Tag.tag_Festival, Tag.tag_centipedes}, "centipedes_Festival_2", 0, true, true, true, true),
    d1e772("d1e772", new Tag[]{Tag.tag_Festival, Tag.tag_centipedes}, "centipedes_Festival_3", 0, true, true, true, true),
    d1e777("d1e777", new Tag[]{Tag.tag_Festival, Tag.tag_centipedes}, "centipedes_Festival_4", 0, true, true, true, true),
    d1e782("d1e782", new Tag[]{Tag.tag_Festival, Tag.tag_centipedes}, "centipedes_Festival_5", 0, true, true, true, true),
    d1e787("d1e787", new Tag[]{Tag.tag_centipedes, Tag.tag_Thai}, "centipedes_Thai_0", 0, true, true, true, true),
    d1e792("d1e792", new Tag[]{Tag.tag_centipedes, Tag.tag_Thai}, "centipedes_Thai_1", 0, true, true, true, true),
    d1e797("d1e797", new Tag[]{Tag.tag_centipedes, Tag.tag_Thai}, "centipedes_Thai_2", 0, true, true, true, true),
    d1e802("d1e802", new Tag[]{Tag.tag_centipedes, Tag.tag_Thai}, "centipedes_Thai_3", 0, true, true, true, true),
    d1e808("d1e808", new Tag[]{Tag.tag_centipedes, Tag.tag_Thai}, "centipedes_Thai_4", 0, true, true, true, true),
    d1e813("d1e813", new Tag[]{Tag.tag_centipedes, Tag.tag_Thai}, "centipedes_Thai_5", 0, true, true, true, true),
    d1e818("d1e818", new Tag[]{Tag.tag_centipedes, Tag.tag_ประเพณีบุญบั้งไฟ}, "centipedes_ประเพณีบุญบั้งไฟ_0", 0, true, true, true, true),
    d1e823("d1e823", new Tag[]{Tag.tag_centipedes, Tag.tag_ประเพณีบุญบั้งไฟ}, "centipedes_ประเพณีบุญบั้งไฟ_1", 0, true, true, true, true),
    d1e828("d1e828", new Tag[]{Tag.tag_centipedes, Tag.tag_ประเพณีบุญบั้งไฟ}, "centipedes_ประเพณีบุญบั้งไฟ_2", 0, true, true, true, true),
    d1e833("d1e833", new Tag[]{Tag.tag_centipedes, Tag.tag_ประเพณีบุญบั้งไฟ}, "centipedes_ประเพณีบุญบั้งไฟ_3", 0, true, true, true, true),
    d1e838("d1e838", new Tag[]{Tag.tag_centipedes, Tag.tag_ประเพณีบุญบั้งไฟ}, "centipedes_ประเพณีบุญบั้งไฟ_4", 0, true, true, true, true),
    d1e843("d1e843", new Tag[]{Tag.tag_centipedes, Tag.tag_ประเพณีบุญบั้งไฟ}, "centipedes_ประเพณีบุญบั้งไฟ_5", 0, true, true, true, true),
    d1e848("d1e848", new Tag[]{Tag.tag_centipedes, Tag.tag_Lao}, "centipedes_Lao_0", 0, true, true, true, true),
    d1e853("d1e853", new Tag[]{Tag.tag_centipedes, Tag.tag_Lao}, "centipedes_Lao_1", 0, true, true, true, true),
    d1e858("d1e858", new Tag[]{Tag.tag_centipedes, Tag.tag_Lao}, "centipedes_Lao_2", 0, true, true, true, true),
    d1e864("d1e864", new Tag[]{Tag.tag_centipedes, Tag.tag_Lao}, "centipedes_Lao_3", 0, true, true, true, true),
    d1e869("d1e869", new Tag[]{Tag.tag_centipedes, Tag.tag_Lao}, "centipedes_Lao_4", 0, true, true, true, true),
    d1e874("d1e874", new Tag[]{Tag.tag_centipedes, Tag.tag_Lao}, "centipedes_Lao_5", 0, true, true, true, true),
    d1e879("d1e879", new Tag[]{Tag.tag_centipedes, Tag.tag_ບຸນບັ້ງໄຟ}, "centipedes_ບຸນບັ້ງໄຟ_0", 0, true, true, true, true),
    d1e884("d1e884", new Tag[]{Tag.tag_centipedes, Tag.tag_ບຸນບັ້ງໄຟ}, "centipedes_ບຸນບັ້ງໄຟ_1", 0, true, true, true, true),
    d1e889("d1e889", new Tag[]{Tag.tag_centipedes, Tag.tag_ບຸນບັ້ງໄຟ}, "centipedes_ບຸນບັ້ງໄຟ_2", 0, true, true, true, true),
    d1e894("d1e894", new Tag[]{Tag.tag_centipedes, Tag.tag_ບຸນບັ້ງໄຟ}, "centipedes_ບຸນບັ້ງໄຟ_3", 0, true, true, true, true),
    d1e899("d1e899", new Tag[]{Tag.tag_centipedes, Tag.tag_ບຸນບັ້ງໄຟ}, "centipedes_ບຸນບັ້ງໄຟ_4", 0, true, true, true, true),
    d1e904("d1e904", new Tag[]{Tag.tag_centipedes, Tag.tag_ບຸນບັ້ງໄຟ}, "centipedes_ບຸນບັ້ງໄຟ_5", 0, true, true, true, true),
    d1e909("d1e909", new Tag[]{Tag.tag_bad, Tag.tag_, Tag.tag__t_n_x0B_f_r_, Tag.tag_bad_chars, Tag.tag_this_add_, Tag.tag__, Tag.tag____, Tag.tag_chars}, "bad chars", 0, true, true, true, true);

    public enum Tag {

        tag_number, tag_interesting, tag_multiple_words, tag_FILLER_AUDIO, tag_NOISE_AUDIO, tag_sim, tag_mid, tag_diff, tag_noise, tag_termites, tag_Rocket, tag_Festival, tag_Thai, tag_ประเพณีบุญบั้งไฟ, tag_Lao, tag_ບຸນບັ້ງໄຟ, tag_scorpions, tag_centipedes, tag_bad, tag_, tag__t_n_x0B_f_r_, tag_bad_chars, tag_this_add_, tag__, tag____, tag_chars
    }

    public static final void fillStimulusList(List<Stimulus> stimulusArray) {
        stimulusArray.add(d1e286);
        stimulusArray.add(d1e291);
        stimulusArray.add(d1e298);
        stimulusArray.add(d1e301);
        stimulusArray.add(d1e304);
        stimulusArray.add(d1e307);
        stimulusArray.add(d1e310);
        stimulusArray.add(d1e313);
        stimulusArray.add(d1e316);
        stimulusArray.add(d1e319);
        stimulusArray.add(d1e322);
        stimulusArray.add(d1e326);
        stimulusArray.add(d1e329);
        stimulusArray.add(d1e332);
        stimulusArray.add(d1e335);
        stimulusArray.add(d1e338);
        stimulusArray.add(d1e341);
        stimulusArray.add(d1e344);
        stimulusArray.add(d1e347);
        stimulusArray.add(d1e350);
        stimulusArray.add(d1e353);
        stimulusArray.add(d1e356);
        stimulusArray.add(d1e360);
        stimulusArray.add(d1e365);
        stimulusArray.add(d1e370);
        stimulusArray.add(d1e375);
        stimulusArray.add(d1e380);
        stimulusArray.add(d1e385);
        stimulusArray.add(d1e390);
        stimulusArray.add(d1e395);
        stimulusArray.add(d1e400);
        stimulusArray.add(d1e405);
        stimulusArray.add(d1e410);
        stimulusArray.add(d1e416);
        stimulusArray.add(d1e421);
        stimulusArray.add(d1e426);
        stimulusArray.add(d1e431);
        stimulusArray.add(d1e436);
        stimulusArray.add(d1e441);
        stimulusArray.add(d1e446);
        stimulusArray.add(d1e451);
        stimulusArray.add(d1e456);
        stimulusArray.add(d1e461);
        stimulusArray.add(d1e466);
        stimulusArray.add(d1e472);
        stimulusArray.add(d1e477);
        stimulusArray.add(d1e482);
        stimulusArray.add(d1e487);
        stimulusArray.add(d1e492);
        stimulusArray.add(d1e497);
        stimulusArray.add(d1e502);
        stimulusArray.add(d1e507);
        stimulusArray.add(d1e512);
        stimulusArray.add(d1e517);
        stimulusArray.add(d1e522);
        stimulusArray.add(d1e528);
        stimulusArray.add(d1e533);
        stimulusArray.add(d1e538);
        stimulusArray.add(d1e543);
        stimulusArray.add(d1e548);
        stimulusArray.add(d1e553);
        stimulusArray.add(d1e558);
        stimulusArray.add(d1e563);
        stimulusArray.add(d1e568);
        stimulusArray.add(d1e573);
        stimulusArray.add(d1e578);
        stimulusArray.add(d1e584);
        stimulusArray.add(d1e589);
        stimulusArray.add(d1e594);
        stimulusArray.add(d1e599);
        stimulusArray.add(d1e604);
        stimulusArray.add(d1e609);
        stimulusArray.add(d1e614);
        stimulusArray.add(d1e619);
        stimulusArray.add(d1e624);
        stimulusArray.add(d1e629);
        stimulusArray.add(d1e634);
        stimulusArray.add(d1e640);
        stimulusArray.add(d1e645);
        stimulusArray.add(d1e650);
        stimulusArray.add(d1e655);
        stimulusArray.add(d1e660);
        stimulusArray.add(d1e665);
        stimulusArray.add(d1e670);
        stimulusArray.add(d1e675);
        stimulusArray.add(d1e680);
        stimulusArray.add(d1e685);
        stimulusArray.add(d1e690);
        stimulusArray.add(d1e696);
        stimulusArray.add(d1e701);
        stimulusArray.add(d1e706);
        stimulusArray.add(d1e711);
        stimulusArray.add(d1e716);
        stimulusArray.add(d1e721);
        stimulusArray.add(d1e726);
        stimulusArray.add(d1e731);
        stimulusArray.add(d1e736);
        stimulusArray.add(d1e741);
        stimulusArray.add(d1e746);
        stimulusArray.add(d1e752);
        stimulusArray.add(d1e757);
        stimulusArray.add(d1e762);
        stimulusArray.add(d1e767);
        stimulusArray.add(d1e772);
        stimulusArray.add(d1e777);
        stimulusArray.add(d1e782);
        stimulusArray.add(d1e787);
        stimulusArray.add(d1e792);
        stimulusArray.add(d1e797);
        stimulusArray.add(d1e802);
        stimulusArray.add(d1e808);
        stimulusArray.add(d1e813);
        stimulusArray.add(d1e818);
        stimulusArray.add(d1e823);
        stimulusArray.add(d1e828);
        stimulusArray.add(d1e833);
        stimulusArray.add(d1e838);
        stimulusArray.add(d1e843);
        stimulusArray.add(d1e848);
        stimulusArray.add(d1e853);
        stimulusArray.add(d1e858);
        stimulusArray.add(d1e864);
        stimulusArray.add(d1e869);
        stimulusArray.add(d1e874);
        stimulusArray.add(d1e879);
        stimulusArray.add(d1e884);
        stimulusArray.add(d1e889);
        stimulusArray.add(d1e894);
        stimulusArray.add(d1e899);
        stimulusArray.add(d1e904);
        stimulusArray.add(d1e909);
    }
    final private String uniqueId;
    final private List<Tag> tags;
    final private String label;
    final private int pauseMs;
    final private boolean mp3;
    final private boolean mp4;
    final private boolean ogg;
    final private boolean image;

    Stimulus(String uniqueId, Tag tags[], String label, int pauseMs, boolean mp3, boolean mp4, boolean ogg, boolean image) {
        this.uniqueId = uniqueId;
        this.tags = Arrays.asList(tags);
        this.label = label;
        this.pauseMs = pauseMs;
        this.mp3 = mp3;
        this.mp4 = mp4;
        this.ogg = ogg;
        this.image = image;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getLabel() {
        return label;
    }

    public String getMp3() {
        return uniqueId + ".mp3";
    }

    public String getImage() {
        return uniqueId + ".jpg";
    }

    public String getMp4() {
        return uniqueId + ".mp4";
    }

    public String getOgg() {
        return uniqueId + ".ogg";
    }

}
