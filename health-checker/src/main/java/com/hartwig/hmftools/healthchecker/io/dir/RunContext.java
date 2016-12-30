package com.hartwig.hmftools.healthchecker.io.dir;

import org.jetbrains.annotations.NotNull;

public interface RunContext {

    @NotNull
    String runDirectory();

    @NotNull
    String runName();

    @NotNull
    String refSample();

    @NotNull
    String tumorSample();

    boolean hasPassedTests();
}