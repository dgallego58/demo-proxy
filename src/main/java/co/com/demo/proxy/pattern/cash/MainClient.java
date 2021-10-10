package co.com.demo.proxy.pattern.cash;

import co.com.demo.proxy.pattern.contract.CashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainClient {

    private static final Logger log = LoggerFactory.getLogger(MainClient.class);

    public static void main(String[] args) {
        CashService concrete = new CashConcrete();
        CashService proxy = new CashServiceProxy(concrete);
        var result2 = proxy.cashIsValidReturnY("Empty $0");
        log.info("result is {}", result2);
    }
}
