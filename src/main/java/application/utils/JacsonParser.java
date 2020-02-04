package application.utils;

import application.responses.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class JacsonParser extends PropertyNamingStrategy {
    private static ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(UPPER_CAMEL_CASE);

    private static responseFactory factory = new responseFactory();

    public static baseResponse responseParser(String valToParse) {
        baseResponse state = factory.getResponseTemplate(valToParse);
        try {
            state = objectMapper.readValue(valToParse, state.getClass());
        } catch (JsonProcessingException e) {
            System.out.println("Problem value is: " + valToParse);
            e.printStackTrace();
        }
        return state;
    }
}
