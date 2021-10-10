package co.com.demo.proxy.aop.interceptor;

import co.com.demo.proxy.aop.interceptor.util.TraceIdHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
public class WebMvcFilter implements Filter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(WebMvcFilter.class);

    private final Tracer tracer;

    public WebMvcFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void init(FilterConfig filterConfig) {
        //not initiated
        var traceId = TraceIdHelper.operator(tracer).apply(tracer.currentSpan());
        log.info("WebMvcFilter - init -> TxID: {}", traceId);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var traceId = TraceIdHelper.operator(tracer).apply(tracer.currentSpan());
        MDC.put("keyCustom", "TX-" + traceId);
        log.info("WebMvcFilter - doFilter -> TxID: {}", traceId);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //not used
        var currentSpan = tracer.currentSpan();
        var traceId = TraceIdHelper.operator(tracer).apply(currentSpan);
        log.info("WebMvcFilter - Destroy -> TxID: {}", traceId);
        MDC.clear();
    }

    @Override
    public int getOrder() {
        return -2;
    }
}
