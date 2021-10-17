package co.com.demo.proxy.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Component
@Validated
@ConfigurationProperties(prefix = "component-demo-props")
public class ComponentDemoProps {

    @NotEmpty(message = "ComponentDemoProps.name not nullable nor empty")
    private String name;

    public String getName() {
        return name;
    }

    public ComponentDemoProps setName(String name) {
        this.name = name;
        return this;
    }
}
