package co.com.demo.proxy.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {DemoProps.class})
public class PropertiesConfig {
}
