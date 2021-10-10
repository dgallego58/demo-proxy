package co.com.demo.proxy.cash;

import co.com.demo.proxy.aop.aspect.Monitoreable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Monitoreable
public class CashServiceProxy implements CashService {

    private static final Logger log = LoggerFactory.getLogger(CashServiceProxy.class);
    private final CashService cashService;

    public CashServiceProxy(CashService cashService) {
        this.cashService = cashService;
    }

    @Override
    public String cashIsValidReturnY(String cash) {
        log.info("Proxy: cash received {}", cash);
        String delegator = cashService.cashIsValidReturnY(cash);
        log.info("Proxy: response is {}", delegator);
        return delegator;
    }
}
