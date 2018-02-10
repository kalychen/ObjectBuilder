package com.chris.framework.builder.utils;

import com.chris.framework.builder.model.OneKeyParams;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * YdxApiWebApp
 * com.ydx.app.utils
 * Created by Chris Chen
 * 2018/1/26
 * Explain:关于一键操作的工具
 */
public class OneKeyUtils {

    /**
     * 一键生成
     *
     * @param params
     */
    public static void onkey(OneKeyParams params) {
        //构建必要参数
        String rootPath = new File("").getAbsolutePath();//项目根文件夹
        String srcPath = rootPath + "/src/main/java";//源码文件夹
        String templeteFilePath = srcPath + "/" + params.getTempletePackage().replace(".", "/") + "/" + params.getTempleteFileName();//模版文件全路径
        String targetPath = srcPath + "/" + params.getTargetPackage().replace(".", "/");//目标存放全路径

        String tempContent = IoUtils.readTxtFile(templeteFilePath);//读取模板文件内容
        //获取包下的所有类
        List<Class<?>> classList = ClassUtils.getClasses(params.getOrmPackageName());
        Map<String, String> classTagMap = params.getClassTagMap();
        String targetFilePath = null;
        String targetContent = null;
        for (Class<?> clazz : classList) {
            String classSimpleName = clazz.getSimpleName().replace(params.getOrmExt(), "");

            targetFilePath = targetPath + "/" + classSimpleName + params.getTargetFileExt() + ".java";//目标文件全名

            //替换
            Map<String, String> replaceSchemeMap = params.getReplaceSchemeMap();
            //添加替换类的
            replaceSchemeMap.put(params.getClassPlaceHolder(), classSimpleName);
            Set<String> replaceKeySet = replaceSchemeMap.keySet();
            targetContent = new String(tempContent);
            //替换数据类标记
            String replacement = classTagMap.get(classSimpleName.toLowerCase());
            if (StringUtils.isEmpty(replacement)) {
                replacement = classSimpleName;//如果集合中没有对应的标记，就用简短类名来代替
            }
            targetContent = targetContent.replace(params.getClassTagPlaceHolder(), replacement);
            for (String placeHolder : replaceKeySet) {
                targetContent = targetContent.replace(placeHolder, replaceSchemeMap.get(placeHolder));
            }
            //放大招
            IoUtils.writeTxtFile(targetFilePath, targetContent);
        }
    }
}
