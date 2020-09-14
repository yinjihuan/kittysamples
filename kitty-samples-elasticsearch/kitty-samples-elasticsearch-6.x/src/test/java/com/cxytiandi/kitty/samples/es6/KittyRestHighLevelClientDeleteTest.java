package com.cxytiandi.kitty.samples.es6;

import com.cxytiandi.kitty.db.elasticsearch.client.KittyRestHighLevelClient;
import com.cxytiandi.kitty.samples.es6.constant.EsConstant;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * KittyRestHighLevelClient Delete 单测
 *
 * @作者 尹吉欢
 * @个人微信 jihuan900
 * @微信公众号 猿天地
 * @GitHub https://github.com/yinjihuan
 * @作者介绍 http://cxytiandi.com/about
 * @时间 2020-04-26 23:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KittyRestHighLevelClientDeleteTest {

    @Autowired
    private KittyRestHighLevelClient kittyRestHighLevelClient;

    @Test
    public void testDelete() {
        DeleteResponse deleteResponse = kittyRestHighLevelClient.delete(EsConstant.ARTICLE_INDEX, EsConstant.DEFAULT_TYPE, "1");
        Assert.assertTrue(deleteResponse.getResult() == DocWriteResponse.Result.DELETED);
    }

    @Test
    public void testDelete2() {
        DeleteRequest deleteRequest = new DeleteRequest(EsConstant.ARTICLE_INDEX, EsConstant.DEFAULT_TYPE, "1");
        DeleteResponse deleteResponse = kittyRestHighLevelClient.delete(deleteRequest);
        Assert.assertTrue(deleteResponse.getResult() == DocWriteResponse.Result.DELETED);
    }

}
