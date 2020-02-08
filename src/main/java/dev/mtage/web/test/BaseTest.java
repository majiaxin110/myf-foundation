package dev.mtage.web.test;

import com.alibaba.fastjson.JSON;
import dev.mtage.web.SimpleResult;
import org.junit.Assert;

import java.util.List;

/**
 * @author mtage
 * @since 2020/2/8 12:10
 */
public class BaseTest {
    protected void checkResultAndOut(SimpleResult<?> result) {
        Assert.assertTrue(result.getSuccess());
        System.out.println(JSON.toJSONString(result, true));
    }

    protected void checkListAndOut(List<?> result) {
        Assert.assertTrue(result.size() > 0);
        System.out.println(JSON.toJSONString(result, true));
    }
}
