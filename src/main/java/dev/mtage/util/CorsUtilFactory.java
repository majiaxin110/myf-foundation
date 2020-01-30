package dev.mtage.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 对象存储相关 目前仅支持腾讯云
 *
 * @author mtage
 * @since 2020/1/27 18:24
 */
public class CorsUtilFactory {
    private CorsUtil corsUtil;

    /**
     * 获取对象存储工具实例
     *
     * @param secretId
     * @param secretKey
     * @param bucketName
     * @return
     * @throws IOException 存储桶未找到
     */
    public static CorsUtil getInstance(String secretId, String secretKey, String bucketName) throws IOException {
        return new CorsUtil(secretId, secretKey, bucketName);
    }

    public static class CorsUtil {
        private String secretId;
        private String secretKey;
        private String bucketName;
        /**
         * cos客户端
         */
        public COSClient cosClient;

        public Bucket goalBucket;

        public CorsUtil(String secretId, String secretKey, String bucketName) throws IOException {
            this.secretId = secretId;
            this.secretKey = secretKey;
            this.bucketName = bucketName;
            init();
        }

        private CorsUtil() {
        }

        public void init() throws IOException {
            COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
            Region region = new Region("ap-shanghai");
            ClientConfig clientConfig = new ClientConfig(region);
            clientConfig.setHttpProtocol(HttpProtocol.https);

            cosClient = new COSClient(cred, clientConfig);

            List<Bucket> allBucket = cosClient.listBuckets();
            goalBucket = allBucket.stream().filter(eachBucket -> eachBucket.getName().equals(bucketName))
                    .findFirst().orElseThrow(() -> new IOException("无目标存储桶 " + bucketName));
        }

        /**
         * 上传文件 将根据文件名和当前时间自动生成键
         *
         * @param file
         * @return 文件键值
         * @throws CosClientException
         */
        public String uploadFile(File file) {
            // 获得存储桶
            String[] nameSplits = file.getName().split("\\.");
            String key = file.getName().substring(0, file.getName().lastIndexOf(".")) + "-" + new DateTime().toString("yyyy_MM_dd") +
                    "." + nameSplits[nameSplits.length - 1];
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            return key;
        }

        /**
         * 指定文件名 自动删除旧的文件
         *
         * @param fileName
         * @param days     保留的最近天数
         * @return
         */
        public int autoDeleteFiles(String fileName, int days) {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
            // 设置 bucket 名称
            listObjectsRequest.setBucketName(bucketName);
            // prefix 表示列出的 object 的 key 以 prefix 开始
            listObjectsRequest.setPrefix(fileName.split("\\.")[0]);
            // 设置最大遍历出多少个对象, 一次 listobject 最大支持1000
            listObjectsRequest.setMaxKeys(1000);
            listObjectsRequest.setDelimiter("/");
            ObjectListing objectListing = cosClient.listObjects(listObjectsRequest);
            List<String> deletedKeys = new ArrayList<>();
            for (COSObjectSummary cosObjectSummary : objectListing.getObjectSummaries()) {
                // 对象的路径 key
                String key = cosObjectSummary.getKey();
                DateTime modifiedDT = new DateTime(cosObjectSummary.getLastModified());
                DateTime presentDT = new DateTime();
                Duration duration = new Duration(modifiedDT, presentDT);
                if (duration.isLongerThan(Duration.standardDays(days))) {
                    deletedKeys.add(key);
                }
            }
            deletedKeys.forEach(this::deleteFile);
            return deletedKeys.size();
        }

        /**
         * 指定键值删除文件
         *
         * @param key
         * @throws CosClientException
         */
        public void deleteFile(String key) {
            cosClient.deleteObject(bucketName, key);
        }

        public void shutdown() {
            cosClient.shutdown();
        }
    }
}
