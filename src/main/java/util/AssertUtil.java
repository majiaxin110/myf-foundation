package util;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.base.Verify;

/**
 * 大部分情况就是对Guava API的简单封装
 * @author mtage
 * @since 2019/9/3 21:44
 **/
public class AssertUtil {
    public static <T> T checkNotNull(T obj, String errorMessage) {
        return Preconditions.checkNotNull(obj, errorMessage);
    }

    public static String checkNotBlank(String str, String errorMessage) {
        if (Strings.isNullOrEmpty(str)) {
            throw new IllegalArgumentException(errorMessage);
        }
        return str;
    }

    public static void verify(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        Verify.verify(expression, errorMessageTemplate, errorMessageArgs);
    }

}
