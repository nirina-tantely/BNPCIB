package org.bnpcib.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bnpcib.exception.TechnicalException;
import org.bnpcib.model.ModelDto;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JsonUtils {

    private final static ObjectMapper JSON_MAPPER = new ObjectMapper();

    /**
     * Method allows to read jsonIs inputStream and convert it to object.
     *
     * @param jsonIs
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T extends ModelDto> T fromJson(final InputStream jsonIs, final Class<T> valueType) {
        try {
            return JSON_MAPPER.readValue(jsonIs, valueType);
        } catch (IOException e) {
            throw new TechnicalException("Error when reading json file! " + e.getMessage(), e);
        }
    }

    /**
     * Method allows to convert and write an object to json text
     *
     * @param obj
     * @param outputFile
     * @param <T>
     */
    public static <T extends ModelDto> void toJson(final T obj, File outputFile) {
        try {
            JSON_MAPPER.writeValue(outputFile, obj);
        } catch (IOException e) {
            throw new TechnicalException("Error when serializing json object! " + e.getMessage(), e);
        }
    }

}
