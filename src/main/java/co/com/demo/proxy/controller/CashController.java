package co.com.demo.proxy.controller;

import co.com.demo.proxy.aop.aspect.Monitoreable;
import co.com.demo.proxy.cash.CashService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller using CGLIB
 */
@RestController
@RequestMapping(path = "/cglib")
@Monitoreable
public class CashController {

    private final CashService cashService;

    public CashController(CashService cashService) {
        this.cashService = cashService;
    }

    @GetMapping("/any")
    public ResponseEntity<String> getAnyCash(@RequestParam String cash){
        return ResponseEntity.ok(cashService.cashIsValidReturnY(cash));
    }
}
