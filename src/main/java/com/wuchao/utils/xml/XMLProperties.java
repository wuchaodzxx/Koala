package com.wuchao.utils.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wuchao.utils.exception.UtilException;

public class XMLProperties {
    private Document doc = null;

    /**
     * 缓存element值 key为element全称，root.parent.child value为element值
     * 没有子节点的element才保存
     */
    private Map propertiesCache = new HashMap();

    /**
     * 缓存element的attribute值
     * key为element全称＋attribute名称，root.parent.child.attribute value为attribute值
     */
    private Map attributesCache = new HashMap();

    /**
     * 根据指定的xml文件构造本类，构造后遍历该文件，填充propertiesCache和attributesCache
     * 
     * @param xmlFile
     * @throws DocumentException
     */
    public XMLProperties(String xmlFile)
        throws UtilException {
        try {
            SAXReader reader = new SAXReader();
            doc = reader.read(new File(xmlFile));
            doc.getRootElement().accept(
                new MyXMLVisitor(propertiesCache, attributesCache));
        } catch (DocumentException e) {
            throw new UtilException(e, this.getClass());
        }

    }

    /**
     * 返回根节点
     * 
     * @return
     */
    public Element getRootElement()
        throws UtilException {
        try {
            return doc.getRootElement();
        } catch (Exception e) {
            throw new UtilException(e, this.getClass());
        }
    }

    /**
     * 根据element名称，返回element值 没有找到指定的element，返回null
     * 
     * @param propertyName
     *            全称，从root开始
     * @return
     */
    public String getElementValue(String elementName)
        throws UtilException {
        try {
            if (propertiesCache.containsKey(elementName)) {
                return (String)propertiesCache.get(elementName);
            }
            Element element = getLeafElement(elementName);
            if (element != null) {
                String value = element.getText();
                value = value.trim();
                return value;
            } else
                return null;
        } catch (Exception e) {
            throw new UtilException(e, this.getClass());
        }
    }

