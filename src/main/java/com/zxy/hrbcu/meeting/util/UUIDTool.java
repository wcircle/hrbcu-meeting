package com.zxy.hrbcu.meeting.util;

import java.util.UUID;

/**
 * Created by wenxu on 2018/2/24.
 */
public class UUIDTool {
    public UUIDTool() {
    }
    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
