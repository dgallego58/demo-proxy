package co.com.demo.proxy.aop.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class MvcLogInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(MvcLogInterceptor.class);
    private final Tracer tracer;
    private final brave.Tracer braveTracer;

    public MvcLogInterceptor(Tracer tracer, brave.Tracer braveTracer) {
        this.tracer = tracer;
        this.braveTracer = braveTracer;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        var braveTraceId = braveTracer.currentSpan().context().traceIdString();
        var sleuthTraceId = Objects.requireNonNull(tracer.currentSpan(), "Current span is null").context().traceId();
        log.info("PreHandle Brave ID {}", braveTraceId);
        log.info("PreHandle Sleuth ID {}", sleuthTraceId);
        MDC.put("keyCustom", "TX-" + sleuthTraceId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        var braveTraceId = braveTracer.currentSpan().context().traceIdString();
        var sleuthTraceId = Objects.requireNonNull(tracer.currentSpan(), "Current span is null").context().traceId();
        log.info("PostHandle Brave ID {}", braveTraceId);
        log.info("PostHandle Sleuth ID {}", sleuthTraceId);
        MDC.put("keyCustom", "TX-" + sleuthTraceId);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        MDC.clear();
    }
}
