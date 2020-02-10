package application.webWork;

import okhttp3.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class WebRQ {
    private final OkHttpClient httpClient = new OkHttpClient();
    private static String mazeUrl = "";

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

    public void sendPost(String endPointValue, String endPointRequest) throws Exception {
        Request request = new Request.Builder()
                .url(mazeUrl + endPointValue)
                .addHeader("Accept-Encoding", "gzip,deflate")  // add request headers
                .addHeader("Connection", "Keep-Alive")
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(endPointRequest, MediaType.parse("application/json")))
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        }
    }
}