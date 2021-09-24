package co.com.demo.proxy.aop;

import co.com.demo.proxy.cash.CashService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CashController {

    private final CashService cashService;

    public CashController(CashService cashService) {
        this.cashService = cashService;
    }

    @GetMapping("/")
    public ResponseEntity<String> getAnyCash(@RequestParam String cash){
        return ResponseEntity.ok(cashService.cashIsValidReturnY(cash));
    }
}
