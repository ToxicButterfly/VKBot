package com.example.vkbot.client;
import com.example.vkbot.model.VkMessage;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class VkApiClient {

    @Value("${vk.api.token}")
    private String API_TOKEN;
    @Value("${vk.api.version}")
    private String VK_API_VERSION;

    private static final String VK_API_URL = "https://api.vk.com/method/messages.send";

    public void sendMessage(VkMessage message) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(VK_API_URL);

        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("peer_id", message.getPeer_id()));
        params.add(new BasicNameValuePair("message", message.getMessage()));
        params.add(new BasicNameValuePair("random_id", message.getRandom_id()));
        params.add(new BasicNameValuePair("access_token", API_TOKEN));
        params.add(new BasicNameValuePair("v", VK_API_VERSION));

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
        httpPost.setEntity(entity);

        // Send the request
        HttpResponse response = httpClient.execute(httpPost);

        // Print the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

        System.out.println("Response: " + result.toString());

        // Close the HttpClient
        httpClient.close();

    }
}
