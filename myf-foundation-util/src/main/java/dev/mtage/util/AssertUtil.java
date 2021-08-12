package dev.mtage.util;

import dev.mtage.error.CommonSysException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * 断言工具类
 * 之所以不直接使用 guava 的 preconditions，是因为其抛出的Exception各种各样，又无法添加errorCode
 *
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
        checkNotNull(obj, errorCode, errorMessage, null);
    }

    /***
     * Ensures that an object reference passed as a parameter to the calling method is NOT null based on Guava.
     * @param obj an object reference
     * @param errorCode the exception code to use if the check fails
     * @param errorMessage the exception message to use if the check fails
     * @param preAction the action need to execute before throwing the exception
     * @throws CommonSysException if {@code obj} is null, with code and message
     */
    public static <T> void checkNotNull(T obj, String errorCode, String errorMessage, Consumer<CommonSysException> preAction) {
        if (obj == null) {
            throwSysException(errorCode, errorMessage, preAction);
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
     * Ensures that an object reference passed as a parameter to the calling method is null
     *
     * @param obj          an object reference
     * @param errorCode    the exception code to use if the check fails
     * @param errorMessage the exception message to use if the check fails
     * @param preAction    the action need to execute before throwing the exception
     * @throws CommonSysException if {@code obj} is not null, with code and message
     */
    public static <T> void checkNull(T obj, String errorCode, String errorMessage, Consumer<CommonSysException> preAction) {
        if (obj != null) {
            throwSysException(errorCode, errorMessage, preAction);
        }
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is null.
     *
     * @param obj          an object reference
     * @param errorCode    the exception code to use if the check fails
     * @param errorMessage the exception message to use if the check fails
     * @throws CommonSysException if {@code obj} is not null, with code and message
     */
    public static <T> void checkNull(T obj, String errorCode, String errorMessage) {
        checkNull(obj, errorCode, errorMessage, null);
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is null.
     *
     * @param obj          an object reference
     * @param errorMessage the exception message to use if the check fails
     * @throws CommonSysException if {@code obj} is not null, with message
     */
    public static <T> void checkNull(T obj, String errorMessage) {
        checkNull(obj, null, errorMessage);
    }

    /**
     * Ensures that a string is not blank based Apache Commons.
     *
     * @param str
     * @param errorCode
     * @param errorMessage
     * @param preAction    the action need to execute before throwing the exception
     * @throws CommonSysException if {@code obj} is blank, with code and message
     */
    public static void checkNotBlank(String str, String errorCode, String errorMessage, Consumer<CommonSysException> preAction) {
        if (StringUtils.isBlank(str)) {
            throwSysException(errorCode, errorMessage, preAction);
        }
    }

    /**
     * Ensures that a string is not blank based Apache Commons.
     *
     * @param str
     * @param errorCode
     * @param errorMessage
     * @throws CommonSysException if {@code obj} is blank, with code and message
     */
    public static void checkNotBlank(String str, String errorCode, String errorMessage) {
        checkNotBlank(str, errorCode, errorMessage, null);
    }

    /**
     * Ensures that a string is not blank based Apache Commons.
     *
     * @param str
     * @param errorMessage
     * @throws CommonSysException if {@code obj} is blank, with message
     */
    public static void checkNotBlank(String str, String errorMessage) {
        checkNotBlank(str, null, errorMessage);
    }


    /**
     * Ensures that a string is not empty based Apache Commons.
     *
     * @param str
     * @param errorCode
     * @param errorMessage
     * @param preAction    the action need to execute before throwing the exception
     * @throws CommonSysException if {@code obj} is blank, with code and message
     */
    public static void checkNotEmpty(String str, String errorCode, String errorMessage, Consumer<CommonSysException> preAction) {
        if (StringUtils.isEmpty(str)) {
            throwSysException(errorCode, errorMessage, preAction);
        }
    }

    /**
     * Ensures that a string is not empty based Apache Commons.
     *
     * @param str
     * @param errorMessage
     * @throws CommonSysException if {@code obj} is blank, with message
     */
    public static void checkNotEmpty(String str, String errorMessage) {
        checkNotEmpty(str, null, errorMessage);
    }

    /**
     * Ensures that a string is not empty based Apache Commons.
     *
     * @param str
     * @param errorCode
     * @param errorMessage
     * @throws CommonSysException if {@code obj} is blank, with code and message
     */
    public static void checkNotEmpty(String str, String errorCode, String errorMessage) {
        checkNotEmpty(str, errorCode, errorMessage, null);
    }

    /**
     * Ensures that two object reference passed are equals
     *
     * @param objA
     * @param objB
     * @param errorCode
     * @param errorMessage
     * @param preAction    the action need to execute before throwing the exception
     */
    public static void checkEquals(Object objA, Object objB, String errorCode, String errorMessage,
                                   Consumer<CommonSysException> preAction) {
        if (!Objects.equals(objA, objB)) {
            throwSysException(errorCode, errorMessage, preAction);
        }
    }

    /**
     * Ensures that two object reference passed are equals
     *
     * @param objA
     * @param objB
     * @param errorCode
     * @param errorMessage
     */
    public static void checkEquals(Object objA, Object objB, String errorCode, String errorMessage) {
        checkEquals(objA, objB, errorCode, errorMessage, null);
    }

    /**
     * Ensures that two object reference passed are equals
     *
     * @param objA
     * @param objB
     * @param errorMessage
     */
    public static void checkEquals(Object objA, Object objB, String errorMessage) {
        checkEquals(objA, objB, null, errorMessage);
    }

    /**
     * Ensures that an object is instance of a class
     *
     * @param obj
     * @param type
     */
    public static void checkClass(Object obj, Class<?> type, String errorCode, String errorMessage,
                                  Consumer<CommonSysException> preAction) {
        if (!obj.getClass().equals(type)) {
            throwSysException(errorCode, errorMessage, preAction);
        }
    }

    /**
     * Ensures that an object is instance of a class
     *
     * @param obj
     * @param type
     */
    public static void checkClass(Object obj, Class<?> type, String errorCode, String errorMessage) {
        checkClass(obj, type, errorCode, errorMessage, null);
    }

    /**
     * Ensures that an object is instance of a class
     *
     * @param obj
     * @param type
     */
    public static void checkClass(Object obj, Class<?> type, String errorCode) {
        checkClass(obj, type, null, errorCode);
    }

    /**
     * Ensures that {@code expression} is {@code true}
     *
     * @param expression
     * @param errorCode
     * @param errorMessage
     * @param preAction    the action need to execute before throwing the exception
     * @throws CommonSysException if {@code expression} is not {@code true}, with code and message
     */
    public static void verify(boolean expression, String errorCode, String errorMessage, Consumer<CommonSysException> preAction) {
        if (!expression) {
            throwSysException(errorCode, errorMessage, preAction);
        }
    }

    /**
     * Ensures that {@code expression} is {@code true}
     *
     * @param expression
     * @param errorCode
     * @param errorMessage
     * @throws CommonSysException if {@code expression} is not {@code true}, with code and message
     */
    public static void verify(boolean expression, String errorCode, String errorMessage) {
        verify(expression, errorCode, errorMessage, null);
    }

    /**
     * Ensures that {@code expression} is {@code true}
     *
     * @param expression
     * @param errorMessage
     * @throws CommonSysException if {@code expression} is not {@code true}, with message
     */
    public static void verify(boolean expression, String errorMessage) {
        verify(expression, null, errorMessage);
    }

    private static void throwSysException(String errorCode, String errorMessage, Consumer<CommonSysException> preAction) {
        CommonSysException sysException = new CommonSysException(errorCode, errorMessage);
        if (Objects.nonNull(preAction)) {
            preAction.accept(sysException);
        }
        throw sysException;
    }

}
