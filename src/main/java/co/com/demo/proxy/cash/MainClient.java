package co.com.demo.proxy.cash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainClient {

    private static final Logger log = LoggerFactory.getLogger(MainClient.class);

    public static void main(String[] args) {
        CashService service = new CashConcrete();
       /* var result = service.cashIsValidReturnY("Empty $0");
        log.info("result is " + result);*/

        CashService proxy = new CashServiceProxy(service);
        var result2 = proxy.cashIsValidReturnY("Empty $0");
        log.info("result is " + result2);
    }
}
