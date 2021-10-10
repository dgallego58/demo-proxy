package co.com.demo.proxy.aop.config;

import co.com.demo.proxy.cash.CashConcrete;
import co.com.demo.proxy.cash.CashService;
import co.com.demo.proxy.cash.CashServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfiguration {

    @Bean
    public CashService cashService() {
        return new CashConcrete();
    }

    @Bean
    public CashService cashServiceProxy(CashService cashService) {
        return new CashServiceProxy(cashService);
    }

}
