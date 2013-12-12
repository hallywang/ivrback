package com.emag.util;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用 JDOM + XPATH 解析XMl
 */
public class XmlParse {
    /**
     * builder
     */
    SAXBuilder builder;

    /**
     * 文档
     */
    Document doc;

    /**
     * xml 的根元素
     */
    Element rootElement;

    /**
     * 构造函数
     *
     * @param fileName 完整的文件名，路径+文件名
     * @throws JDOMException 有可能抛出JDOM异常
     * @throws IOException   有可能跑出IO异常
     */
    public XmlParse(String fileName) throws JDOMException, IOException {
        builder = new SAXBuilder();
        doc = builder.build(fileName);
        rootElement = doc.getRootElement();
    }

    /**
     * 构造函数
     *
     * @param in 输入
     * @throws JDOMException 有可能抛出JDOM异常
     * @throws IOException   有可能跑出IO异常
     */
    public XmlParse(InputStream in) throws JDOMException, IOException {
        builder = new SAXBuilder();
        doc = builder.build(in);
        rootElement = doc.getRootElement();
    }

    /**
     * 根据path，获取元素获取到的元素列表
     *
     * @param path 路径信息
     * @return 返回符合条件的元素列表
     * @throws JDOMException 可能跑出JDOM异常
     */
    public List<Object> getNodeList(String path) throws JDOMException {
        //默认返回一个空列表（避免返回null）
        List<Object> rtnList = new ArrayList<Object>();

        List<Object> tmpList = XPath.selectNodes(rootElement, path);
        if (tmpList != null && !tmpList.isEmpty()) {
            rtnList = tmpList;
        }

        return rtnList;
    }

    /**
     * 根据path，返回第一个符合条件的元素
     *
     * @param path 路径信息
     * @return 返回第一个符合条件的元素
     */
    public Object getSingleNode(String path) throws JDOMException {
        //默认返回null，表示根据path没有查询到任何元素
        Object rtnObject = null;

        Object tmpObject = XPath.selectSingleNode(rootElement, path);
        if (tmpObject != null) {
            rtnObject = tmpObject;
        }

        return rtnObject;
    }


    /**
     * 将元素以字符串的形式进行输出
     *
     * @param element 输出的元素
     * @param flag    输出对象是否包含“自己”的标记：0不输出自己、只输出所有“子”；1输出“自己”，包括“自己”和所有“子”
     * @return 返回字符串类型的元素
     */
    public String outputElement(Element element, int flag) {
        StringBuilder buff = new StringBuilder();

        //只输出“子”，而不输出自己
        if (flag == 0) {
            List<Object> childList = element.getChildren();
            for (Object oneChile : childList) {
                buff.append(outputElement((Element) oneChile));
            }
        } else {
            buff.append(outputElement(element));
        }

        return buff.toString();
    }

    /**
     * 将元素以字符串的形式进行输出
     *
     * @param element 输出的元素
     * @return 返回字符串类型的元素
     */
    public String outputElement(Element element) {
        Format format = Format.getPrettyFormat();
        format.setEncoding("utf-8");
        XMLOutputter out = new XMLOutputter();
        out.setFormat(format);

        return out.outputString(element);
    }


    public static void main(String[] aa) throws JDOMException, IOException {
        for (int i = 13; i<117; i++) {
            System.out.println("april"+i);
        }
    }

}
