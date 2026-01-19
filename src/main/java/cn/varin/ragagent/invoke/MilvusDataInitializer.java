package cn.varin.ragagent.invoke;


import cn.varin.ragagent.rag.DocumentLoader;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.ai.document.Document;

import java.util.List;


@Component
@Slf4j
public class MilvusDataInitializer implements ApplicationRunner {

    @Resource
    private VectorStore milvusVectorStore;

    @Resource
    private DocumentLoader documentLoader;

    @Override
    public void run(ApplicationArguments args) {
        List<Document> documents = documentLoader.loadDocuments();

        if (documents.isEmpty()) {
            return;
        }

        milvusVectorStore.add(documents);
        log.info("Milvus initialized with documents: " + documents.size());
    }
}
