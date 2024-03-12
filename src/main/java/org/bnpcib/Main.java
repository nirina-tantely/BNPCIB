package org.bnpcib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bnpcib.exception.TechnicalException;
import org.bnpcib.service.BnpServiceFcty;
import org.bnpcib.util.IOUtils;

import java.util.Arrays;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static String INPUT_FILE_PATH = null;
    private static String OUT_FILE_PATH = null;

    public static void main(String[] args) {

        LOGGER.info("----------------- GENERATING CUSTOMER TRIPS SUMMARIES ---------------------------");
        //Check and get parameters
        checkParameters(args);
        BnpServiceFcty.get().getService().generateCustomersSummariesTrips(INPUT_FILE_PATH, OUT_FILE_PATH);
        LOGGER.info("The output file result is successfully saved in : " + OUT_FILE_PATH);
        LOGGER.info("----------------- END OF THE PROGRAM ---------------------------");
    }

    private static void checkParameters(String[] args) {
        if (args.length < 2)
            throw new TechnicalException("Missing parameters! InputFile and OutputFile paths are expected as parameters!");
        INPUT_FILE_PATH = args[0];
        OUT_FILE_PATH = args[1];
        LOGGER.info("Checking parameters ...");
        IOUtils.validateInputFilePath(INPUT_FILE_PATH);
        IOUtils.validateOutputFilePath(OUT_FILE_PATH);
        LOGGER.info("Running the program with parameter: " + Arrays.toString(args));
    }
}