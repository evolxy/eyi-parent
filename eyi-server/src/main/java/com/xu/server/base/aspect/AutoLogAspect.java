package com.xu.server.base.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/7/25 20:55
 */

@Aspect
@Component
@Slf4j
public class AutoLogAspect {
    @Pointcut("execution(* com.xu.server.*.*Controller(*)) || @within(org.springframework.web.bind.annotation.RestController)")
    public void pointCut() {}

    @Before("pointCut()")
    public void before(JoinPoint jp) {
        StringBuilder sb = new StringBuilder();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        for (Object arg: args) {
            if (arg == null) {
                continue;
            }
            Class<?> aClass = arg.getClass();
            if (MultipartFile.class.isAssignableFrom(aClass)) {
                continue;
            }
            String argTypeName = aClass.getSimpleName();
            String argStr = JSONObject.toJSONString(arg);

            sb.append("\t").append(argTypeName).append("\t").append(argStr);
        }
        log.debug("正在调用方法 {} , 参数列表: {}", methodName, sb);
    }
}
