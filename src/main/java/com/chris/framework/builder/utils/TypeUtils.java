package com.chris.framework.builder.utils;

import com.chris.framework.builder.annotation.Expand;
import com.chris.framework.builder.annotation.query.Query;
import com.chris.framework.builder.model.Self;

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
            Class<?> baseClass = queryAnno.value();
            if (baseClass == null || baseClass.getName().equals(Self.class.getName())) {
                return clazz;
            }
            MsgUtils.println("baseClassName: " + baseClass.getName());
            return baseClass;
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
            String fieldClassName = fieldClass.getName();
            String fieldTypeName = field.getType().getName();
            if (fieldClassName.equals(fieldTypeName)) {
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

    /**
     * 根据类名获取类
     *
     * @param className
     * @return
     */
    public static Class<?> getClassForName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
