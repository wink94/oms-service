package com.windula.oms.filter;

import com.windula.oms.annotation.GenerateCorrelationId;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static com.windula.oms.common.Constants.CORRELATION_ID_LOG_VAR_NAME;
import static com.windula.oms.common.Constants.HEADER_KEY_CORRELATION_ID;

public class CorrelationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) {

        if (handler instanceof HandlerMethod) {
            GenerateCorrelationId generateCorrelationId = ((HandlerMethod) handler).getMethodAnnotation(GenerateCorrelationId.class);

            String correlationId = request.getHeader(HEADER_KEY_CORRELATION_ID);
            MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);

            if (generateCorrelationId != null && !StringUtils.hasLength(correlationId)) {
                /*
                if generateCorrelationId is not null, and the X-Syy-Correlation-Id has not been passed in the request header,
                generate a unique id manually
                 */
                correlationId = generateUniqueCorrelationId();
                MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
                                final Object handler, final Exception ex) {
        MDC.remove(CORRELATION_ID_LOG_VAR_NAME);
    }

    private String generateUniqueCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
