package com.chris.framework.builder.model;

import com.chris.framework.builder.utils.OneKeyUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * YdxApiWebApp
 * com.ydx.app.model
 * Created by Chris Chen
 * 2018/1/26
 * Explain:一键生成的参数表
 */
public class OneKeyParams {
    public static final String SERVICE_PACKAGE_PLACEHOLDER = "${SERVICE_PACKAGE_NAME}";//service包名占位符
    public static final String DAO_PACKAGE_PLACEHOLDER = "${DAO_PACKAGE_NAME}";//dao包名占位符
    public static final String CLASS_PLACEHOLDER = "${CLASS_NAME}";//精简类名占位符
    public static final String TIME_PLACEHOLDER = "${TIME}";//时间占位符

    private String ormPackageName;//用于参照的orm类文件所在的包名
    private String ormExt;//orm类后缀

    private String templetePackage;//模板文件所在的包名
    private String templeteFileName;//dao模板文件名

    private String targetPackage;//目标文件存放的包名
    private String targetFileExt;//目标类的后缀
    private String classPlaceHolder;//类型替换占位符

    private Map<String, String> replaceSchemeMap;//替换方案


    private OneKeyParams() {
    }

    public static OneKeyParams get() {
        OneKeyParams oneKeyParams = new OneKeyParams();
        oneKeyParams.replaceSchemeMap = new HashMap<String, String>();
        return oneKeyParams;
    }

    public String getOrmPackageName() {
        return ormPackageName;
    }

    public OneKeyParams setOrmPackageName(String ormPackageName) {
        this.ormPackageName = ormPackageName;
        return this;
    }

    public String getOrmExt() {
        return ormExt;
    }

    public OneKeyParams setOrmExt(String ormExt) {
        this.ormExt = ormExt;
        return this;
    }

    public String getTempletePackage() {
        return templetePackage;
    }

    public OneKeyParams setTempletePackage(String templetePackage) {
        this.templetePackage = templetePackage;
        return this;
    }

    public String getTempleteFileName() {
        return templeteFileName;
    }

    public OneKeyParams setTempleteFileName(String templeteFileName) {
        this.templeteFileName = templeteFileName;
        return this;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public OneKeyParams setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
        return this;
    }

    public String getTargetFileExt() {
        return targetFileExt;
    }

    public OneKeyParams setTargetFileExt(String targetFileExt) {
        this.targetFileExt = targetFileExt;
        return this;
    }

    public String getClassPlaceHolder() {
        return classPlaceHolder;
    }

    public OneKeyParams setClassPlaceHolder(String classPlaceHolder) {
        this.classPlaceHolder = classPlaceHolder;
        return this;
    }

    public Map<String, String> getReplaceSchemeMap() {
        return replaceSchemeMap;
    }

    public OneKeyParams setReplaceSchemeMap(Map<String, String> replaceSchemeMap) {
        this.replaceSchemeMap = replaceSchemeMap;
        return this;
    }

    public OneKeyParams addReplaceScheme(String placeHodler, String replaceString) {
        if (replaceSchemeMap == null) {
            replaceSchemeMap = new HashMap<>();
        }
        replaceSchemeMap.put(placeHodler, replaceString);
        return this;
    }

    public void create() {
        OneKeyUtils.onkey(this);
    }
}
