package com.chris.framework.builder.utils;

import com.chris.framework.builder.annotation.Expand;
import com.chris.framework.builder.annotation.query.Query;

import java.lang.reflect.Field;

/**
 * ChrisFrameworkObjectBuilder
 * com.chris.framework.builder.utils
 * Created by Chris Chen
 * 2018/1/16
 * Explain:处理类型的工具
 */
public class TypeUtils {
    /**
     * 判断一个类是不是基本数据类型或者其包装类
     *
     * @param clazz
     * @return
     */
    public static boolean equalsPrimitive(Class<?> clazz) {
        Class<?>[] clazzes = {
                int.class, Integer.class,
                short.class, Short.class,
                long.class, Long.class,
                float.class, Float.class,
                double.class, Double.class,
                byte.class, Byte.class,
                char.class, Character.class,
                boolean.class, Boolean.class
        };
        if (clazz == null) {
            return false;
        }
        String clazzName = clazz.getName();
        for (Class<?> cls : clazzes) {
            if (cls.getName().equals(clazzName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取基本数据类型
     *
     * @param object
     * @return
     */
    public static Class<?> getBaseEntityClass(Object object) {
        return getBaseEntityClass(object.getClass());
    }

    /**
     * 获取基本数据类型
     *
     * @param clazz
     * @return
     */
    public static Class<?> getBaseEntityClass(Class<?> clazz) {
        Expand expandAnno = clazz.getDeclaredAnnotation(Expand.class);
        if (expandAnno != null) {
            return expandAnno.baseEntity();
        }
        Query queryAnno = clazz.getDeclaredAnnotation(Query.class);
        if (queryAnno != null) {
            return queryAnno.value();
        }
        return clazz;
    }

    /**
     * 在一个对象中找到一个数据类型的字段
     *
     * @param object
     * @param fieldClass
     * @return
     */
    public static Field getFieldByTypeFromObject(Object object, Class<?> fieldClass) {
        Class<?> objectClass = object.getClass();
        Field[] fields = objectClass.getDeclaredFields();
        for (Field field : fields) {
            if (fieldClass.getName().equals(field.getType().getName())) {
                return field;
            }
        }
        return null;
    }

    /**
     * 在一个对象中找到一个特定名称的字段
     *
     * @param object
     * @param fieldName
     * @return
     */
    public static Field getFieldByNameFromObject(Object object, String fieldName) {
        Class<?> objectClass = object.getClass();
        return getFieldByNameFromClass(objectClass, fieldName);
    }

    /**
     * 在一个对象中找到一个特定名称的字段
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    public static Field getFieldByNameFromClass(Class<?> clazz, String fieldName) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (fieldName.equals(field.getName())) {
                return field;
            }
        }
        return null;
    }
}
