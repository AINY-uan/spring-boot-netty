package org.ainy.util;

import org.springframework.context.ApplicationContext;

/**
 * @author YUANDONG
 * @description Spring上下文工具
 * @date 2020/1/14 08:37
 */
public class SpringContextUtil {

    private static ApplicationContext applicationContext;

    /**
     * 获取上下文
     *
     * @return Spring上下文
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 设置上下文
     *
     * @param applicationContext Spring上下文
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 通过名字获取上下文中的bean
     *
     * @param name 名称
     * @return bean
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 通过类型获取上下文中的bean
     *
     * @param requiredType 类型
     * @return bean
     */
    public static Object getBean(Class<?> requiredType) {
        return applicationContext.getBean(requiredType);
    }
}
