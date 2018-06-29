package com.mx.dao.druid;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by menxu on 18/6/29.
 */
@ConfigurationProperties(prefix = "druid")
@Getter
@Setter
public class DruidProperties {
    private String url;
    private String username;
    private String password;
    private String driverClass;

    private int maxActive;
    private int minIdle;
    private int initialSize;
    private boolean testOnBorrow;
    private boolean testWhileIdle;
    private String validationQuery;
}
