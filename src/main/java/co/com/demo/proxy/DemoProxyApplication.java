package co.com.demo.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoProxyApplication {


    @Value("${demo-ex.name}")
    private String name;
    @Value("#{componentDemoProps.name}")
    private String fromBean;

    @Value("#{componentDemoProps['name']}")
    private String spelBean;

    @Value("#{@componentDemoProps.name}")
    private String leamBean;

    @Value("#{(@componentDemoProps.name eq '${demo-ex.name}') and (componentDemoProps.getName() == '${demo-ex.name}') ? 'c:' : ':c'}")
    private String evaluatedBean;

    public static void main(String[] args) {
        SpringApplication.run(DemoProxyApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            System.out.println("Property val: " + name);
            System.out.println("Property fromBean: " + fromBean);
            System.out.println("Property SpElBean: " + spelBean);
            System.out.println("Property leamBean: " + leamBean);
            System.out.println("Property evaluatedBean: " + evaluatedBean);
        };
    }
}
