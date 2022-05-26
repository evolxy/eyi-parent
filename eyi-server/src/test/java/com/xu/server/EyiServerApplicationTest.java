package com.xu.server;

import cn.dev33.satoken.secure.BCrypt;
import com.xu.commons.utils.TikaUtils;
import com.xu.server.admin.user.pojo.entities.EyiUser;
import com.xu.server.admin.user.repository.UserInfoRepository;
import com.xu.server.base.util.QueryBuilderUtil;
import com.xu.server.email.pojo.EmailInfo;
import com.xu.server.email.service.EmailService;
import com.xu.server.storage.fdfs.services.impl.FdfsFileServiceImpl;
import com.xu.server.storage.fdfs.utils.FdfsFileUtil;
import org.csource.common.MyException;
import org.csource.fastdfs.StorageClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/22 15:20
 */
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
        user.setDelFlag((byte)0);
        user.setCreateTime(LocalDateTime.now());
        user.setDeleteTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setCreateId(0L);
        user.setDeleteId(0L);
        user.setUpdateId(0L);
        repository.save(user);
    }

    @Autowired
    private StorageClient client;

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

    @Test
    void download() {
        try {
            byte[] bytes = client.download_file("group1", "M00/00/00/cnWlgmJdICiAC4C7AAAJioD2mYI625.png");
            FileOutputStream fos = new FileOutputStream("C:\\Users\\xu\\Pictures\\download.png");
            fos.write(bytes);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private FdfsFileServiceImpl impl;

    @Test
    void test5() {
//        String save = impl.save("C:\\Users\\xu\\Pictures\\test.png");
//        System.out.println(save);
        File file = new File("C:\\Users\\xu\\Pictures\\test.png");
        impl.save(file);
    }

    @Test
    void testDelete() throws MyException {
        String storage = "group1/M00/00/00/cnWlgmJdFYeADXvhAAAJioD2mYI104.png";
        try {
            int group1 = client.delete_file("group1", "M00/00/00/cnWlgmJdICiAC4C7AAAJioD2mYI625.png");
            System.out.println("group 1 = "+ group1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test21() {
        System.out.println(TikaUtils.getContentTypeByExtension("txt"));
    }


    @Autowired
    private EmailService es;
    @Test
    void test22() {
        Map<String, Object> params = new HashMap<>();
        es.sendMailByTemplate(params, new EmailInfo("1254226073@qq.com", "测试"), "email/TestTemplate.ftlh");
    }
}
