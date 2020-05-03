package dev.mtage.util.web.test;

import com.alibaba.fastjson.JSON;
import dev.mtage.util.web.SimpleResult;
import org.junit.jupiter.api.Assertions;

import java.util.List;


/**
 * @author mtage
 * @since 2020/2/8 12:10
 */
public class BaseTest {
    protected void checkResultAndOut(SimpleResult<?> result) {
        Assertions.assertTrue(result.getSuccess());
        System.out.println(JSON.toJSONString(result, true));
    }

    protected void checkListAndOut(List<?> result) {
        Assertions.assertTrue(result.size() > 0);
        System.out.println(JSON.toJSONString(result, true));
    }
}
