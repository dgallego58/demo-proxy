package co.com.demo.proxy.serializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
public class SerializationConfiguration {

    private static final DateTimeFormatter UTC_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withResolverStyle(ResolverStyle.STRICT);
    private static final LocalDateTimeDeserializer DATE_TIME_DESERIALIZER = new LocalDateTimeDeserializer(UTC_DATE_TIME_FORMAT);
    private static final LocalDateTimeSerializer DATE_TIME_SERIALIZER = new LocalDateTimeSerializer(UTC_DATE_TIME_FORMAT);

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilder() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .deserializers(DATE_TIME_DESERIALIZER)
                .serializers(DATE_TIME_SERIALIZER);
    }

    @RestController
    public static class CustomDateController {

        @PostMapping("/pojito")
        public ResponseEntity<Optional<PojoWithDate>> elPojito(@RequestBody PojoWithDate pojo) {
            return ResponseEntity.ok(Optional.ofNullable(pojo));
        }
    }

    @RestControllerAdvice
    public static class Handler {

        @ExceptionHandler(DateTimeParseException.class)
        public ResponseEntity<Map<String, String>> handleWrongDateFormat(DateTimeParseException ex) {

            Map<String, String> errorResponseMap = new HashMap<>();
            errorResponseMap.put("input", ex.getParsedString());
            errorResponseMap.put("message", ex.getMessage());
            return ResponseEntity.ok(errorResponseMap);
        }
    }

}

class PojoWithDate {
    private LocalDateTime localDateTime;
    private LocalDate localDate;

    public PojoWithDate() {
        //to deserialize
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public PojoWithDate setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public PojoWithDate setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
        return this;
    }
}