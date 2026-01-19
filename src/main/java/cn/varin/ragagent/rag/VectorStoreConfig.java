package cn.varin.ragagent.rag;


import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
/**
*
*/

public class VectorStoreConfig {

    @Resource
    private DocumentLoader documentLoader;
    // 注册VectorSotre



    /**
     * SimpleVectorStore基于内存读写的向量数据库
     * 注意：EmbeddingModel使用SpringAI的，不要使用alibaba的
     * @param environment
     * @return
     */
    @Bean
    VectorStore memorySimpleVectorStore(EmbeddingModel environment) {
        VectorStore  vectorStore = SimpleVectorStore.builder(environment).build();
        List<Document> documents = documentLoader.loadDocuments();
        vectorStore.add(documents);

        return vectorStore;

    }
}