package com.example.vkbot.controller;
import com.example.vkbot.model.VkEvent;
import com.example.vkbot.service.VkBotService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class BotController {

    private final VkBotService vkBotService;
    @Value("${vk.api.confirmation.code}")
    private String VK_API_CONFIRMATION_CODE;

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String str) {
        try {
            JSONObject event = new JSONObject(str);
            String type = event.optString("type");
            if (type.equals("confirmation")) {
                return ResponseEntity.ok(VK_API_CONFIRMATION_CODE);
            }
            vkBotService.handleEvent(new JSONObject(str));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal Server Error");
        }
        return ResponseEntity.ok("ok");
    }

//    @PostMapping("/webhook")
//    public ResponseEntity<String> handlePost(@RequestBody String body) {
////        JSONObject request = new JSONObject(body);
////        System.out.println(request);
//
//        return ResponseEntity.ok("5de96367");
//    }
}