    /**
     * 根据父element名称，获取子element列表，匹配其中一个孙的值，返回另外一个孙的值
     * 
     * @param parentName
     *            父
     * @param equalName
     *            匹配的孙的元素名
     * @param equalValue
     *            匹配的孙的元素值
     * @param resultName
     *            需要获取值的孙元素的名称
     * @return
     * @throws UtilException
     */
    public String getElementValue(String parentName, String equalName,
                                  String equalValue, String resultName)
        throws UtilException {
        try {
            List l = getElements(parentName);
            if (l.size() == 0)
                return "";
            Iterator iter = l.iterator();
            Element child = null;
            Element grandson = null;
            boolean flag = false;
            while (iter.hasNext()) {
                child = (Element)iter.next();
                if (isLeafElement(child))
                    return "";
                List list = child.elements();
                for (int i = 0; i < list.size(); i++ ) {
                    grandson = (Element)list.get(i);
                    if (equalName.equals(grandson.getName())) {
                        if (equalValue.equals(grandson.getTextTrim())) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) {
                    for (int i = 0; i < list.size(); i++ ) {
                        grandson = (Element)list.get(i);
                        if (resultName.equals(grandson.getName())) {
                            return grandson.getTextTrim();
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new UtilException(e, this.getClass());
        }
        return "";
    }

    /**
     * 根据名称，返回指定element，如果非叶子element，返回null
     * 
     * @param propertyName
     * @return
     */
    public Element getLeafElement(String elementName)
        throws UtilException {
        try {
            String[] propName = parseElementName(elementName);
            Element element = getRootElement();
            for (int i = 1; i < propName.length; i++ ) {
                element = element.element(propName[i]);
                if (element == null) {
                    return null;
                }
            }
            if ( !isLeafElement(element)) {
                System.out.println("not leaf element");
                return null;
            }
            return element;
        } catch (Exception e) {
            throw new UtilException(e, this.getClass());
        }
    }

    /**
     * 根据名称，返回指定element，不判断是否为叶子
     * 
     * @param propertyName
     * @return
     */
    public Element getElement(String elementName)
        throws UtilException {
        try {
            String[] propName = parseElementName(elementName);
            Element element = getRootElement();
            for (int i = 1; i < propName.length; i++ ) {
                element = element.element(propName[i]);
                if (element == null) {
                    return null;
                }
            }
            return element;
        } catch (Exception e) {
            throw new UtilException(e, this.getClass());
        }
    }

    /**
     * 根据名称，返回子元素List
     * 
     * @param elementName
     * @return
     */
    public List getElements(String elementName)
        throws UtilException {
        try {
            String[] names = parseElementName(elementName);
            Element element = getRootElement();
            for (int i = 1; i < names.length; i++ ) {
                element = element.element(names[i]);
                if (element == null) {
                    return null;
                }
            }
            return element.elements();
        } catch (Exception e) {
            throw new UtilException(e, this.getClass());
        }
    }

    /**
     * 根据父元素名称，返回子元素的value的List，子元素必须为叶子，否则抛出异常
     * 
     * @param elementName
     * @return
     * @throws UtilException
     */
    public List getElementsValues(String elementName)
        throws UtilException {
        try {
            String[] names = parseElementName(elementName);
            Element element = getRootElement();
            for (int i = 1; i < names.length; i++ ) {
                element = element.element(names[i]);
                if (element == null) {
                    return null;
                }
            }
            List tmpElements = element.elements();
            if (tmpElements == null || tmpElements.size() == 0) {
                return null;
            } else {
                List valueList = new ArrayList();
                Iterator iter = tmpElements.iterator();
                while (iter.hasNext()) {
                    element = (Element)iter.next();
                    if ( !isLeafElement(element)) {
                        throw new Exception("子元素中包含非叶子元素，不能取值！");
                    } else {
                        valueList.add(element.getText().trim());
                    }
                }
                return valueList;
            }
        } catch (Exception e) {
            throw new UtilException(e, this.getClass());
        }
    }

    /**
     * 判断element是否为叶子
     * 
     * @param element
     * @return
     */
    public boolean isLeafElement(Element element)
        throws UtilException {
        try {
            List list = element.elements();
            if (list.size() > 0)
                return false;
            else
                return true;
        } catch (Exception e) {
            throw new UtilException(e, this.getClass());
        }
    }

    /**
     * 根据属性名，返回属性值
     * 
     * @param attributeName
     *            全称 root.parent.child.attribute
     * @return
     */
    public String getAttributeValue(String attributeName)
        throws UtilException {
        try {
            if (attributesCache.containsKey(attributeName)) {
                return (String)attributesCache.get(attributeName);
            }
            Element element = getElement(attributeName.substring(0,
                attributeName.lastIndexOf(".")));
            return element.attributeValue(attributeName.substring(attributeName.lastIndexOf(".") + 1));
        } catch (Exception e) {
            throw new UtilException(e, this.getClass());
        }
    }

    /**
     * 切分元素名称
     * 
     * @param name
     * @return
     */
    private String[] parseElementName(String name)
        throws UtilException {
        // return name.split(".");
        try {
            int size = 1;
            for (int i = 0; i < name.length(); i++ ) {
                if (name.charAt(i) == '.') {
                    size++ ;
                }
            }
            String[] eleName = new String[size];
            // Use a StringTokenizer to tokenize the property name.
            StringTokenizer tokenizer = new StringTokenizer(name, ".");
            int i = 0;
            while (tokenizer.hasMoreTokens()) {
                eleName[i] = tokenizer.nextToken();
                i++ ;
            }
            return eleName;
        } catch (Exception e) {
            throw new UtilException(e, this.getClass());
        }
    }

    /**
     * 根据父元素，返回子元素的 属性名为的attributeName得 值
     * 
     * @param elementName
     * @return
     * @throws UtilException
     */
    public Hashtable getElementsValues(Element element, String attributeName)
        throws UtilException {
        Hashtable returnH = new Hashtable();
        try {

            List tmpElements = element.elements();
            if (tmpElements == null || tmpElements.size() == 0) {
                return null;
            } else {
                for (int i = 0; i < tmpElements.size(); i++ ) {
                    Element chileelement = (Element)tmpElements.get(i);
                    returnH.put(chileelement.attributeValue(attributeName),
                        chileelement.getText());
                }
            }
        } catch (Exception e) {
            throw new UtilException(e, this.getClass());
        }
        return returnH;
    }

    public static void main(String[] args) {
        try {
            XMLProperties properties = new XMLProperties("d:\\lcims60\\webapps\\config\\sysconfig.xml");
            System.out.println(properties.getElementValue("lcbms.modGroupid.checkBill"));
            System.out.println(properties.getElementValue(
                "lcbms.modGroupid.results", "value", "5", "description"));
            // System.out
            // .println(properties
            // .getAttributeValue("table.m_customer.customername.querytype"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
