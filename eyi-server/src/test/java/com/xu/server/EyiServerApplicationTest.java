package com.xu.server;

import com.xu.commons.utils.TikaUtils;
import com.xu.server.admin.user.pojo.entities.EyiUser;
import com.xu.server.admin.user.repository.UserInfoRepository;
import com.xu.server.base.util.QueryBuilderUtil;
import com.xu.server.base.util.RedisUtils;
import com.xu.server.email.pojo.EmailInfo;
import com.xu.server.email.service.EmailService;
import com.xu.server.storage.utils.FdfsFileUtil;
import com.xu.server.storage.utils.MinioUtils;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.ObjectLockConfiguration;
import io.minio.messages.RetentionDurationDays;
import io.minio.messages.RetentionMode;
import io.minio.messages.VersioningConfiguration;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/22 15:20
 */
@Slf4j
@SpringBootTest
public class EyiServerApplicationTest {
    @Autowired
    private UserInfoRepository repository;
    @Test
    void test() {
        EyiUser user = new EyiUser();
        user.setId(518605471188258816L);
        Specification<EyiUser> specification = QueryBuilderUtil.specificationBuild(user);
        List<EyiUser> all = repository.findAll(specification);
        System.out.println(all);
    }

    @Test
    void test2() {
//        Page<EyiUser> all = repository.findAll(PageRequest.of(0,10));
        int del = repository.logicDelById(518605471188258816L);
        System.out.println("del "+ del);
        EyiUser all = repository.findById(518605471188258816L).orElse(null);

        System.out.println(all);
    }

    @Test
    void testAdd() {
        EyiUser user = new EyiUser();
        user.setUsername("测试");
        user.setPassword("132131231332");
        user.setEmail("111111111111");
//        user.setGender(GenderEnum.MALE.gender);
        user.setIntroduce("1111");
        user.setExpire(false);
        user.setLocked(false);
        user.setDelFlag(0);
        user.setCreateTime(LocalDateTime.now());
        user.setDeleteTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setCreateId(0L);
        user.setDeleteId(0L);
        user.setUpdateId(0L);
        repository.save(user);
    }

//    @Autowired
//    private StorageClient client;

