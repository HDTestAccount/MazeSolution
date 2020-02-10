package application.utils;

import application.responses.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class JacsonParser extends PropertyNamingStrategy {
    private static ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(UPPER_CAMEL_CASE);

    private static ResponseFactory responseFactory = new ResponseFactory();

    public static BaseResponse responseParser(String valToParse) {
        BaseResponse response = responseFactory.getResponseTemplate(valToParse);
        try {
            response = objectMapper.readValue(valToParse, response.getClass());
        } catch (JsonProcessingException e) {
            System.out.println("Problem value is: " + valToParse);
            e.printStackTrace();
        }
        return response;
    }
}
