package by.yason.maze.utils.webWork;

import okhttp3.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class WebRQ {
    private static String mazeUrl = "";
    private final OkHttpClient httpClient = new OkHttpClient();

    public WebRQ() {
        String fileName = "src/main/resources/setting.properties";
        File file = new File(Paths.get(fileName).toString());
        if (!file.exists()) {
            fileName = "resources\\setting.properties";
        }

        try (InputStream input = new FileInputStream(Paths.get(fileName).toFile())) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            mazeUrl = prop.getProperty("url");
            // get the property value and print it out
            System.out.println(mazeUrl);
        } catch (IOException ex) {
            System.out.println(fileName);
            ex.printStackTrace();
        }
    }

    /**
     * @param endPointValue endpoint to send request
     * @return result of request (String)
     * @throws Exception
     */
    public String sendGet(String endPointValue) throws Exception {
        Request request = new Request.Builder()
                .url(mazeUrl + endPointValue)
                .addHeader("Accept-Encoding", "gzip,deflate")  // add request headers
                .addHeader("Connection", "Keep-Alive")
                .build();
        String httpRequestResponce = "";
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            // Get response body
            httpRequestResponce = response.body().string();
        }
        return httpRequestResponce;
    }

    /**
     * @param endPointValue endpoint to send request
     * @param requestBody   request (json)
     * @throws Exception
     */
    public void sendPost(String endPointValue, String requestBody) throws Exception {
        Request request = new Request.Builder()
                .url(mazeUrl + endPointValue)
                .addHeader("Accept-Encoding", "gzip,deflate")  // add request headers
                .addHeader("Connection", "Keep-Alive")
                .addHeader("Content-Type", "by.yason.maze.application/json")
                .post(RequestBody.create(requestBody, MediaType.parse("by.yason.maze.application/json")))
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        }
    }
}