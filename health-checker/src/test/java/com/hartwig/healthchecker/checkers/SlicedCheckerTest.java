package com.hartwig.healthchecker.checkers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import com.google.common.io.Resources;
import com.hartwig.healthchecker.checkers.checks.HealthCheck;
import com.hartwig.healthchecker.checkers.checks.SlicedCheck;
import com.hartwig.healthchecker.exception.HealthChecksException;
import com.hartwig.healthchecker.io.dir.RunContext;
import com.hartwig.healthchecker.io.dir.TestRunContextFactory;
import com.hartwig.healthchecker.result.BaseResult;
import com.hartwig.healthchecker.result.SingleValueResult;

import org.junit.Assert;
import org.junit.Test;

public class SlicedCheckerTest {

    private static final String RUN_DIRECTORY = Resources.getResource("checks/sliced").getPath();
    private static final String REF_SAMPLE = "sample1";
    private static final String TUMOR_SAMPLE = "sample2";

    private static final int EXPECTED_SLICED_COUNT = 4;

    private final SlicedChecker checker = new SlicedChecker();

    @Test
    public void canAnalyseTypicalSlicedVCF() throws IOException, HealthChecksException {
        final RunContext runContext = TestRunContextFactory.forTest(RUN_DIRECTORY, REF_SAMPLE, TUMOR_SAMPLE);

        final BaseResult result = checker.tryRun(runContext);
        Assert.assertEquals(CheckType.SLICED, result.getCheckType());

        final HealthCheck check = ((SingleValueResult) result).getCheck();
        Assert.assertEquals(SlicedCheck.SLICED_NUMBER_OF_VARIANTS.toString(), check.getCheckName());
        assertEquals(TUMOR_SAMPLE, check.getSampleId());
        assertEquals(Integer.toString(EXPECTED_SLICED_COUNT), check.getValue());
    }

    @Test
    public void errorYieldsCorrectOutput() {
        final RunContext runContext = TestRunContextFactory.forTest(RUN_DIRECTORY, REF_SAMPLE, TUMOR_SAMPLE);
        final SingleValueResult result = (SingleValueResult) checker.errorRun(runContext);
        assertNotNull(result.getCheck());
    }

    @Test(expected = IOException.class)
    public void readingNonExistingFileYieldsIOException() throws IOException, HealthChecksException {
        final RunContext runContext = TestRunContextFactory.forTest("DoesNotExist", REF_SAMPLE, TUMOR_SAMPLE);
        checker.tryRun(runContext);
    }
}