package com.example.joycenterserver.global.s3;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cloud.aws")
public class AwsProperties {

    private String region;
    private Credentials credentials;
    private S3 s3;

    public String getRegion() {
        return region;
    }

    public String getAccessKey() {
        return credentials.accessKey;
    }

    public String getSecretKey() {
        return credentials.secretKey;
    }

    public String getBucket() {
        return s3.bucket;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void setS3(S3 s3) {
        this.s3 = s3;
    }

    public static class Credentials {
        private String accessKey;
        private String secretKey;

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }
    }

    public static class S3 {
        private String bucket;

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }
    }
}
