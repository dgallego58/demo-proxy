package co.com.demo.proxy.aop.interceptor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;

import java.util.function.Function;

public final class TraceIdHelper {

    private static final Logger log = LoggerFactory.getLogger(TraceIdHelper.class);

    private TraceIdHelper() {
        //Final Class
    }

    public static Function<Span, String> operator(Tracer tracer) {
        return chain -> {
            if (chain == null) {
                var traceId = tracer.nextSpan().context().traceId();
                log.warn("Span is Null generating TxID: {}", traceId);
                return traceId;
            }
            return chain.context().traceId();
        };
    }
}
