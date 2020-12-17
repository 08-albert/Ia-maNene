package com.sda.rideshare.configs;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan("com.sda.rideshare")
@Import({DataBaseConfig.class,WebSecurityConfig.class})
public class AppConfig {
}
