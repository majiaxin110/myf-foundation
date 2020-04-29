package dev.mtage.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mtage
 * @since 2020/4/29 19:21
 */
class AssertUtilTest {

    @Test
    void checkClass() {
        AssertUtil.checkClass(BigDecimal.valueOf(5.0), BigDecimal.class, "");
    }
}