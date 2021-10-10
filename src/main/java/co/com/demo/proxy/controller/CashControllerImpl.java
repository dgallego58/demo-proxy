package co.com.demo.proxy.controller;

import co.com.demo.proxy.controller.swagger.SwaggerDemo;
import co.com.demo.proxy.pattern.contract.CashService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller Using JDK Dynamic proxy, since is implementing an interface
 * Needed to mark the controller as a component also with MvCHanlding methods to inherits all the annotations in the interface
 */
@RestController
@RequestMapping(path = "/jdk")
public class CashControllerImpl implements SwaggerDemo {

    private final CashService cashService;
    @Value(value = "#{'${demo.cosa}'.equals('Y') ? T(java.util.UUID).randomUUID().toString(): 'N'}")
    private String uuid;

    public CashControllerImpl(@Qualifier("cashServiceProxy") CashService cashService) {
        this.cashService = cashService;
    }

    @Override
    public ResponseEntity<Map<String, Object>> testing(@RequestBody Map<String, Object> body) {
        body.put("demoId", uuid);
        body.put("cash", cashService.cashIsValidReturnY("Empty"));
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity<String> testingThrows(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(cashService.cashIsValidReturnY("Throws"));
    }

    @Override
    public ResponseEntity<String> cash(@RequestBody String cash) {
        return ResponseEntity.ok(cashService.cashIsValidReturnY(cash));
    }
}
