package co.com.demo.proxy.cash;


//proxy -> AOP (solo para Runtime)
public class CashConcrete implements CashService {

    @Override
    public String cashIsValidReturnY(String cash) {
        return cash.startsWith("Empty") ? "N" : "Y";
    }
}
