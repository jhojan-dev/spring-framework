package com.jhojan.curso.springboot.calendarinterceptor.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class CalendarInterceptor implements HandlerInterceptor {

    @Value("${config.calendar.open}")
    private int open;

    @Value("${config.calendar.close}")
    private int close;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        boolean result = hour >= open && hour < close;
        if (result) {
            StringBuilder message = new StringBuilder("Bienvenidos al horario de atención a clientes");
            message.append(", atendemos desde las ");
            message.append(open);
            message.append(" hrs. ");
            message.append("hasta las ");
            message.append(close);
            message.append(" hrs. ");
            message.append(" Gracias por su visita!");
            request.setAttribute("message", message.toString());
        } else {
            ObjectMapper mapper = new ObjectMapper();
            String message = String.format("Cerrado, fuera del horario de atencion, por favor" +
                    "visitenos desde las %d y las %d hrs. Gracias!", open, close);
            Map<String, Object> data = Map.of(
                    "message", message
            );
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(mapper.writeValueAsString(data));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return result;
        // return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
