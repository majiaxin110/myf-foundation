package dev.mtage.util;

import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import com.google.common.base.VerifyException;
import dev.mtage.error.CommonSysException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 断言工具类
 * 之所以不直接使用guava 的 preconditions，是因为其抛出的Exception各种各样，又无法添加errorCode
 * @author mtage
 * @since 2019/9/3 21:44
 **/
public class AssertUtil {
    /***
     * Ensures that an object reference passed as a parameter to the calling method is NOT null based on Guava.
     * @param obj an object reference
     * @param errorCode the exception code to use if the check fails
     * @param errorMessage the exception message to use if the check fails
     * @throws CommonSysException if {@code obj} is null, with code and message
     */
    public static <T> void checkNotNull(T obj, String errorCode, String errorMessage) {
        try {
            Preconditions.checkNotNull(obj, errorMessage);
        } catch (NullPointerException e) {
            throw new CommonSysException(errorCode, errorMessage, e);
        }
    }

    /***
     * Ensures that an object reference passed as a parameter to the calling method is NOT null based on Guava.
     * @param obj an object reference
     * @param errorMessage the exception message to use if the check fails
     * @throws CommonSysException if {@code obj} is null, with and message
     */
    public static <T> void checkNotNull(T obj, String errorMessage) {
        checkNotNull(obj, null, errorMessage);
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is null based on Guava.
     * @param obj an object reference
     * @param errorCode the exception code to use if the check fails
     * @param errorMessage the exception message to use if the check fails
     * @throws CommonSysException if {@code obj} is not null, with code and message
     */
    public static <T> void checkNull(T obj, String errorCode, String errorMessage) {
        try {
            Preconditions.checkArgument(Objects.isNull(obj), errorMessage);
        } catch (IllegalArgumentException e) {
            throw new CommonSysException(errorCode, errorMessage, e);
        }
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is null based on Guava.
     * @param obj an object reference
     * @param errorMessage the exception message to use if the check fails
     * @throws CommonSysException if {@code obj} is not null, with message
     */
    public static <T> void checkNull(T obj, String errorMessage) {
        checkNull(obj, null, errorMessage);
    }


    /**
     * Ensures that a string is not blank based Apache Commons.
     * @param str
     * @param errorCode
     * @param errorMessage
     * @throws CommonSysException if {@code obj} is blank, with code and message
     */
    public static void checkNotBlank(String str, String errorCode, String errorMessage) {
        if (StringUtils.isBlank(str)) {
            throw new CommonSysException(errorCode, errorMessage);
        }
    }

    /**
     * Ensures that a string is not blank based Apache Commons.
     * @param str
     * @param errorMessage
     * @throws CommonSysException if {@code obj} is blank, with message
     */
    public static void checkNotBlank(String str, String errorMessage) {

    }

    /**
     * Ensures that {@code expression} is {@code true}
     * @param expression
     * @param errorCode
     * @param errorMessage
     * @throws CommonSysException if {@code expression} is not {@code true}, with code and message
     */
    public static void verify(boolean expression, String errorCode, String errorMessage) {
        try {
            Verify.verify(expression, errorMessage);
        } catch (VerifyException e) {
            throw new CommonSysException(errorCode, errorMessage, e);
        }
    }

    /**
     * Ensures that {@code expression} is {@code true}
     * @param expression
     * @param errorMessage
     * @throws CommonSysException if {@code expression} is not {@code true}, with message
     */
    public static void verify(boolean expression, String errorMessage) {
        verify(expression, null, errorMessage);
    }

}
