package co.com.demo.proxy.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Validated
//@Component
@ConfigurationProperties(prefix = "demo-ex")
public class DemoProps {

    @NotEmpty(message = "ComponentDemoProps.name not nullable nor empty")
    private String name;

    public String getName() {
        return name;
    }

    public DemoProps setName(String name) {
        this.name = name;
        return this;
    }
}
