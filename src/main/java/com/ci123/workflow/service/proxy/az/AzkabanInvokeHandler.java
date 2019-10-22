package com.ci123.workflow.service.proxy.az;

import com.ci123.workflow.bean.response.azkaban.base.BaseResponse;
import com.ci123.workflow.service.az.service.AzkabanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p> 登录的动态代理
 * Project: workflow
 * Package: com.ci123.workflow.service.proxy.az
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/22 15:00
 */
public class AzkabanInvokeHandler implements InvocationHandler {
    private static Logger log = LoggerFactory.getLogger(AzkabanInvokeHandler.class);
    // 接口实现类
    private AzkabanService azkabanApi;
    // 重复次数,事不过三
    private static final Integer RECOUNT = 3;
    // 默认的方法
    private List<String> defaultMsthods;

    public AzkabanInvokeHandler(AzkabanService azkabanApi) {
        this.azkabanApi = azkabanApi;
        this.defaultMsthods = new ArrayList<>(16);
        for (Method method : Object.class.getMethods()) {
            this.defaultMsthods.add(method.getName());
        }
    }

    // 对默认方法的判断
    private boolean isDefaultMethod(String methodName) {
        return this.defaultMsthods.contains(methodName);
    }

    /**
     * 重写动态代理
     *
     * @param proxy  代理实例
     * @param method 代理方法
     * @param args   参数
     * @return 执行结果2
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("动态代理调用 .. ");
        Object result = null;
        int count = 1;
        try {
            while (count < RECOUNT) {
                // 指定代理方法
                result = method.invoke(azkabanApi, args);
                // 判断是否执行成功
                if (Objects.nonNull(result) && !this.isDefaultMethod(method.getName())) {
                    Class<?> superclass = result.getClass().getSuperclass();
                    if (Object.class.equals(superclass)) {
                        superclass = result.getClass();
                    }
                    Field field = superclass.getDeclaredField("status");
                    field.setAccessible(true);

                    if (BaseResponse.SUCCESS.equals(field.get(result))) {
                        log.info("Execute the azkaban's API {} successfully.", method.getName());
                        return result;
                    }else{
                        log.info("Execute the azkaban's API {} failed.", method.getName());
                    }
                    azkabanApi.loginAZ();
                }
                count++;
            }
        } catch (Exception e) {
            // 如果返回的是 null , 捕获异常并实现重新生成结果实例
            if (Objects.isNull(result)) {
                Class<?> returnType = method.getReturnType();
                try {
                    Object o = returnType.newInstance();
                    if (o instanceof BaseResponse) {
                        BaseResponse baseResponse = (BaseResponse) o;
                        baseResponse.setStatus(BaseResponse.ERROR);

                        if (e instanceof InvocationTargetException) {
                            baseResponse.setMessage(((InvocationTargetException) e).getTargetException().getMessage());
                        } else {
                            baseResponse.setMessage(e.getMessage());
                        }

                        baseResponse.correction();
                        result = o;
                    }
                } catch (InstantiationException | IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return result;
    }
}
