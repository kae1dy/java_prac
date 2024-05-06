
package com.billing.services;

import com.billing.dao.AccountDAO;
import com.billing.dao.ClientDAO;
import com.billing.dao.OrganizationDAO;
import com.billing.models.Account;
import com.billing.models.Client;
import com.billing.models.Organization;

import java.math.BigDecimal;

public class OrganizationService extends CommonService<Organization, Integer, OrganizationDAO>{
    public OrganizationService(){
        super(new OrganizationDAO());
    }

    public Organization findByName(String name) {
        return dao.findByName(name);
    }
}