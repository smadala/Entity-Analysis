package com.ire.people;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class HelloController {
    static Log log = LogFactory.getLog(HelloController.class.getName());
    static Launcher launcher = Launcher.getInstance();
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!1");
        return "autosuggest1";
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public Object displayResult(ModelMap modelMap, @RequestParam String id)  {

        try {
            PersonData personData=launcher.getDetails(id);
            modelMap.put("personData",personData);
        } catch (Exception e) {
            modelMap.put("error",e.getMessage());
            log.error("error",e);
            return "error";
        }

        modelMap.put("id", id);
        return "peopleDetail1";
    }
    public static void main(String args[]){
        //Launcher launcher = Launcher.getInstance();
        try {
            PersonData personData=launcher.getDetails("/m/0kvfq2z");
            System.out.println(personData);
        } catch (Exception e) {
            log.error("error",e);
            e.printStackTrace();
        }
    }
}