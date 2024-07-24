package com.example.vkbot.service;
import com.example.vkbot.client.VkApiClient;
import com.example.vkbot.model.VkMessage;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class VkBotService {

    private final VkApiClient vkApiClient;

    public void handleEvent(JSONObject event) throws IOException {
        if ("message_new".equals(event.optString("type"))) {
            String userMessage = event.optJSONObject("object").optJSONObject("message").optString("text");
            VkMessage message = new VkMessage();
            message.setUser_id(event.optJSONObject("object").optJSONObject("message").optString("from_id"));
            message.setMessage("Вы сказали: " + userMessage);
            message.setRandom_id(String.valueOf(System.currentTimeMillis()));
            message.setPeer_id(event.optJSONObject("object").optJSONObject("message").optString("peer_id"));
            vkApiClient.sendMessage(message);
        }
    }
}
