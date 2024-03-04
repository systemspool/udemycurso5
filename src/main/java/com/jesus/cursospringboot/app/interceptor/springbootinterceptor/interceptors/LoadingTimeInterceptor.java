package com.jesus.cursospringboot.app.interceptor.springbootinterceptor.interceptors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("loadingTimeInterpcetor")
public class LoadingTimeInterceptor implements HandlerInterceptor{

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                HandlerMethod controller = ((HandlerMethod)handler);   //se castea el controller con handlerMethod para poder mostrar el nombre de que ednpoint estamos llamando
                logger.info("LoadingTimeInterceptor: preHandle() entrando.... " + controller.getMethod().getName()); //se concatena con el msj
                Long start = System.currentTimeMillis();
                request.setAttribute("start", start);

                //se simula cuanto tardo el request
                Random random = new Random();
                int delay = random.nextInt(500);  //al delay se le da una pausa entre 0 y 500 milisegundos
                Thread.sleep(delay);  //el delay es para que se tarde un poco mas si se quita tardaria menos el endpoin en ejecutarse
                //return true;


                // esto se ocupa en dado caso se arroje un false el interceptor y perzonalicemos el msj
                //se crea un map para mostrar un msj cuando lanze false 
                // Map<String, String> json = new HashMap<>();  //creamos el map
                // json.put("Error", "No tienes acceso a esta pagina");  //le pasamos los msj a mostrar
                // json.put("Date", new Date().toString());

                //convertiremos el msj pasado como map a un json se debe convertir ya que estamos en un interceptor
                // ObjectMapper mapper = new ObjectMapper();  //se utiliza objectMapper para crear un json
                // String jsonString = mapper.writeValueAsString(json); //convertimos en string el objectmapper y le pasamos el json del map
                // response.setContentType("application/json");  //lo guardamos en la respuesta y se la pasamos con setcontentype y le indicamos que es un tipo json con application json
                // response.setStatus(401); //le indicamos el status puede ser 401 o 403 que son respuestas http de no acceso
                // response.getWriter().write(jsonString);  //le pasamos el jsonstring a la respuesta
                return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

                Long end = System.currentTimeMillis();
                Long start = (Long) request.getAttribute("start");
                Long result = end - start;
                logger.info("Tiempo transcurrido: " + result + " milisegundos");
                logger.info("LoadingTimeInterceptor: postHandle() saliendo...." + ((HandlerMethod)handler).getMethod().getName());
    }

    
}
