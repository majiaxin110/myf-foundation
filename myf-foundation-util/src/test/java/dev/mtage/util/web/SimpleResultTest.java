package dev.mtage.util.web;

import dev.mtage.util.AssertUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mtage
 * @since 2020/9/3 15:54
 */
class SimpleResultTest {
    @Test
    void test() {
        SimpleResult<String> result = SimpleResult.success("result");
        assertNotNull(result);
        assertTrue(result.getSuccess());

        result = SimpleResult.fail("code", "msg");
        assertNotNull(result);
        assertFalse(result.getSuccess());
    }

    @Test
    void testAssertUtil() {
        AssertUtil.checkNotNull(SimpleResult.success("OK"), "result is null");
    }
}