package hello.servlet.web.frontcontroller.v5.adepter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdepter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controllerV3 = (ControllerV3) handler;
        Map<String,String> paramMap = getMap(request);
        ModelView modelView = controllerV3.process(paramMap);
        return modelView;
    }

    private Map<String, String> getMap(HttpServletRequest request) {
        Map<String,String> requestMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(getName -> requestMap.put(getName, request.getParameter(getName)));
        return requestMap;
    }

}
