package com.fang.springboot.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

/**
 * @author shaobin
 * @date 2024/6/19 11:25
 */
public class ElasticsearchIndexTest {
    public static void main(String[] args) throws Exception {
        // 创建ES客户端
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 创建索引
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("user");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        boolean createAcknowledged = createIndexResponse.isAcknowledged();
        System.out.println("索引操作相应状态：" + createAcknowledged);
        // 删除索引
//        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("user");
//        AcknowledgedResponse deleteIndexResponse = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
//        System.out.println(deleteIndexResponse.isAcknowledged());
        // 查询索引
        GetIndexRequest getIndexRequest = new GetIndexRequest("user");
        GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(getIndexResponse.getAliases());
        System.out.println(getIndexResponse.getMappings());
        System.out.println(getIndexResponse.getSettings());
        // 关闭ES客户端
        restHighLevelClient.close();
    }
}
