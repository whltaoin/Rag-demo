package cn.varin.ragagent.app;


import cn.varin.ragagent.Advisors.LogAdvisor;
import cn.varin.ragagent.common.Prompt;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetrievalAdvisor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class AgentClient {

    private final ChatClient chatClient;


    public AgentClient(ChatClient.Builder chatClient) {
        this.chatClient = chatClient
                .defaultSystem(Prompt.SYSTEM_PROPERTY)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(new InMemoryChatMemory())      ,
                        //  // 添加自定Log义advisor
                        new LogAdvisor()

                )
                .build();
    }

    /**
     * 问答
     * @param content 用户提示词
     * @param chatId  随机id
     * @return
     */
    public String getMessage(String content,String chatId, Integer number) {
        ChatResponse chatResponse = this.chatClient.prompt()
                .user(content)
                .advisors(advisor -> advisor.param("chat_memory_conversation_id", chatId)

                        .param("chat_memory_response_size", number)).call().chatResponse();
        String text = chatResponse.getResult().getOutput().getText();
        log.info("text:{}", text);
        return text;
    }
}

