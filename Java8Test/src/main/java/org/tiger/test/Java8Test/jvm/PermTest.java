/**
 *
 * Project Name:	Java8Test
 * File Name:	PermTest.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2020年4月5日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.Java8Test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangHuiyuan
 *
 */
public class PermTest {

    // -XX:PermSize=4m -XX:MaxPermSize=4m -Xmx8m 运行时改一下jvm参数
    static String base = "StringPool";
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }

}
