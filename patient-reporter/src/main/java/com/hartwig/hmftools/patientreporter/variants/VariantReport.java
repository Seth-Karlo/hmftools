package com.hartwig.hmftools.patientreporter.variants;

import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VariantReport {

    @NotNull
    private final String gene;
    @NotNull
    private final String position;
    @NotNull
    private final String ref;
    @NotNull
    private final String alt;
    @NotNull
    private final String transcript;
    @NotNull
    private final String hgvsCoding;
    @NotNull
    private final String hgvsProtein;
    @NotNull
    private final String consequence;

    @Nullable
    private final String cosmicID;
    private final double alleleFrequency;
    private final int readDepth;

    private VariantReport(@NotNull final String gene, @NotNull final String position, @NotNull final String ref,
            @NotNull final String alt, @NotNull final String transcript, @NotNull final String hgvsCoding,
            @NotNull final String hgvsProtein, @NotNull final String consequence, @Nullable final String cosmicID,
            final double alleleFrequency, final int readDepth) {
        this.gene = gene;
        this.position = position;
        this.ref = ref;
        this.alt = alt;
        this.transcript = transcript;
        this.hgvsCoding = hgvsCoding;
        this.hgvsProtein = hgvsProtein;
        this.consequence = consequence;
        this.cosmicID = cosmicID;
        this.alleleFrequency = alleleFrequency;
        this.readDepth = readDepth;
    }

    static class Builder {
        @NotNull
        private String gene = Strings.EMPTY;
        @NotNull
        private String position = Strings.EMPTY;
        @NotNull
        private String ref = Strings.EMPTY;
        @NotNull
        private String alt = Strings.EMPTY;
        @NotNull
        private String transcript = Strings.EMPTY;
        @NotNull
        private String hgvsCoding = Strings.EMPTY;
        @NotNull
        private String hgvsProtein = Strings.EMPTY;
        @NotNull
        private String consequence = Strings.EMPTY;

        @Nullable
        private String cosmicID;
        private double alleleFrequency;
        private int readDepth;

        Builder() {
        }

        @NotNull
        Builder gene(@NotNull final String gene) {
            this.gene = gene;
            return this;
        }

        @NotNull
        Builder position(@NotNull final String position) {
            this.position = position;
            return this;
        }

        @NotNull
        Builder ref(@NotNull final String ref) {
            this.ref = ref;
            return this;
        }

        @NotNull
        Builder alt(@NotNull final String alt) {
            this.alt = alt;
            return this;
        }

        @NotNull
        Builder transcript(@NotNull final String transcript) {
            this.transcript = transcript;
            return this;
        }

        @NotNull
        Builder hgvsCoding(@NotNull final String hgvsCoding) {
            this.hgvsCoding = hgvsCoding;
            return this;
        }

        @NotNull
        Builder hgvsProtein(@NotNull final String hgvsProtein) {
            this.hgvsProtein = hgvsProtein;
            return this;
        }

        @NotNull
        Builder consequence(@NotNull final String consequence) {
            this.consequence = consequence;
            return this;
        }

        @NotNull
        Builder cosmicID(@Nullable final String cosmicID) {
            this.cosmicID = cosmicID;
            return this;
        }

        @NotNull
        Builder alleleFrequency(final double alleleFrequency) {
            this.alleleFrequency = alleleFrequency;
            return this;
        }

        @NotNull
        Builder readDepth(final int readDepth) {
            this.readDepth = readDepth;
            return this;
        }

        @NotNull
        VariantReport build() {
            return new VariantReport(gene, position, ref, alt, transcript, hgvsCoding, hgvsProtein, consequence,
                    cosmicID, alleleFrequency, readDepth);
        }
    }
}