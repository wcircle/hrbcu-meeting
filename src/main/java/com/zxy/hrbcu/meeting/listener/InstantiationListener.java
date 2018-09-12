package com.zxy.hrbcu.meeting.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by wenxu on 2017/5/24.
 */
public class InstantiationListener implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //==>继承于InitializingBean 的afterPropertiesSet()方法是初始化方法
    @Override
    public void afterPropertiesSet() {
        logger.info("初始化执行");
    }
}
