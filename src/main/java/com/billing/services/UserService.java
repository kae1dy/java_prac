
package com.billing.services;

import com.billing.dao.ClientDAO;
import com.billing.dao.UserDAO;
import com.billing.models.Client;
import com.billing.models.User;

public class UserService extends CommonService<User, Integer, UserDAO>{
    public UserService(){
        super(new UserDAO());
    }

    public User checkValid(String login, String password){
        return dao.checkValid(login, password);
    }
}