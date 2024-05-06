package com.billing.controllers;

import com.billing.models.*;
import com.billing.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class Controllers {
    Client2ServiceService css = new Client2ServiceService();
    ClientService cs = new ClientService();
    ServiceService ss = new ServiceService();
    AccountService as = new AccountService();
    OrganizationService os = new OrganizationService();
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

    @GetMapping("/operations")
    public String operations() {
        return "operations";
    }

    @GetMapping("/clients")
    public String clients(Model model) {
        model.addAttribute("allServices", ss.findAll());
        return "clients";
    }

    @GetMapping("/clients_filter")
    public String ClientsList(@RequestParam(value = "serviceVAC", required = false) String service,
                              @RequestParam(value = "beginDateVAC", required = false) String begin,
                              @RequestParam(value = "endDateVAC", required = false) String end,
                              @RequestParam(value = "creditVAC", required = false) String credit,
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

        String serviceName = (Objects.equals(service, "")) ? null : service;
        boolean creditFlag = (credit == "undefined");

        model.addAttribute("allServices", ss.findAll());
        model.addAttribute("clientServices", css.filter(serviceName, beginDate, endDate, creditFlag));
        return "clients";
    }

    @GetMapping("/register_contract")
    public String registerContract() {
        return "register_contract";
    }

    @GetMapping("/register_contract_save")
    public String registerContractSave(@RequestParam(value = "clientVAC") String clientName,
                                       @RequestParam(value = "serviceVAC") String serviceName,
                                       @RequestParam(value = "contractVAC") String contract,
                                       Model model) {
        model.addAttribute("selectedClientVAC", clientName);
        model.addAttribute("selectedServiceVAC", serviceName);
        model.addAttribute("selectedContractVAC", contract);

        Client client = cs.findByName(clientName);
        Service service = ss.findByName(serviceName);

        if (Objects.isNull(client) || Objects.isNull(service)) {
            model.addAttribute("findError", true);
        } else {
            Client2Service client2Service = new Client2Service(contract, client, service, new java.util.Date(), null);
            try {
                css.save(client2Service);
                model.addAttribute("findError", false);
            } catch (Exception e) {
                model.addAttribute("findError", true);
            }
        }
        return "register_contract";
    }

    @GetMapping("/register_trans")
    public String registerTrans() {
        return "register_trans";
    }

    @GetMapping("/register_trans_save")
    public String registerContractSave(@RequestParam(value = "clientVAC") String clientName,
                                       @RequestParam(value = "amountVAC") String amount,
                                       Model model) {

        model.addAttribute("selectedClientVAC", clientName);
        model.addAttribute("selectedAmountVAC", amount);

        Client client = cs.findByName(clientName);

        if (Objects.isNull(client)) {
            model.addAttribute("findError", true);
        } else {
            try {
                BigDecimal val = new BigDecimal(amount);
                as.addTrans(client.getAccount(), val);
                model.addAttribute("findError", false);
            } catch (Exception e) {
                model.addAttribute("findError", true);
            }
        }
        return "register_trans";
    }

    @GetMapping("/history")
    public String history() {
        return "history";
    }

    @GetMapping("/history_filter")
    public String historyFilter(@RequestParam(value = "clientVAC") String clientName,
                                @RequestParam(value = "beginDateVAC", required = false) String begin,
                                @RequestParam(value = "endDateVAC", required = false) String end,
                                Model model) {

        model.addAttribute("selectedClientVAC", clientName);
        model.addAttribute("selectedBeginDateVAC", begin);
        model.addAttribute("selectedEndDateVAC", end);
        Client client = cs.findByName(clientName);

        if (Objects.isNull(client)) {
            model.addAttribute("findError", true);
            return "history";
        }

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

        TreeMap<Date, BigDecimal> filteredHistory = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<Date, BigDecimal> pair : client.getAccount().getHistory().entrySet()) {
            if ((beginDate == null || pair.getKey().after(beginDate)) &&
                    (endDate == null || pair.getKey().before(endDate))) {
                filteredHistory.put(pair.getKey(), pair.getValue());
            }
        }
        model.addAttribute("history", filteredHistory);
        model.addAttribute("findError", false);
        return "history";
    }

    @GetMapping("/manage_client")
    public String manageClient() {
        return "manage_client";
    }

    @GetMapping("/manage_client_process")
    public String manageClientProcess(@RequestParam(value = "clientVAC") String clientName,
                                      @RequestParam(value = "orgVAC", required = false) String organizationName,
                                      @RequestParam(value = "phoneVAC", required = false) String phone,
                                      @RequestParam(value = "emailVAC", required = false) String email,
                                      @RequestParam(value = "addressVAC", required = false) String address,
                                      @RequestParam(value = "actionVAC") String action,
                                      Model model) {
        model.addAttribute("actionError", false);
        switch (action) {
            case "find" -> {
                Client client = cs.findByName(clientName);
                if (client != null) {
                    model.addAttribute("selectedClientVAC", client);
                } else {
                    model.addAttribute("actionError", true);
                }
            }
            case "create" -> {
                try {
                    if (Objects.equals(organizationName, "")) organizationName = "Физ. лицо";
                    Organization org = os.findByName(organizationName);
                    Client client = new Client(org, clientName, new ClientInfo(phone, email, address), new Account());

                    cs.save(client);
                    model.addAttribute("selectedClientVAC", client);
                } catch (Exception ex) {
                    model.addAttribute("actionError", true);
                }
            }
            case "delete" -> {
                try {
                    Client client = cs.findByName(clientName);
                    cs.delete(client);
                } catch (Exception ex) {
                    model.addAttribute("actionError", true);
                }
            }
            case "edit" -> {
                try {
                    if (Objects.equals(organizationName, "")) organizationName = "Физ. лицо";
                    Client client = cs.findByName(clientName);

                    client.setOrg(os.findByName(organizationName));
                    client.setInfo(new ClientInfo(phone, email, address));

                    cs.update(client);
                } catch (Exception ex) {
                    model.addAttribute("actionError", true);
                }
            }
        }
        return "manage_client";
    }


    @GetMapping("/manage_service")
    public String manageService() {
        return "manage_service";
    }

    @GetMapping("/manage_service_process")
    public String manageServiceProcess(@RequestParam(value = "serviceVAC") String serviceName,
                                       @RequestParam(value = "minVAC", required = false) String min,
                                       @RequestParam(value = "smsVAC", required = false) String sms,
                                       @RequestParam(value = "internetVAC", required = false) String internet,
                                       @RequestParam(value = "max_membersVAC", required = false) String maxMembers,
                                       @RequestParam(value = "tariffVAC", required = false) String tariff,
                                       @RequestParam(value = "extra_minVAC", required = false) String extraMin,
                                       @RequestParam(value = "extra_smsVAC", required = false) String extraSms,
                                       @RequestParam(value = "extra_internetVAC", required = false) String extraInternet,
                                       @RequestParam(value = "actionVAC") String action,
                                       Model model) {
        model.addAttribute("actionError", false);
        switch (action) {
            case "find" -> {
                Service service = ss.findByName(serviceName);
                if (service != null) {
                    model.addAttribute("selectedServiceVAC", service);
                } else {
                    model.addAttribute("actionError", true);
                }
            }
            case "create" -> {
                try {
                    ServicePackage pack = new ServicePackage(Integer.parseInt(min), Integer.parseInt(sms), Integer.parseInt(internet), Integer.parseInt(maxMembers));
                    ServiceTariff serviceTariff = new ServiceTariff(new BigDecimal(tariff), new BigDecimal(extraMin), new BigDecimal(extraSms), new BigDecimal(extraInternet));
                    Service service = new Service(serviceName, pack, serviceTariff);

                    ss.save(service);
                    model.addAttribute("selectedServiceVAC", service);
                } catch (Exception ex) {
                    model.addAttribute("actionError", true);
                }
            }
            case "delete" -> {
                try {
                    Service service = ss.findByName(serviceName);
                    ss.delete(service);
                } catch (Exception ex) {
                    model.addAttribute("actionError", true);
                }
            }
            case "edit" -> {
                try {
                    ServicePackage pack = new ServicePackage(Integer.parseInt(min), Integer.parseInt(sms), Integer.parseInt(internet), Integer.parseInt(maxMembers));
                    ServiceTariff serviceTariff = new ServiceTariff(new BigDecimal(tariff), new BigDecimal(extraMin), new BigDecimal(extraSms), new BigDecimal(extraInternet));
                    Service service = ss.findByName(serviceName);

                    service.setPack(pack);
                    service.setTariff(serviceTariff);

                    ss.update(service);
                } catch (Exception ex) {
                    model.addAttribute("actionError", true);
                }
            }
        }
        return "manage_service";
    }
}