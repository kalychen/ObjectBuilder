package com.chris.framework.builder;

import com.chris.framework.builder.utils.MsgUtils;

/**
 * ChrisFrameworkObjectBuilder
 * com.chris.framework.builder
 * Created by Chris Chen
 * 2018/1/14
 * Explain:
 */
public class TestMain {

    public static void main(String[] args) {
        int a = 1;
        MsgUtils.print(getClassName(a));
    }

    public static boolean isPrimitive(Object o){
        return o.getClass().isPrimitive();
    }
    public static String getClassName(Object o){
        return o.getClass().getTypeName();
    }
}