    @Test
    void testUpload() {
//        try {
//            NameValuePair[] nvp = new NameValuePair[]{
//                    new NameValuePair("age", "18"),
//                    new NameValuePair("sex", "male"),
//            };
//            String[] pngs = client.upload_file("C:\\Users\\xu\\Pictures\\test.png", "png", nvp);
//            System.out.println(Arrays.toString(pngs));
//        } catch (IOException | MyException e) {
//            e.printStackTrace();
//        }
        String[] uploadFile = FdfsFileUtil.uploadFile("C:\\Users\\xu\\Pictures\\test.png");
        System.out.println(Arrays.toString(uploadFile));
    }

//    @Test
//    void download() {
//        try {
//            byte[] bytes = client.download_file("group1", "M00/00/00/cnWlgmJdICiAC4C7AAAJioD2mYI625.png");
//            FileOutputStream fos = new FileOutputStream("C:\\Users\\xu\\Pictures\\download.png");
//            fos.write(bytes);
//        } catch (IOException | MyException e) {
//            e.printStackTrace();
//        }
//    }

//    @Autowired
//    private FdfsFileServiceImpl impl;

//    @Test
//    void test5() {
////        String save = impl.save("C:\\Users\\xu\\Pictures\\test.png");
////        System.out.println(save);
//        File file = new File("C:\\Users\\xu\\Pictures\\test.png");
//        impl.save(file);
//    }

//    @Test
//    void testDelete() throws MyException {
//        String storage = "group1/M00/00/00/cnWlgmJdFYeADXvhAAAJioD2mYI104.png";
//        try {
//            int group1 = client.delete_file("group1", "M00/00/00/cnWlgmJdICiAC4C7AAAJioD2mYI625.png");
//            System.out.println("group 1 = "+ group1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    void test21() {
        System.out.println(TikaUtils.getContentTypeByExtension("txt"));
    }


    @Autowired
    private EmailService es;
    @Test
    void test22() {
        Map<String, Object> params = new HashMap<>();
        es.sendMsg(params, new EmailInfo("1254226073@qq.com", "测试"), "email/TestTemplate.ftlh");
    }


    @Test
    void test23() {
        MinioClient client =
                MinioClient.builder()
                        .endpoint("http://localhost:19099")
                        .credentials("admin", "@eyi0524")
                        .build();
        try {
            // 判断是桶是否存在
            BucketExistsArgs args = BucketExistsArgs.builder().bucket("ces").build();
            boolean bucketExists = client.bucketExists(args);
            if (!bucketExists) {
                // 创建桶
                MakeBucketArgs buildArgs = MakeBucketArgs.builder()
                        .bucket("ces").objectLock(true)
                        .build();
                client.makeBucket(buildArgs);
            }

            // 设置桶标签
            Map<String, String > tags = new HashMap<>();
            tags.put("title", "测试");
            tags.put("updateTime", LocalDateTime.now().toString());
            SetBucketTagsArgs tagsArgs = SetBucketTagsArgs.builder()
                    .bucket("ces")
                    .tags(tags)
                    .build();
            client.setBucketTags(tagsArgs);
            // 设置桶版本
            VersioningConfiguration versionConfig = new VersioningConfiguration(VersioningConfiguration.Status.ENABLED, true);
            SetBucketVersioningArgs versioningArgs = SetBucketVersioningArgs.builder()
                    .bucket("ces")
                    .config(versionConfig)
                    .build();
            client.setBucketVersioning(versioningArgs);
            // 设置Object-Lock https://docs.min.io/minio/baremetal/object-retention/minio-object-locking.html
            // 锁定的对象 只有超过设定的日期 才会真正删除
            ObjectLockConfiguration objectLockConfiguration =
                    new ObjectLockConfiguration(RetentionMode.COMPLIANCE, new RetentionDurationDays(1));
            SetObjectLockConfigurationArgs objectRetentionArgs = SetObjectLockConfigurationArgs.builder()
                    .config(objectLockConfiguration).bucket("ces").build();
            client.setObjectLockConfiguration(objectRetentionArgs);

            // 删除桶
//            RemoveBucketArgs removeArgs = RemoveBucketArgs.builder()
//                    .bucket("ces")
//                    .build();
//            client.removeBucket(removeArgs);

        } catch (ErrorResponseException | InsufficientDataException | InternalException | XmlParserException | ServerException | NoSuchAlgorithmException | IOException | InvalidResponseException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Test
    void test24() {
//        System.out.println(MinioUtils.getObjectUrl());
//        FileInputStream fis = new FileInputStream("D:\\env\\xu\\eyi-parent\\eyi-server\\src\\main\\resources\\application.yml");
//        MinioUtils.uploadObject("ces", "application.yml", fis);
//        System.out.println(MinioUtils.getObjectUrl("ces", "test.png", -1, TimeUnit.DAYS));
        MinioClient client =
                MinioClient.builder()
                        .endpoint("http://localhost:19099")
                        .credentials("admin", "@eyi0524")
                        .build();
        try {
            String config = "{\n" +
                    "    \"Version\":\"2012-10-17\",\n" +
                    "    \"Statement\":[\n" +
                    "        {\n" +
                    "            \"Effect\":\"Allow\",\n" +
                    "            \"Principal\":{\n" +
                    "                \"AWS\":[\n" +
                    "                    \"*\"\n" +
                    "                ]\n" +
                    "            },\n" +
                    "            \"Action\":[\n" +
                    "                \"s3:GetBucketLocation\",\n" +
                    "                \"s3:ListBucket\",\n" +
                    "                \"s3:ListBucketMultipartUploads\"\n" +
                    "            ],\n" +
                    "            \"Resource\":[\n" +
                    "                \"arn:aws:s3:::ces2\"\n" +
                    "            ]\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"Effect\":\"Allow\",\n" +
                    "            \"Principal\":{\n" +
                    "                \"AWS\":[\n" +
                    "                    \"*\"\n" +
                    "                ]\n" +
                    "            },\n" +
                    "            \"Action\":[\n" +
                    "                \"s3:ListMultipartUploadParts\",\n" +
                    "                \"s3:PutObject\",\n" +
                    "                \"s3:AbortMultipartUpload\",\n" +
                    "                \"s3:DeleteObject\",\n" +
                    "                \"s3:GetObject\"\n" +
                    "            ],\n" +
                    "            \"Resource\":[\n" +
                    "                \"arn:aws:s3:::ces2/*\"\n" +
                    "            ]\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
            SetBucketPolicyArgs policyArgs = SetBucketPolicyArgs.builder()
                    .bucket("ces2")
                    .config(config)
                    .build();
            client.setBucketPolicy(policyArgs);
            GetBucketPolicyArgs get = GetBucketPolicyArgs.builder()
                    .bucket("ces2")
                    .build();
            System.out.println("BucketPolicy " + client.getBucketPolicy(get));
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            log.error(e.getMessage(), e);

        }
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void test25() {
//        template.opsForValue().set("1", "2");
        EyiUser userBo = new EyiUser();
        userBo.setEmail("test@qq.com");
        userBo.setUsername("ces");
        RedisUtils.setTemplate(redisTemplate);
        RedisUtils.setExpiredValue("user::"+ UUID.randomUUID(), userBo);
    }


    @Test
    void test26() {
        RedisUtils.setTemplate(redisTemplate);
        System.out.println(RedisUtils.get("fec000fe-a030-43fe-be1d-626e28c54e29"));
    }

    @Test
    void test27() {
        String url = MinioUtils.getObjectUrl("test", "test.png");
        System.out.println(url.substring(0, url.indexOf("?")));
        System.out.println(url);
    }
}
