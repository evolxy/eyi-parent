package com.xu.server.base.util;

import com.xu.commons.utils.SnowFlakeIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/14 15:26
 */
@Slf4j
public class SnowFlakeIdGeneratorUtil implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        SnowFlakeIdGenerator generator = SnowFlakeIdGenerator.getFlowIdInstance();
        long nextId = generator.nextId();
        log.info("snowFlakeIdGenerator -> id :{}", nextId);
        return nextId;
    }
}
