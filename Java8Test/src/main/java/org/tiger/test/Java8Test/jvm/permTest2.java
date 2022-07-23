/**
 *
 * Project Name:	javacore
 * File Name:	permTest2.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2020年4月5日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.Java8Test.jvm;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WangHuiyuan
 *
 */
public class permTest2 {

    public static void main(String[] args) {
        // -XX:PermSize=4m -XX:MaxPermSize=4m -Xmx8m 运行时改一下jvm参数
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try {
            url = new File("C:\\Users\\lenovo\\Desktop\\blok").toURI().toURL();
            URL[] urls = { url };
            while (true) {
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("org.tiger.test.Java8Test.jvm.PermTest");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
