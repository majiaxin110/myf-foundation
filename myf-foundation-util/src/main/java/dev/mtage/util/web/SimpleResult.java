package dev.mtage.util.web;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用的API包装
 * TODO: 实现追踪id
 *
 * @author mtage
 * @since 2020/2/7 20:40
 */
@Data
public class SimpleResult<T> implements Serializable {
    private static final long serialVersionUID = -592650298251475150L;

    /**
     * 该errorCode旨在满足规范错误码的需要，与Http状态码并不冲突
     */
    private String errorCode;

    private String errorMsg;

    private Boolean success;

    private Date time = new Date();

    private T result;

    public static <T> SimpleResult<T> success(T result) {
        SimpleResult<T> simpleResult = new SimpleResult<>();
        simpleResult.setSuccess(true);
        simpleResult.setResult(result);
        return simpleResult;
    }

    public static <T> SimpleResult<T> fail(String errorCode, String errorMsg) {
        SimpleResult<T> simpleResult = new SimpleResult<>();
        simpleResult.setSuccess(false);
        simpleResult.setErrorCode(errorCode);
        simpleResult.setErrorMsg(errorMsg);
        return simpleResult;
    }

}
