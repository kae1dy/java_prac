package com.billing.services;

import com.billing.dao.ServiceDAO;
import com.billing.models.Service;

public class ServiceService extends CommonService<Service, Integer, ServiceDAO> {
    public ServiceService() {
        super(new ServiceDAO());
    }

    public Service findByName(String name) {
        return dao.findByName(name);
    }
}