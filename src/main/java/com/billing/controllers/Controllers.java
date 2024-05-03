package com.billing.controllers;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.billing.services.*;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Controller
public class Controllers {
    Client2ServiceService css = new Client2ServiceService();
    ClientService cs = new ClientService();
    ServiceService ss = new ServiceService();

    UserService us = new UserService();
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        if (Objects.isNull(us.checkValid(username, password))) {
            model.addAttribute("isBadTry", true);
            return "index";
        } else {
            model.addAttribute("isBadTry", false);
            return "redirect:/operations";
        }
    }
//    @GetMapping(value = "/login")
//    public String login(Model model){
//        System.out.println(model.getAttribute("username"));
//        return "login";
//    }
    @GetMapping("/operations")
    public String operations() {
        return "operations";
    }

    @GetMapping("/clients")
    public String clients(Model model) {
        model.addAttribute("allServices", ss.findAll());
        return "clients";
    }

    @GetMapping(value = {"/clients_filter"})
    public String ClientsList(@RequestParam(value="serviceVAC", required=false) String service,
                                @RequestParam(value="beginDateVAC", required=false) String begin,
                                @RequestParam(value="endDateVAC", required=false) String end,
                                @RequestParam(value="creditVAC", required = false) String credit,
                                Model model) {

        model.addAttribute("selectedServiceVAC", service);
        model.addAttribute("beginDateVAC", begin);
        model.addAttribute("endDateVAC", end);
        model.addAttribute("minSalaryVAC", credit);

        Date beginDate, endDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            beginDate = format.parse(begin);
        } catch (ParseException e) {
            beginDate = null;
        }
        try {
            endDate = format.parse(end);
        } catch (ParseException e) {
            endDate = null;
        }

//        System.out.println(credit);
        String serviceName = (Objects.equals(service, "")) ? null : service;
        boolean creditFlag = (credit == "undefined");

        model.addAttribute("allServices", ss.findAll());
        model.addAttribute("clientServices", css.filter(serviceName, beginDate, endDate, creditFlag));
        return "clients";
    }
    @GetMapping(value = {"/clients_filter"})

}