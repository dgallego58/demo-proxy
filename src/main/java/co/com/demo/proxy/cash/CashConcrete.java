package co.com.demo.proxy.cash;

import co.com.demo.proxy.aop.aspect.Monitoreable;

@Monitoreable
public class CashConcrete implements CashService {

    @Override
    public String cashIsValidReturnY(String cash) {
        if (cash.contains("Throws")) {
            throw new RuntimeException("Demo throws");
        }
        return cash.startsWith("Empty") ? "N" : "Y";
    }
}
