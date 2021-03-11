package dev.mtage.storage.cache;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import dev.mtage.util.AssertUtil;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author mtage
 * @since 2019/9/3 21:16
 **/
public class CacheClient {
    private static final Logger errorLogger = LoggerFactory.getLogger("COMMON-ERROR");

    private static final int ONE = 1;

    private static final String COLON = ":";

    /**
     * redisson 客户端
     */
    private RedissonClient redissonClient;

    /**
     * 构造CacheClient
     *
     * @param redissonClient
     */
    public CacheClient(RedissonClient redissonClient) {
        Preconditions.checkNotNull(redissonClient, "redissonClient null");
        this.redissonClient = redissonClient;
    }

    /**
     * 数据放入缓存
     *
     * @param nameSpace  命名空间
     * @param key        缓存key
     * @param value      缓存value
     * @param timeToLive 过期时间 秒为单位
     */
    public void set(String nameSpace, String key, Serializable value, int timeToLive) {

        AssertUtil.checkNotBlank(nameSpace, "缓存nameSpace empty");
        AssertUtil.checkNotBlank(key, "缓存key empty");
        AssertUtil.checkNotNull(value, "缓存value null");
        AssertUtil.verify(timeToLive >= ONE, "过期时间必须>=1秒");
        try {
            redissonClient.getBucket(buildActualKey(nameSpace, key)).set(value, timeToLive, TimeUnit.SECONDS);
        } catch (Exception ex) {
            errorLogger.error("数据放入缓存异常 key {} value {} timeToLive {}", key, value, timeToLive);
        }
    }

    /**
     * 从缓存移除数据
     *
     * @param key 缓存key
     */
    public void remove(String nameSpace, String key) {

        AssertUtil.checkNotBlank(nameSpace, "缓存nameSpace empty");
        AssertUtil.checkNotBlank(key, "缓存key empty");

        try {
            redissonClient.getBucket(buildActualKey(nameSpace, key)).delete();
        } catch (Exception ex) {
            errorLogger.error("从缓存删除数据异常 key {}", key);
        }

    }

    /**
     * 从缓存获取数据
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T get(String nameSpace, String key) {

        AssertUtil.checkNotBlank(nameSpace, "缓存nameSpace empty");
        AssertUtil.checkNotBlank(key, "缓存key empty");

        try {
            return redissonClient.<T>getBucket(buildActualKey(nameSpace, key)).get();
        } catch (Exception ex) {
            errorLogger.error("从缓存获取数据异常 key {}", key);
        }
        return null;
    }

    /**
     * 从缓存获取数据 如果获取失败 从底层数据源回源,放入缓存
     * 放入本地缓存 redis
     *
     * @param key
     * @param callable
     * @param timeToLive
     * @param <T>
     * @return
     */
    public <T extends Serializable> T get(String nameSpace, String key, Callable<T> callable, int timeToLive) {

        AssertUtil.checkNotBlank(nameSpace, "缓存nameSpace empty");
        AssertUtil.checkNotBlank(key, "缓存key empty");
        AssertUtil.checkNotNull(callable, "callable null");
        AssertUtil.verify(timeToLive >= ONE, "过期时间必须>=1秒");

        T value = get(nameSpace, key);
        if (value != null) {
            return value;
        }

        try {
            value = callable.call();
            set(nameSpace, key, value, timeToLive);
            return value;
        } catch (Throwable ex) {
            Throwable rootCause = Throwables.getRootCause(ex);
            rootCause = rootCause == null ? ex : rootCause;
            throw new RuntimeException("回源异常 key" + key, rootCause);
        }
    }

    /**
     * 构造真实key
     *
     * @param nameSpace
     * @param key
     * @return
     */
    private String buildActualKey(String nameSpace, String key) {
        return Joiner.on(COLON).join(nameSpace, key);
    }

}
