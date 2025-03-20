package com.jhojan.curso.springboot.interceptor.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod controller = (HandlerMethod) handler;
        LOGGER.info("::::::: preHandle() entrando: {}", controller.getMethod().getName());

        request.setAttribute("startTime", System.currentTimeMillis());

        Thread.sleep(new Random().nextInt(2000));

        Map<String, Object> model = Map.of("error", "no tienes acceso a esta pagina.");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(model);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(json);

        // return HandlerInterceptor.super.preHandle(request, response, handler);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.info("::::::: postHandle() saliendo: {}", ((HandlerMethod) handler).getMethod().getName() );
        long totalTime = System.currentTimeMillis() - (Long) request.getAttribute("startTime");
        LOGGER.info("::::::: totalTime: {}", totalTime);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

}
