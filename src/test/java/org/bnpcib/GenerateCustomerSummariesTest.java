package org.bnpcib;

import org.bnpcib.exception.TechnicalException;
import org.bnpcib.model.InputData;
import org.bnpcib.model.OutputData;
import org.bnpcib.service.BnpServiceFcty;
import org.bnpcib.util.JsonUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class GenerateCustomerSummariesTest {


    @Test
    public void readInputFileTest() {
        final InputStream is = GenerateCustomerSummariesTest.class.getClassLoader().getResourceAsStream("CandidateInputExample.txt");
        InputData input = JsonUtils.fromJson(is, InputData.class);
        Assert.assertNotNull(input);
    }

    @Test
    public void generateSummariesTest() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(GenerateCustomerSummariesTest.class.getClassLoader().getResource("CandidateInputExample.txt")).getFile());
        String filePath = inputFile.getAbsolutePath();
        File outputFile = new File(Objects.requireNonNull(GenerateCustomerSummariesTest.class.getClassLoader().getResource("OutPut.txt")).getFile());
        String outputFilePath = outputFile.getAbsolutePath();
        BnpServiceFcty.get().getService().generateCustomersSummariesTrips(filePath, outputFilePath);
        OutputData outputData = JsonUtils.fromJson(new FileInputStream(outputFile), OutputData.class);
        Assert.assertNotNull(outputData);
    }

    @Test
    public void generateSummariesInvalidInputTest() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(GenerateCustomerSummariesTest.class.getClassLoader().getResource("InvalidInputTest.txt")).getFile());
        String filePath = inputFile.getAbsolutePath();
        File outputFile = new File(Objects.requireNonNull(GenerateCustomerSummariesTest.class.getClassLoader().getResource("OutPut.txt")).getFile());
        String outputFilePath = outputFile.getAbsolutePath();

        Exception exception = Assert.assertThrows(TechnicalException.class, () -> {
            BnpServiceFcty.get().getService().generateCustomersSummariesTrips(filePath, outputFilePath);
        });
        Assert.assertTrue(exception.getMessage().contains("Input data of tap is not correct: customerId or station name or time of tap cannot be null!"));
    }

    @Test
    public void entryAndExitOnTheSameStationTest() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(GenerateCustomerSummariesTest.class.getClassLoader().getResource("TestEntryAndExitOnTheStation.txt")).getFile());
        String filePath = inputFile.getAbsolutePath();
        File outputFile = new File(Objects.requireNonNull(GenerateCustomerSummariesTest.class.getClassLoader().getResource("OutPut.txt")).getFile());
        String outputFilePath = outputFile.getAbsolutePath();
        BnpServiceFcty.get().getService().generateCustomersSummariesTrips(filePath, outputFilePath);
        OutputData outputData = JsonUtils.fromJson(new FileInputStream(outputFile), OutputData.class);
        Assert.assertNotNull(outputData);
        Assert.assertEquals(0, outputData.getCustomerSummaries().get(0).getTotalCostInCents());
    }


}
