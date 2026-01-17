package cn.varin.ragagent.controller;

import cn.varin.ragagent.app.AgentClient;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Resource
    private AgentClient agentClient;
    @GetMapping("/message")
    public String test(String content) {
      return   agentClient.getMessage(content, UUID.randomUUID().toString(),10);
    }

}
