package com.billing.services;

import com.billing.dao.OrganizationDAO;
import com.billing.models.Organization;

public class OrganizationService extends CommonService<Organization, Integer, OrganizationDAO> {
    public OrganizationService() {
        super(new OrganizationDAO());
    }

    public Organization findByName(String name) {
        return dao.findByName(name);
    }
}