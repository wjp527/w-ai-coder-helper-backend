package com.wjp.waicoderhelperbackend.ai.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 加载 RAG
 */
@Configuration
public class RagConfig {

    /**
     * 向量模型
     */
    @Resource
    private EmbeddingModel qwenEmbeddingModel;

    /**
     * 自定义向量存储【基于内存】
     */
    @Resource
    private EmbeddingStore<TextSegment> embeddingStore;

    /**
     * 为 AI Service 提供内容加载器
     * @return
     */
    @Bean
    public ContentRetriever contentRetriever() {
        // ------------------ RAG ------------------
        // 1.加载文件
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("src/main/resources/docs");
        // 2.文档切割：每个文档按照段落进行分割，最大 1000 个字符，每次最多重叠 200 个字符
        DocumentByParagraphSplitter documentByParagraphSplitter = new DocumentByParagraphSplitter(1000, 200);
        // 3.自定义文档加载器，把文档转换成向量并保存到向量数据库中
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                // 加载文档分割器 ，进行文档切割
                .documentSplitter(documentByParagraphSplitter)
                // 优化文档质量：为每个切割后的文件 TextSegment 添加文档名称作为辕信息
                .textSegmentTransformer(textSegment -> TextSegment.from(textSegment.metadata().getString("file_name") +
                        "\n" + textSegment.text(), textSegment.metadata()))
                // 使用向量模型
                .embeddingModel(qwenEmbeddingModel)
                // 保存向量到向量数据库中【存放切片好的文档】
                .embeddingStore(embeddingStore)
                .build();

        // 加载文档
        ingestor.ingest(documents);

        // 4.自定义内容加载器
        EmbeddingStoreContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                // 指定模型
                .embeddingModel(qwenEmbeddingModel)
                // 执行向量存储
                .embeddingStore(embeddingStore)
                // 指定结果最大数
                .maxResults(5) // 最多 5 条结果
                // 指定过滤阈值【可动态】
                .minScore(0.75) // 过滤分数小于 0.75 的结果
                .build();

        // 返回内容加载器
        return contentRetriever;
    }
}
