package co.com.demo.proxy.componentaspect;

import co.com.demo.proxy.componentaspect.monitor.Monitoreable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Monitoreable
public class CashController2 {

    @Value(value = "#{'${demo.cosa}'.equals('Y') ? T(java.util.UUID).randomUUID().toString(): 'N'}")
    private String uuid;

    @PostMapping(path = "/before")
    public ResponseEntity<String> testing(@RequestBody Map<String, Object> body) {
        var clave = body.keySet().stream().findFirst().get();
        var response = (String) body.get(clave);
        return ResponseEntity.ok(uuid);
    }

    @PostMapping(path = "/before2")
    public ResponseEntity<String> testingThrows(@RequestBody Map<String, Object> body) {
        throw new RuntimeException("La mondá de excepción");
    }
}
