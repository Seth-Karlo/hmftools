package com.hartwig.hmftools.healthchecker.runners;

import java.io.IOException;

import com.hartwig.hmftools.common.exception.HartwigException;
import com.hartwig.hmftools.healthchecker.context.RunContext;
import com.hartwig.hmftools.healthchecker.result.BaseResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public abstract class ErrorHandlingChecker implements HealthChecker {

    private static final Logger LOGGER = LogManager.getLogger(ErrorHandlingChecker.class);
    private static final String EXCEPTION_MSG = "Got an exception with message: %s";

    @NotNull
    @Override
    public BaseResult run(@NotNull final RunContext runContext) {
        BaseResult result;
        try {
            result = tryRun(runContext);
        } catch (IOException | HartwigException exception) {
            LOGGER.error(String.format(EXCEPTION_MSG, exception.getMessage()));
            result = errorRun(runContext);
        }
        return result;
    }

    @NotNull
    protected abstract BaseResult tryRun(@NotNull RunContext runContext) throws IOException, HartwigException;

    @NotNull
    protected abstract BaseResult errorRun(@NotNull RunContext runContext);
}
