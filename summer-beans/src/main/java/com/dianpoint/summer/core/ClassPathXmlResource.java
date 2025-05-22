package com.dianpoint.summer.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/**
 * <p>
 * ClassPathXmlResource处理xml类型资源
 * </p>
 * 
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 14:19
 */
public class ClassPathXmlResource implements Resource {

    private Document document;
    private Element rootElement;
    private Iterator<Element> elementIterator;

    public ClassPathXmlResource(String fileName) {
        SAXReader saxReader = new SAXReader();
        URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
        try {
            this.document = saxReader.read(xmlPath);
            this.rootElement = document.getRootElement();
            this.elementIterator = rootElement.elementIterator();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        return this.elementIterator.hasNext();
    }

    @Override
    public Object next() {
        return this.elementIterator.next();
    }
}
