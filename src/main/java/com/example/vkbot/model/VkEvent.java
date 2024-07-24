package com.example.vkbot.model;
import lombok.Data;

@Data
public class VkEvent {
    private String type;
    private VkObject object;

    @Data
    public static class VkObject {
        private String user_id;
        private String body;
    }
}
