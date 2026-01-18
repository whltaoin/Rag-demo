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
    // 普通上下文联系问答
    @GetMapping("/message")
    public String message(String content,String chatid) {
      return   agentClient.getMessage(content, chatid,10);
    }
    // 上下文问答+打印日志
    @GetMapping("/log")
    public String log(String content,String chatid) {
        return   agentClient.getMessagewAndLog(content, chatid,10);
    }

}
