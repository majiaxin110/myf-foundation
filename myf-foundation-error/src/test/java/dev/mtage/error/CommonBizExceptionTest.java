package dev.mtage.error;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mtage
 * @since 2020/9/3 14:51
 */
class CommonExceptionTest {
    @Test
    void testBizException() {
        String msg = "exception message";
        String code = "CODE_001";
        CommonBizException bizException = new CommonBizException(code, msg);
        assertNotNull(bizException);
        assertEquals(code, bizException.getErrorCode());

        System.out.println("Done");
    }
}