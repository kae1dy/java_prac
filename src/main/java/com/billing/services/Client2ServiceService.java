package com.billing.services;

import com.billing.dao.Client2ServiceDAO;
import com.billing.models.Client2Service;
import com.billing.models.Client2ServiceId;

import java.util.Date;
import java.util.List;

public class Client2ServiceService extends CommonService<Client2Service, Client2ServiceId, Client2ServiceDAO> {
    public Client2ServiceService() {
        super(new Client2ServiceDAO());
    }

    public List<Client2Service> filter(String service_name, Date begin, Date end, boolean credit) {
        return dao.filter(service_name, begin, end, credit);
    }
}