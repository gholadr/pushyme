package co.ghola.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;


/**
 * Created by macbook on 2/26/16.
 */
public  class PushyAPI {

    public static ObjectMapper mapper = new ObjectMapper();
    private static final Logger log = Logger.getLogger(PushyAPI.class.getName());

    public static synchronized void sendPush(PushyPushRequest req) throws Exception {

        try {
            APIKeys.getInstance();
            URL url = new URL(String.format("https://www.pushy.me/push?api_key=%s", APIKeys.PUSHY_API_KEY));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            // Convert API request to JSON
            String json = mapper.writeValueAsString(req);
            writer.write(json);
            writer.flush();
            writer.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                log.info("connection.getResponseCode():" + connection.getResponseCode());

            } else {
                log.info("connection.getResponseCode():" + connection.getResponseCode());
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            throw new Exception(e);
        }
    }
}
