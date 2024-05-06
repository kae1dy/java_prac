package com.billing;

import com.billing.models.Organization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrganizationTests {
    @Test
    public void test() {
        Organization org = new Organization();
        org.setId(1);
        org.setName("ОАО СБЕРБАНК");
        org.setInn("3620961634");

        Assertions.assertEquals(org.getId(), 1);
        Assertions.assertEquals(org.getName(), "ОАО СБЕРБАНК");
        Assertions.assertEquals(org.getInn(), "3620961634");
    }
}