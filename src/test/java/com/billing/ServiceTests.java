package com.billing;

import com.billing.models.Service;
import com.billing.services.ServiceService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ServiceTests {
    private static Service service;
    @BeforeAll
    public static void save(){
        service = new Service();
        service.setName("Домашнее ТВ");
        ServiceService serviceService = new ServiceService();
        serviceService.save(service);
    }

    @AfterAll
    public static void delete(){
        ServiceService serviceService = new ServiceService();
        serviceService.delete(service);
    }

    @Test
    public void test(){
        Service m = new Service("Test");
        Assertions.assertEquals(m.getName(), "Test");
    }

    @Test
    public void testFindById(){
        ServiceService serviceService = new ServiceService();
        Service service = serviceService.findById(1);
        Assertions.assertEquals(service.getName(), "Всё за 500");
        Assertions.assertEquals(service.getId(), 1);
    }

    @Test
    public void testFindByName(){
        ServiceService serviceService = new ServiceService();
        Service service = serviceService.findByName("Семья");
        Assertions.assertEquals(service.getName(), "Семья");
        Assertions.assertEquals(service.getId(), 3);
    }

    @Test
    public void testFindAll(){
        ServiceService serviceService = new ServiceService();
        List<Service> service = serviceService.findAll();

//        Assertions.assertEquals(service.size(), 10);
        Assertions.assertEquals(service.get(0).getName(), "Всё за 500");
        Assertions.assertEquals(service.get(1).getName(), "Всё за 1000");
        Assertions.assertEquals(service.get(2).getName(), "Семья");
        Assertions.assertEquals(service.get(3).getName(), "Семья+");
        Assertions.assertEquals(service.get(4).getName(), "Только звонки");
        Assertions.assertEquals(service.get(5).getName(), "Только интернет");
        Assertions.assertEquals(service.get(6).getName(), "Поминутный");
    }
    @Test
    public void testService(){
        ServiceService serviceService = new ServiceService();
        Service s = serviceService.findByName("Test4");
        if (s != null){
            serviceService.delete(s);
        }

        s = new Service("Test4");
        serviceService.save(s);
        Service tmp = serviceService.findById(s.getId());
        Assertions.assertEquals(s, tmp);

        s = serviceService.findByName("Test4");
        s.setName("Test5");
        serviceService.update(s);
        s = serviceService.findById(s.getId());

        Assertions.assertEquals("Test5", s.getName());

        serviceService.deleteById(s.getId());
        s = serviceService.findById(s.getId());
        Assertions.assertNull(s);
    }
}