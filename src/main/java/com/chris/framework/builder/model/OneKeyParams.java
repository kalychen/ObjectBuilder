package com.chris.framework.builder.model;

/**
 * YdxApiWebApp
 * com.ydx.app.model
 * Created by Chris Chen
 * 2018/1/26
 * Explain:一键生成的参数表
 */
public class OneKeyParams {

    public static final String SERVICE_PACKAGE_PLACEHOLDER="${SERVICE_PACKAGE_NAME}";//service包名占位符
    public static final String DAO_PACKAGE_PLACEHOLDER="${DAO_PACKAGE_NAME}";//dao包名占位符
    public static final String CLASS_PLACEHOLDER="${CLASS_NAME}";//精简类名占位符
    public static final String TIME_PLACEHOLDER="${TIME}";//时间占位符

    private String rootPath;//项目根文件夹绝对路径
    private String sourcePath;//项目源码文件夹绝对路径
    private String ormPackageName;//用于参照的orm类文件所在的包名
    private String ormExt;//orm类后缀
    private String packagePlaceHolder;//包名占位符
    private String classPlaceHolder;//精简类名占位符
    private String timePlaceHolder;//时间串占位符

    private String templeteFilePackageName;//模板文件所在的包名
    private String templeteFileName;//dao模板文件名
    private String templeteFilePath;//dao模板文件名

    private String targetFilePackageName;//目标文件存放的包名 单次使用需要 一般一键生成三种文件，由下面三个属性指定
    private String targetFilePath;//目标文件存放的路径
    private String targetFileExt;//目标类的后缀

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getOrmPackageName() {
        return ormPackageName;
    }

    public void setOrmPackageName(String ormPackageName) {
        this.ormPackageName = ormPackageName;
    }

    public String getOrmExt() {
        return ormExt;
    }

    public void setOrmExt(String ormExt) {
        this.ormExt = ormExt;
    }

    public String getPackagePlaceHolder() {
        return packagePlaceHolder;
    }

    public void setPackagePlaceHolder(String packagePlaceHolder) {
        this.packagePlaceHolder = packagePlaceHolder;
    }

    public String getClassPlaceHolder() {
        return classPlaceHolder;
    }

    public void setClassPlaceHolder(String classPlaceHolder) {
        this.classPlaceHolder = classPlaceHolder;
    }

    public String getTimePlaceHolder() {
        return timePlaceHolder;
    }

    public void setTimePlaceHolder(String timePlaceHolder) {
        this.timePlaceHolder = timePlaceHolder;
    }

    public String getTempleteFilePackageName() {
        return templeteFilePackageName;
    }

    public void setTempleteFilePackageName(String templeteFilePackageName) {
        this.templeteFilePackageName = templeteFilePackageName;
    }

    public String getTempleteFileName() {
        return templeteFileName;
    }

    public void setTempleteFileName(String templeteFileName) {
        this.templeteFileName = templeteFileName;
    }

    public String getTempleteFilePath() {
        return templeteFilePath;
    }

    public void setTempleteFilePath(String templeteFilePath) {
        this.templeteFilePath = templeteFilePath;
    }

    public String getTargetFilePackageName() {
        return targetFilePackageName;
    }

    public void setTargetFilePackageName(String targetFilePackageName) {
        this.targetFilePackageName = targetFilePackageName;
    }

    public String getTargetFilePath() {
        return targetFilePath;
    }

    public void setTargetFilePath(String targetFilePath) {
        this.targetFilePath = targetFilePath;
    }

    public String getTargetFileExt() {
        return targetFileExt;
    }

    public void setTargetFileExt(String targetFileExt) {
        this.targetFileExt = targetFileExt;
    }

    public void build() {
        setSourcePath(rootPath + "/src/main/java");
        setTargetFilePath(sourcePath + "/" + targetFilePackageName.replace(".", "/"));
        setTempleteFilePath(sourcePath + "/" + templeteFilePackageName.replace(".", "/") + "/" + templeteFileName);
    }

    @Override
    public String toString() {
        return " rootPath='" + rootPath + '\'' +
                ",\n sourcePath='" + sourcePath + '\'' +
                ",\n ormPackageName='" + ormPackageName + '\'' +
                ",\n ormExt='" + ormExt + '\'' +
                ",\n packagePlaceHolder='" + packagePlaceHolder + '\'' +
                ",\n classPlaceHolder='" + classPlaceHolder + '\'' +
                ",\n timePlaceHolder='" + timePlaceHolder + '\'' +
                ",\n templeteFilePackageName='" + templeteFilePackageName + '\'' +
                ",\n templeteFileName='" + templeteFileName + '\'' +
                ",\n templeteFilePath='" + templeteFilePath + '\'' +
                ",\n targetFilePackageName='" + targetFilePackageName + '\'' +
                ",\n targetFilePath='" + targetFilePath + '\'' +
                ",\n targetFileExt='" + targetFileExt + '\'';
    }
}
