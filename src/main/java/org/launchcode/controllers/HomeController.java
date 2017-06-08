package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {                                         //handles all requests to and from root route ('/')

    @RequestMapping(value = "")                                       //root route ('/')
    public String index() {                                                                                             //method that returns 'index' view
        return "index";
    }

}
