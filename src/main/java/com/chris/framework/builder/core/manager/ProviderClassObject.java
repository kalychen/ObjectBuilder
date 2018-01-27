package com.chris.framework.builder.core.manager;

/**
 * ChrisFrameworkObjectBuilder
 * com.chris.framework.builder.core
 * Created by Chris Chen
 * 2018/1/20
 * Explain:提供者类对象
 */
public class ProviderClassObject {
    private Class<?> providerClass;//提供者类
    private Object providerObject;//提供者实例

    public ProviderClassObject() {
    }

    public ProviderClassObject(Class<?> providerClass, Object providerObject) {
        this.providerClass = providerClass;
        this.providerObject = providerObject;
    }

    public Class<?> getProviderClass() {
        return providerClass;
    }

    public void setProviderClass(Class<?> providerClass) {
        this.providerClass = providerClass;
    }

    public Object getProviderObject() {
        return providerObject;
    }

    public void setProviderObject(Object providerObject) {
        this.providerObject = providerObject;
    }
}
