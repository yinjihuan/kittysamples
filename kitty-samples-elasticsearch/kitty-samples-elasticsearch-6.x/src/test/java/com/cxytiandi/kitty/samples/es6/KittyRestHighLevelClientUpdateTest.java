package com.cxytiandi.kitty.samples.es6;

import com.cxytiandi.kitty.common.json.JsonUtils;
import com.cxytiandi.kitty.db.elasticsearch.client.KittyRestHighLevelClient;
import com.cxytiandi.kitty.samples.es6.constant.EsConstant;
import com.cxytiandi.kitty.samples.es6.document.ArticleDocument;
import org.assertj.core.util.Lists;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * KittyRestHighLevelClient Update 单测
 *
 * @作者 尹吉欢
 * @个人微信 jihuan900
 * @微信公众号 猿天地
 * @GitHub https://github.com/yinjihuan
 * @作者介绍 http://cxytiandi.com/about
 * @时间 2020-04-26 23:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KittyRestHighLevelClientUpdateTest {

    @Autowired
    private KittyRestHighLevelClient kittyRestHighLevelClient;

    private ArticleDocument buildArticleDocument() {
        ArticleDocument articleDocument = new ArticleDocument();
        articleDocument.setId(1L);
        articleDocument.setHeat(100);
        articleDocument.setStatus(1);
        articleDocument.setTags(Lists.newArrayList("java"));
        articleDocument.setTextContent("我要学Java好不好");
        articleDocument.setTitle("Java怎么学啊");
        articleDocument.setType(1);
        articleDocument.setUserId(1L);
        return articleDocument;
    }

    @Test
    public void testUpdate() {
        ArticleDocument articleDocument = buildArticleDocument();
        UpdateResponse updateResponse = kittyRestHighLevelClient.update(EsConstant.ARTICLE_INDEX, EsConstant.DEFAULT_TYPE, articleDocument.getId().toString(), articleDocument);
        Assert.assertTrue(updateResponse.getResult() == DocWriteResponse.Result.UPDATED);
    }

    @Test
    public void testUpdate2() {
        String id = "1";
        Map<String, Object> articleDocument = new HashMap<>();
        articleDocument.put("title", "Java怎么学啊,666");
        UpdateResponse updateResponse = kittyRestHighLevelClient.update(EsConstant.ARTICLE_INDEX, EsConstant.DEFAULT_TYPE, id, articleDocument);
        Assert.assertTrue(updateResponse.getResult() == DocWriteResponse.Result.UPDATED);
    }

    @Test
    public void testUpdate3() {
        ArticleDocument articleDocument = buildArticleDocument();
        UpdateRequest updateRequest = new UpdateRequest(EsConstant.ARTICLE_INDEX, EsConstant.DEFAULT_TYPE, articleDocument.getId().toString());
        updateRequest.doc(JsonUtils.toJson(articleDocument), XContentType.JSON);

        UpdateResponse updateResponse = kittyRestHighLevelClient.update(updateRequest);
        Assert.assertTrue(updateResponse.getResult() == DocWriteResponse.Result.UPDATED);
    }

    @Test
    public void tetsUpdate4() {
        UpdateByQueryRequest request = new UpdateByQueryRequest(EsConstant.ARTICLE_INDEX);
        request.setQuery(new TermQueryBuilder("userId", 1));
        request.setScript(new Script("ctx._source['status']=1;"));

        kittyRestHighLevelClient.updateByQuery(request, RequestOptions.DEFAULT);
    }

    @Test
    public void testUpdate5() {
        Map<String, Object> document = new HashMap<>();
        document.put("title", "Java");
        document.put("status", 0);
        document.put("tags", Lists.newArrayList("JS", "CSS"));
        kittyRestHighLevelClient.updateByQuery(EsConstant.ARTICLE_INDEX, new TermQueryBuilder("userId", 1), document);
    }

}
