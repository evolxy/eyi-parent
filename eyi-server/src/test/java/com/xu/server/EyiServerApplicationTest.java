package com.xu.server;

import com.xu.server.admin.user.entities.EyiUser;
import com.xu.server.admin.user.repository.UserInfoRepository;
import com.xu.server.base.util.QueryBuilderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.List;

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
        Page<EyiUser> all = repository.findAll(PageRequest.of(0,10));

        System.out.println(all);
    }

    @Test
    void testAdd() {
        EyiUser user = new EyiUser();
        user.setUsername("测试");
        user.setPassword("132131231332");
        user.setEmail("111111111111");
        user.setGender(false);
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
}
