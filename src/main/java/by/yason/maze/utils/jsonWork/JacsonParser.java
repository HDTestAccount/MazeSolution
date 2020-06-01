package by.yason.maze.utils.jsonWork;

import by.yason.maze.data.responses.BaseResponse;
import by.yason.maze.data.responses.ResponseFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class JacsonParser extends PropertyNamingStrategy {
    private static ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(UPPER_CAMEL_CASE);

    private static ResponseFactory responseFactory = new ResponseFactory();

    /**
     * parsing json response to object
     *
     * @param valToParse String(json) answer from web-service
     * @return response object
     */
    public static BaseResponse responseParser(String valToParse) {
        BaseResponse response = responseFactory.getResponseTemplateByType(valToParse);
        try {
            response = objectMapper.readValue(valToParse, response.getClass());
        } catch (JsonProcessingException e) {
            System.out.println("Problem value is: " + valToParse);
            e.printStackTrace();
        }
        return response;
    }
}
