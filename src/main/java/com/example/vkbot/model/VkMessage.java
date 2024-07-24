package com.example.vkbot.model;

import lombok.Data;

@Data
public class VkMessage {
    private String access_token;
    private String v;
    private String user_id;
    private String message;
    private String random_id;
    private String peer_id;
}