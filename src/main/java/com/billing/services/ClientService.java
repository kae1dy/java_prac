
package com.billing.services;

import com.billing.dao.ClientDAO;
import com.billing.models.Client;

public class ClientService extends CommonService<Client, Integer, ClientDAO>{
    public ClientService(){
        super(new ClientDAO());
    }

    public Client findByName(String name){
        return dao.findByName(name);
    }
}