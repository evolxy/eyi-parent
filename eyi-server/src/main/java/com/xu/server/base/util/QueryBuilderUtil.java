package com.xu.server.base.util;

import com.xu.server.base.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Transient;
import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/25 10:05
 */

@Slf4j
public class QueryBuilderUtil<T extends BaseEntity> {
    public static <T> Specification<T> specificationBuild(T entity) {
        return (r, q, cb)->{
            List<Predicate> predicateList = new ArrayList<>();
            Class<?> clazz = entity.getClass();
            Field[] fields = getFields(clazz);
            for (Field field : fields) {
                field.setAccessible(true);
                //  忽略非数据库字段和final修饰的字段
                boolean isFinal = Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers());
                boolean isNotCol = field.isAnnotationPresent(Transient.class);
                if (isNotCol || isFinal) {
                    continue;
                }
                try {
                    Object value = field.get(entity);
                    // 属性名和属性值
                    String name = field.getName();
                    if (value==null) {
                        continue;
                    }
                    Predicate predicate = cb.equal(r.get(name), value);
                    predicateList.add(predicate);
                } catch (IllegalAccessException e) {
                    log.error(e.getMessage(), e);
                }
            }
            Predicate and = cb.and(predicateList.toArray(new Predicate[0]));
            return q.where(and).getRestriction();
        };
    }

    private static Field[] getFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz!=null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields.toArray(new Field[0]);
    }
}
