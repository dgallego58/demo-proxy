package co.com.demo.proxy;

import co.com.demo.proxy.cash.CashConcrete;
import co.com.demo.proxy.cash.CashService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProxyApplication.class, args);
    }

    @Bean
    public CashService cashService(){
        return new CashConcrete();
    }

}
