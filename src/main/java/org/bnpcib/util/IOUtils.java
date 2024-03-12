package org.bnpcib.util;

import org.bnpcib.exception.TechnicalException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOUtils {

    public static void validateInputFilePath(final String filePath) {
        if (null == filePath) {
            throw new TechnicalException("Input File path cannot be null!");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            throw new TechnicalException("Input File doesn't exist!");
        }
    }

    public static void validateOutputFilePath(final String filePath) {
        if (null == filePath) {
            throw new TechnicalException("Output File path cannot be null!");
        }
        File file = new File(filePath);
        Path parent = file.toPath().getParent();
        if (!Files.exists(parent)) {
            try {
                Files.createDirectory(parent);
                Files.createDirectories(parent);
            } catch (IOException e) {
                throw new TechnicalException("OutputFile not valid!" + e.getMessage(), e);
            }
        }
        if (!Files.exists(parent.resolve(file.getName()))) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new TechnicalException("Cannot create the outputFile!" + e.getMessage(), e);
            }
        }
    }
}
