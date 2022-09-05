package com.fang.spring.my_ioc;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shaobin
 * @date 2022/9/2 11:26
 */
public class MyBeanFactory {

    Map<String, Object> beanMap = new HashMap<>();

    public Object getBean(String beanId) {
         return beanMap.get(beanId);
    }

    // 更好的写法，还没到办法解决泛型的问题
//    public <T> T getBean(Class<T> classType, Objects... args) {
//        String simpleName = classType.getSimpleName();
//        simpleName = simpleName.length() > 0 ? simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1, simpleName.length()) : null;
//        return beanMap.get(simpleName);
//    }

    public MyBeanFactory() throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 通过xml文件读取bean
        File file = new File(this.getClass().getResource("/").getPath() + "IOCInjection.xml");
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(file);
        Element rootElement = doc.getRootElement();
        List<Element> beans = rootElement.elements("bean");
        for (Element bean : beans) {
            String beanKey = bean.attributeValue("id");
            String beanClassPath = bean.attributeValue("class");
            Class<?> beanClass = Class.forName(beanClassPath);
            beanMap.put(beanKey, (Object) beanClass.newInstance());
        }
    }

}
