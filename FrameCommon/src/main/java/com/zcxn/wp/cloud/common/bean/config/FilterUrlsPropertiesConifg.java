package com.zcxn.wp.cloud.common.bean.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangchaof
 * @date 2018/4/9
 */
@Configuration
@ConditionalOnExpression("!'${urls}'.isEmpty()")
@ConfigurationProperties(prefix = "urls")
public class FilterUrlsPropertiesConifg {

    public List<String> getIgnore() {
        return ignore;
    }

    public void setIgnore(List<String> ignore) {
        this.ignore = ignore;
    }
    //需要忽略的url连接
    private List<String> ignore = new ArrayList<>();


}
