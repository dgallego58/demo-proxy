package co.com.demo.proxy.aop.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@Order(-1)
public class RequestFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestFilter.class);
    private final Tracer tracer;

    public RequestFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var traceId = Objects.requireNonNull(tracer.currentSpan(), "TraceId is null").context().traceId();
        log.info("OncePerRequestFilter -> traceId {}", traceId);
        filterChain.doFilter(request, response);
    }
}
