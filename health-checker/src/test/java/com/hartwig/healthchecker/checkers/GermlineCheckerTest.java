package com.hartwig.healthchecker.checkers;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.google.common.io.Resources;
import com.hartwig.healthchecker.checkers.checks.GermlineCheck;
import com.hartwig.healthchecker.checkers.checks.HealthCheck;
import com.hartwig.healthchecker.exception.HealthChecksException;
import com.hartwig.healthchecker.io.dir.RunContext;
import com.hartwig.healthchecker.io.dir.TestRunContextFactory;
import com.hartwig.healthchecker.result.BaseResult;
import com.hartwig.healthchecker.result.PatientResult;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class GermlineCheckerTest {

    private static final String RUN_DIRECTORY = Resources.getResource("checks/variants/run").getPath();
    private static final String RUN_DIRECTORY_V1_9 = Resources.getResource("checks/variants/run_v1_9").getPath();
    private static final String REF_SAMPLE = "sample1";
    private static final String TUMOR_SAMPLE = "sample2";

    private static final int EXPECTED_NUM_CHECKS = 2;
    private static final int EXPECTED_REF_SNPS = 55;
    private static final int EXPECTED_REF_INDELS = 4;
    private static final int EXPECTED_TUMOR_SNPS = 74;
    private static final int EXPECTED_TUMOR_INDELS = 4;

    private final GermlineChecker checker = new GermlineChecker();

    @Test
    public void canCountSNPAndIndels() throws IOException, HealthChecksException {
        final RunContext runContext = TestRunContextFactory.forTest(RUN_DIRECTORY, REF_SAMPLE, TUMOR_SAMPLE);
        final BaseResult result = checker.tryRun(runContext);

        Assert.assertEquals(CheckType.GERMLINE, result.getCheckType());
        final List<HealthCheck> refChecks = ((PatientResult) result).getRefSampleChecks();
        final List<HealthCheck> tumorChecks = ((PatientResult) result).getTumorSampleChecks();

        assertChecks(refChecks, EXPECTED_REF_SNPS, EXPECTED_REF_INDELS);
        assertChecks(tumorChecks, EXPECTED_TUMOR_SNPS, EXPECTED_TUMOR_INDELS);
    }

    @Test
    public void canCountSNPAndV1_9() throws IOException, HealthChecksException {
        final RunContext runContext = TestRunContextFactory.forTest(RUN_DIRECTORY_V1_9, REF_SAMPLE, TUMOR_SAMPLE);
        final PatientResult result = (PatientResult) checker.tryRun(runContext);
        assertEquals(EXPECTED_NUM_CHECKS, result.getRefSampleChecks().size());
        assertEquals(EXPECTED_NUM_CHECKS, result.getTumorSampleChecks().size());
    }

    @Test
    public void errorYieldsCorrectOutput() {
        final RunContext runContext = TestRunContextFactory.forTest(RUN_DIRECTORY, REF_SAMPLE, TUMOR_SAMPLE);
        final PatientResult result = (PatientResult) checker.errorRun(runContext);
        assertEquals(EXPECTED_NUM_CHECKS, result.getRefSampleChecks().size());
        assertEquals(EXPECTED_NUM_CHECKS, result.getTumorSampleChecks().size());
    }

    private static void assertChecks(@NotNull final List<HealthCheck> checks, final long expectedCountSNP,
            final long expectedCountIndels) {
        assertEquals(EXPECTED_NUM_CHECKS, checks.size());

        final Optional<HealthCheck> snpCheck = checks.stream().filter(
                check -> check.getCheckName().equals(GermlineCheck.VARIANTS_GERMLINE_SNP.toString())).findFirst();
        assert snpCheck.isPresent();
        assertEquals(Long.toString(expectedCountSNP), snpCheck.get().getValue());

        final Optional<HealthCheck> indelCheck = checks.stream().filter(
                check -> check.getCheckName().equals(GermlineCheck.VARIANTS_GERMLINE_INDELS.toString())).findFirst();
        assert indelCheck.isPresent();
        assertEquals(Long.toString(expectedCountIndels), indelCheck.get().getValue());
    }
}