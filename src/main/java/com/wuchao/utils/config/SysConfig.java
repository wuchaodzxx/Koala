package com.wuchao.utils.config;

import com.wuchao.utils.exception.UtilException;
import com.wuchao.utils.xml.XMLProperties;

public class SysConfig {
	private static XMLProperties xmlProperties = null;

    private static boolean isReload = true;

    private static String className = SysConfig.class.getName();
    
    private SysConfig() {
    }

    public static boolean isReload() {
        return isReload;
    }

    public static void setReload(boolean isReload) {
    	SysConfig.isReload = isReload;
    }

    /**
     * @description 重新加载配置文件
     * @author panym
     * @date 2009-5-5
     * @version 1.0.0
     * @history1:@author;@date;@description
     * @history2:@author;@date;@description
     * @throws UtilException
     */
    private static void reload()
        throws UtilException {
        try {
            if (isReload() || xmlProperties == null) {
                String xmlFile = ConfigFactory.getConfigFilePath("SysConfigFileName");
                xmlProperties = new XMLProperties(xmlFile);
            }
            setReload(false);
        } catch (Exception e) {
            throw new UtilException(e, className);
        }
    }

    /**
     * @description 根据元素路径得到元素属性
     * @author panym
     * @date 2009-5-5
     * @version 1.0.0
     * @history1:@author;@date;@description
     * @history2:@author;@date;@description
     * @param path
     * @return
     * @throws UtilException
     */
    public static String getProperty(String path)
        throws UtilException {
        try {
            if (isReload)
                reload();
            return xmlProperties.getElementValue(path);
        } catch (Exception e) {
            throw new UtilException(e, className);
        }
    }

    /*
     * 测试
     */
    public static String getTestData()
        throws UtilException {
        return getProperty("nanri");
    }
}
