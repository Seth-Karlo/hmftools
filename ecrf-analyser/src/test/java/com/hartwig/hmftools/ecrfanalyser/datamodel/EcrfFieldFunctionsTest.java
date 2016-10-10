package com.hartwig.hmftools.ecrfanalyser.datamodel;

import static org.junit.Assert.assertEquals;

import com.google.common.collect.Maps;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

public class EcrfFieldFunctionsTest {

    @Test
    public void canDetermineFieldRelevance() {
        verifyItemGroup(true, "Relevant");
        verifyItemGroup(false, "MetaData");
        verifyItem(false, "GROUP");
        verifyItem(false, "group3");
        verifyItem(true, "Relevant");
    }

    private static void verifyItemGroup(boolean isRelevant, @NotNull String itemGroupOID) {
        EcrfField field = new EcrfField("SE.Study", "FRM.Form", "GRP." + itemGroupOID, "GRP.Item", "",
                Maps.<Integer, String>newHashMap());
        assertEquals(isRelevant, EcrfFieldFunctions.isRelevant(field));
    }

    private static void verifyItem(boolean expected, @NotNull String itemOID) {
        EcrfField field = new EcrfField("SE.Study", "FRM.Form", "GRP.ItemGroup", "GRP." + itemOID, "",
                Maps.<Integer, String>newHashMap());
        assertEquals(expected, EcrfFieldFunctions.isRelevant(field));
    }

    @Test
    public void canConvertToName() {
        String studyEventOID = "SE.Study";
        String formOID = "FRM.Form";
        String itemGroupOID = "GRP.ItemGroup";
        String itemOID = "GRP.ItemGroup.Item";

        assertEquals("STUDY.FORM.ITEMGROUP.ITEM",
                EcrfFieldFunctions.name(studyEventOID, formOID, itemGroupOID, itemOID));
    }
}