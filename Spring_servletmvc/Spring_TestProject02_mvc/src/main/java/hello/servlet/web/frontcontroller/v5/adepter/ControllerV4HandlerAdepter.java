package hello.servlet.web.frontcontroller.v5.adepter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdepter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controllerV4 = (ControllerV4) handler;
        Map<String, Object> model = new HashMap<>();
        Map<String,String> paramMap = getMap(request);
        String viewName = controllerV4.process(paramMap, model);

        ModelView modelView = new ModelView(viewName);
        modelView.setMap(model);

        return modelView;
    }

    private Map<String, String> getMap(HttpServletRequest request) {
        Map<String,String> requestMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(getName -> requestMap.put(getName, request.getParameter(getName)));
        return requestMap;
    }

}
