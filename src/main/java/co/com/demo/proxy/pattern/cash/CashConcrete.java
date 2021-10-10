package co.com.demo.proxy.pattern.cash;

import co.com.demo.proxy.aop.aspect.Monitoreable;
import co.com.demo.proxy.pattern.contract.CashService;

@Monitoreable
public class CashConcrete implements CashService {

    @Override
    public String cashIsValidReturnY(String cash) {
        if (cash.contains("Throws")) {
            throw new BusinessException("Demo throws");
        }
        return cash.startsWith("Empty") ? "N" : "Y";
    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }

        public BusinessException(String message, Throwable cause) {
            super(message, cause);
        }

        public BusinessException(Throwable cause) {
            super(cause);
        }
    }
}
