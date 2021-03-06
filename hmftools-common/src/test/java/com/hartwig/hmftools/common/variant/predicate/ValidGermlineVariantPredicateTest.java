package com.hartwig.hmftools.common.variant.predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.hartwig.hmftools.common.variant.GermlineVariant;
import com.hartwig.hmftools.common.variant.VariantType;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

public class ValidGermlineVariantPredicateTest {

    private static final String VARIANT = "1/1:0,3:3:9:103,9,0";
    private static final String NOT_VARIANT = "./.:773,0:773";

    @Test
    public void checkSNPVariant() {
        final GermlineVariant refSNPVariant = createVariant(VariantType.SNP, VARIANT, NOT_VARIANT);
        final GermlineVariant noVariant = createVariant(VariantType.SNP, NOT_VARIANT, NOT_VARIANT);

        ValidGermlineVariantPredicate predicate = new ValidGermlineVariantPredicate(VariantType.SNP, true);
        assertTrue(predicate.test(refSNPVariant));
        assertFalse(predicate.test(noVariant));

        predicate = new ValidGermlineVariantPredicate(VariantType.INDEL, true);
        assertFalse(predicate.test(refSNPVariant));
        assertFalse(predicate.test(noVariant));
    }

    @Test
    public void checkIndelVariant() {
        final GermlineVariant refIndelVariant = createVariant(VariantType.INDEL, VARIANT, NOT_VARIANT);
        final GermlineVariant noVariant = createVariant(VariantType.INDEL, NOT_VARIANT, NOT_VARIANT);

        ValidGermlineVariantPredicate predicate = new ValidGermlineVariantPredicate(VariantType.INDEL, true);
        assertTrue(predicate.test(refIndelVariant));
        assertFalse(predicate.test(noVariant));

        predicate = new ValidGermlineVariantPredicate(VariantType.SNP, true);
        assertFalse(predicate.test(refIndelVariant));
        assertFalse(predicate.test(noVariant));
    }

    @NotNull
    private static GermlineVariant createVariant(@NotNull VariantType type, @NotNull String germlineData,
            @NotNull String tumorData) {
        return new GermlineVariant(type, "AnyFilter", germlineData, tumorData);
    }
}
