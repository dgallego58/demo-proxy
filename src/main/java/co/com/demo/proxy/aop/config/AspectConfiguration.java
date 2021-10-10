package co.com.demo.proxy.aop.config;

import co.com.demo.proxy.pattern.cash.CashConcrete;
import co.com.demo.proxy.pattern.cash.CashServiceProxy;
import co.com.demo.proxy.pattern.contract.CashService;
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
