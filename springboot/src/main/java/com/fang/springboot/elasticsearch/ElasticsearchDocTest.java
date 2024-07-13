package com.fang.springboot.elasticsearch;

import com.fang.springboot.user.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * @author shaobin
 * @date 2024/6/19 14:22
 */
public class ElasticsearchDocTest {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        User userDO = new User("01", "fang", 0, 20);
        ObjectMapper mapper = new ObjectMapper();
        String userJsonStr = mapper.writeValueAsString(userDO);
        // 新增文档
//        IndexRequest indexRequest = new IndexRequest();
//        indexRequest.index("user").id("1001");
//        indexRequest.source(userJsonStr, XContentType.JSON);
//        IndexResponse indexResponse = esClient.index(indexRequest, RequestOptions.DEFAULT);
//        System.out.println(indexResponse.getResult());
        // 批量新增
        BulkRequest addBulkRequest = new BulkRequest();
        for (int i = 0; i < 5; i++) {
            userDO.setId("00" + i);
            userDO.setName("fang00" + i);
            userDO.setAge(20 + i);
            userDO.setSex(i % 2);
            userJsonStr = mapper.writeValueAsString(userDO);
            addBulkRequest.add(new IndexRequest().index("user").id("100" + i).source(userJsonStr, XContentType.JSON));
        }
        BulkResponse addBulkResponse = esClient.bulk(addBulkRequest, RequestOptions.DEFAULT);
        System.out.println(addBulkResponse.getTook());
        System.out.println(addBulkResponse.getItems());
        // 批量删除
//        BulkRequest deleteBulkRequest = new BulkRequest();
//        for (int i = 0; i < 5; i++) {
//            deleteBulkRequest.add(new DeleteRequest().index("user").id("100" + i));
//        }
//        BulkResponse deleteBulkResponse = esClient.bulk(deleteBulkRequest, RequestOptions.DEFAULT);
//        System.out.println(deleteBulkResponse.getTook());
//        System.out.println(deleteBulkResponse.getItems());
        // 修改文档
//        UpdateRequest updateRequest = new UpdateRequest();
//        updateRequest.index("user").id("1001");
//        updateRequest.doc(XContentType.JSON, "sex", 1, "age", 18);
//        UpdateResponse updateResponse = esClient.update(updateRequest, RequestOptions.DEFAULT);
//        System.out.println(updateResponse.getResult());
        // 删除文档
//        DeleteRequest deleteRequest = new DeleteRequest();
//        deleteRequest.index("user").id("1001");
//        DeleteResponse deleteResponse = esClient.delete(deleteRequest, RequestOptions.DEFAULT);
//        System.out.println(deleteResponse.getResult());
        // id查询文档
//        GetRequest getRequest = new GetRequest();
//        getRequest.index("user").id("1001");
//        GetResponse getResponse = esClient.get(getRequest, RequestOptions.DEFAULT);
//        Map<String, Object> sourceMap = getResponse.getSource();
//        System.out.println(getResponse.getSourceAsString());
        // 条件查询文档
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        String[] include = {};
        String[] exclude = {"id"};
        searchRequest.source(
                new SearchSourceBuilder()
//                        .query(QueryBuilders.matchAllQuery()) //全量查询
//                        .sort("age", SortOrder.DESC).from(2).size(2) //分页
//                        .fetchSource(include, exclude) //排除、包含（隐藏）字段
//                        .query(QueryBuilders.boolQuery() //组合查询
//                                .should(QueryBuilders.matchQuery("age", 20))
//                                .should(QueryBuilders.matchQuery("age", 21)))
//                        .query(QueryBuilders.rangeQuery("age").gte(20).lte(22)) //范围查询
//                        .query(QueryBuilders.fuzzyQuery("name", "fang0").fuzziness(Fuzziness.TWO)) //模糊查询，fuzziness模糊性意思是差几个字符能够匹配
//                        .query(QueryBuilders.termQuery("name", "fang")) //精准匹配
//                        .highlighter(new HighlightBuilder().preTags("<font color='red'>").postTags("</font>").field("name")) //高亮显示
//                        .aggregation(AggregationBuilders.max("maxAgeAggName").field("age")) //聚合操作
                        .aggregation(AggregationBuilders.terms("ageGroup").field("age")) //分组查询
        );
        SearchResponse searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getTook());
        System.out.println(searchResponse.getHits().getTotalHits());
        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
        System.out.println(searchResponse);


    }
}
