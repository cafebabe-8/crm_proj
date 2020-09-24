package org.crm.utils;

import java.util.UUID;

public class UUIDUtil {
    public static String getUUID() {
        // replace 替换所有，不支持正则表达式
        // replaceAll 替换所有，支持正则表达式
        return UUID.randomUUID().toString().replace("-","");
    }
}
