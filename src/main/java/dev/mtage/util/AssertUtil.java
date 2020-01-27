package dev.mtage.util;

import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import org.apache.commons.lang3.StringUtils;

/**
 * @author mtage
 * @since 2019/9/3 21:44
 **/
public class AssertUtil {
    public static <T> T checkNotNull(T obj, String errorMessage) {
        return Preconditions.checkNotNull(obj, errorMessage);
    }

    public static String checkNotBlank(String str, String errorMessage) {
        if (StringUtils.isBlank(str)) {
            throw new IllegalArgumentException(errorMessage);
        }
        return str;
    }

    public static void verify(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        Verify.verify(expression, errorMessageTemplate, errorMessageArgs);
    }

}
