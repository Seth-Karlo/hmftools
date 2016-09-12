package com.hartwig.hmftools.healthcheckeranalyser.model;

import java.util.Map;

import org.jetbrains.annotations.NotNull;

public class HealthCheckReport {

    @NotNull
    private final String runDate;
    @NotNull
    private final String pipelineVersion;
    @NotNull
    private final String refSample;
    @NotNull
    private final String tumorSample;
    @NotNull
    private final Map<String, String> refChecks;
    @NotNull
    private final Map<String, String> tumorChecks;
    @NotNull
    private final Map<String, String> patientChecks;

    HealthCheckReport(@NotNull final String runDate, @NotNull final String pipelineVersion,
            @NotNull final String refSample, @NotNull final String tumorSample,
            @NotNull final Map<String, String> refChecks, @NotNull final Map<String, String> tumorChecks,
            @NotNull final Map<String, String> patientChecks) {
        this.runDate = runDate;
        this.pipelineVersion = pipelineVersion;
        this.refSample = refSample;
        this.tumorSample = tumorSample;
        this.refChecks = refChecks;
        this.tumorChecks = tumorChecks;
        this.patientChecks = patientChecks;
    }

    @NotNull
    public String runDate() {
        return runDate;
    }

    @NotNull
    public String pipelineVersion() {
        return pipelineVersion;
    }

    @NotNull
    public String refSample() {
        return refSample;
    }

    @NotNull
    public String tumorSample() {
        return tumorSample;
    }

    @NotNull
    public Map<String, String> refChecks() {
        return refChecks;
    }

    @NotNull
    public Map<String, String> tumorChecks() {
        return tumorChecks;
    }

    @NotNull
    public Map<String, String> patientChecks() {
        return patientChecks;
    }
}