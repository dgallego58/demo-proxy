package co.com.demo.proxy.aop.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.function.Function;

@Component
public class WebMvcFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(WebMvcFilter.class);

    private final Tracer tracer;

    public WebMvcFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void init(FilterConfig filterConfig) {
        //not initiated
        var currentSpan = tracer.currentSpan();
        var traceId = operator().apply(currentSpan);
        log.info("WebMvcFilter - init -> TxID: {}", traceId);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var currentSpan = tracer.currentSpan();
        var traceId = operator().apply(currentSpan);
        log.info("WebMvcFilter - doFilter -> TxID: {}", traceId);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //not used
        var currentSpan = tracer.currentSpan();
        var traceId = operator().apply(currentSpan);
        log.info("WebMvcFilter - Destroy -> TxID: {}", traceId);
    }

    private Function<Span, String> operator() {
        return chain -> {
            if (chain == null) {
                var traceId = UUID.randomUUID().toString();
                log.warn("Span is Null generating TxID: {}", traceId);
                return traceId;
            }
            return chain.context().traceId();
        };
    }

}
