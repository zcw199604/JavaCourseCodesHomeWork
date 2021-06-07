package com.zcw.other;


import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zcw.selfstarter.Klass;
import com.zcw.selfstarter.School;
import com.zcw.selfstarter.SchoolConfig;
import com.zcw.selfstarter.Student;

/**
 *
 * @author zcw
 * @date 2021/06/05 3:41 下午
 **/
@Configuration
@ConditionalOnClass(value = {School.class, Klass.class, Student.class})
@EnableConfigurationProperties(SchoolConfig.class)
@ConditionalOnProperty(prefix = "school.config", value = "enable", havingValue = "true")
public class AutoConfig {

    @Bean(name = "mySchool")
    @ConditionalOnMissingBean(value = School.class)
    public School getSchoolInfo(SchoolConfig schoolConfig) {
        Student student = new Student(schoolConfig.getStudentId(), schoolConfig.getStudentName());
        Klass klass = new Klass();
        klass.setStudent(student);
        School school = new School(klass);
        return school;
    }
}
