/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanbooster.examSpringMartinsAnthony.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author HB
 */
@RestController
@RequestMapping(path = "/dashboard")
public class DashboardController {
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView mv = new ModelAndView("dashboard");
        return mv;
    }
}
