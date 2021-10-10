package co.com.demo.proxy.aop.interceptor;

import co.com.demo.proxy.aop.interceptor.util.TraceIdHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        var sleuthTraceId = TraceIdHelper.operator(tracer).apply(tracer.currentSpan());
        log.info("PreHandle Brave ID {}", braveTraceId);
        log.info("PreHandle Sleuth ID {}", sleuthTraceId);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {
        var braveTraceId = braveTracer.currentSpan().context().traceIdString();
        var sleuthTraceId = TraceIdHelper.operator(tracer).apply(tracer.currentSpan());
        log.info("PostHandle Brave ID {}", braveTraceId);
        log.info("PostHandle Sleuth ID {}", sleuthTraceId);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        var method = (HandlerMethod) handler;
        var methodName = method.getMethod().getName();
        var braveTraceId = braveTracer.currentSpan().context().traceIdString();
        var sleuthTraceId = TraceIdHelper.operator(tracer).apply(tracer.currentSpan());
        log.info("AfterCompletion BraveId: {}", braveTraceId);
        log.info("AfterCompletion SleuthId: {}", sleuthTraceId);
        log.info("Method called {}", methodName);
    }
}
