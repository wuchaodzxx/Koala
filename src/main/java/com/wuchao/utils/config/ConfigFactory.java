package com.wuchao.utils.config;

/**
 * 创建配置文件工厂 这里手工设置XMLConfig类，如从普通文本读则改为TextConfig
 */

import java.util.PropertyResourceBundle;

public class ConfigFactory {

    private final static String PROPERTIES_NAME = "com/nanri/config/conf";

    // 根据名称获得文件的路径
    public static String getConfigFilePath(String configName)
        throws Exception {
        PropertyResourceBundle prbConfig = (PropertyResourceBundle)PropertyResourceBundle.getBundle(PROPERTIES_NAME);
        String filePath = prbConfig.getString(configName);
        return filePath;
    }

}