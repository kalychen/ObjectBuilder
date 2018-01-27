package com.chris.framework.builder.utils;

import com.chris.framework.builder.model.OneKeyParams;

import java.util.List;

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
        String tempContent = IoUtils.readTxtFile(params.getTempleteFilePath());//模板文件内容
        //获取包下的所有类
        List<Class<?>> classList = ClassUtils.getClasses(params.getOrmPackageName());
        for (Class<?> clazz : classList) {
            String classSimpleName = clazz.getSimpleName().replace("Entity", "");

            //放大招
            IoUtils.writeTxtFile(params.getTargetFilePath() + "/" + classSimpleName + params.getTargetFileExt() + ".java",
                    tempContent.replace(params.getClassPlaceHolder(), classSimpleName)
                            .replace(params.getPackagePlaceHolder(), params.getTargetFilePackageName()));

        }
    }
}
