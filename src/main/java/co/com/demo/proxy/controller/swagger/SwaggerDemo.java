package co.com.demo.proxy.controller.swagger;

import co.com.demo.proxy.aop.aspect.Monitoreable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Interface Driven Controllers (for JDK Dynamic proxy)
 */
@RequestMapping(path = "/default-path")
@Monitoreable
public interface SwaggerDemo {

    @PostMapping(path = "/test")
    ResponseEntity<Map<String, Object>> testing(@RequestBody Map<String, Object> body);

    @PostMapping(path = "/throws")
    ResponseEntity<String> testingThrows(@RequestBody Map<String, Object> body);

    @PostMapping(path = "/cash")
    ResponseEntity<String> cash(@RequestBody String cash);
}
