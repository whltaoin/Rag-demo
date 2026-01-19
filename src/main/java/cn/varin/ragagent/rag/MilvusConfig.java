package cn.varin.ragagent.rag;

import io.milvus.client.MilvusServiceClient;
import io.milvus.param.ConnectParam;
import io.milvus.param.MetricType;
import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.milvus.MilvusVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MilvusConfig {



    /**
     * Milvus Client
     */
    @Bean
    public MilvusServiceClient milvusServiceClient() {
        return new MilvusServiceClient(
                ConnectParam.newBuilder()
                        .withHost("localhost")
                        .withPort(19530)
                        .build()
        );
    }

    /**
     *  Milvus VectorStore
     */
    @Bean
    public VectorStore milvusVectorStore(
            MilvusServiceClient milvusServiceClient,
            EmbeddingModel embeddingModel) {

        return MilvusVectorStore.builder(milvusServiceClient, embeddingModel)
                .collectionName("rag_collection")
                .metricType(MetricType.COSINE)
                .initializeSchema(true)
                .build();
    }

}
