package com.hartwig.hmftools.ecrfanalyser;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;

import org.junit.Test;

public class EcrfAnalysisApplicationTest {

    private static final String TEST_ECRF = Resources.getResource("example/ecrf.xml").getPath();
    private static final String CSV_OUT = "/Users/kduyvesteyn/hmf/tmp/ecrf.csv";

    @Test
    public void tryIt() throws FileNotFoundException, XMLStreamException {
        EcrfAnalysisApplication app = new EcrfAnalysisApplication(TEST_ECRF, CSV_OUT);

        List<String> patients = Lists.newArrayList();
        List<String> fields = Lists.newArrayList();
        app.generateCsv(patients, fields);
    }
}