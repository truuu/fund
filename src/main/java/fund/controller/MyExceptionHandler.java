package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import fund.service.LogService;

@ControllerAdvice
public class MyExceptionHandler  {

    @Autowired LogService logService;

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception e) {
        ModelAndView model = new ModelAndView("home/index");
        String errorMsg = logService.logError(e);
        model.addObject("errorMsg", errorMsg);
        return model;
    }

}
