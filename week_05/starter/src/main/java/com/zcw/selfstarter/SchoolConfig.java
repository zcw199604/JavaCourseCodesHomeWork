package com.zcw.selfstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author zcw
 * @date 2021/06/05 3:08 下午
 **/
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "school.starter.config")
public class SchoolConfig {
    private Integer studentId;
    private String  StudentName;

}
