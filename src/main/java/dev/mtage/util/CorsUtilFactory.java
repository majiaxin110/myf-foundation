package dev.mtage.util;

/**
 * 对象存储相关
 * @author mtage
 * @since 2020/1/27 18:24
 */
public class CorsUtilFactory {
    private CorsUtil corsUtil;


    public static CorsUtil getInstance(String secretId, String secretKey, String bucketName) {
        return new CorsUtil();
    }

    public static class CorsUtil {
        private CorsUtil() {
        }

        public void init() {

        }
    }
}
