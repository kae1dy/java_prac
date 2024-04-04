package com.billing;

import com.billing.models.Client;
import com.billing.models.Client2Service;
import com.billing.models.Client2ServiceId;
import com.billing.models.Service;
import com.billing.services.Client2ServiceService;
import com.billing.services.ClientService;
import com.billing.services.ServiceService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Client2ServiceTests {
    private static Client2Service client2Service;
    @BeforeAll
    public static void save(){
        ClientService clientService = new ClientService();
        ServiceService serviceService = new ServiceService();

        Date date = new Date(System.currentTimeMillis());
        client2Service = new Client2Service("1-24/01", clientService.findById(2), serviceService.findById(4), date, null);
        Client2ServiceService service = new Client2ServiceService();
        service.save(client2Service);
    }

    @AfterAll
    public static void delete(){
        Client2ServiceService service = new Client2ServiceService();
        service.delete(client2Service);
    }

    @Test
    public void test(){
        ClientService clientService = new ClientService();
        ServiceService serviceService = new ServiceService();

        Date date = new Date(System.currentTimeMillis());
        Client2Service cl = new Client2Service("Test", clientService.findById(3), serviceService.findById(2), date, null);
        Assertions.assertEquals(cl.getContract_num(), "Test");
    }

    @Test
    public void testFindById(){
        Client2ServiceService service = new Client2ServiceService();
        Client2Service client2Service = service.findById(new Client2ServiceId(1, 1));
        Assertions.assertEquals(client2Service.getContract_num(), "1-24/01");
        Assertions.assertNotNull(client2Service.getContract_begin());
        Assertions.assertNull(client2Service.getContract_end());
    }
    @Test
    public void testFindAll(){
        Client2ServiceService service = new Client2ServiceService();
        List<Client2Service> client2Service = service.findAll();

//        Assertions.assertEquals(client2Service.size(), 5);
        Assertions.assertEquals(client2Service.get(0).getContract_num(), "1-24/01");
        Assertions.assertEquals(client2Service.get(1).getContract_num(), "1-23/05");
        Assertions.assertEquals(client2Service.get(2).getContract_num(), "1-24/02");
        Assertions.assertEquals(client2Service.get(3).getContract_num(), "2-24/02");
        Assertions.assertEquals(client2Service.get(4).getContract_num(), "3-24/02");
    }
    @Test
    public void testService(){
        Client2ServiceService css = new Client2ServiceService();
        ClientService client = new ClientService();
        ServiceService service = new ServiceService();

        Date date = new Date(System.currentTimeMillis());
        Client2Service cs = new Client2Service("1-11/01", client.findById(3), service.findById(5), date, null);
        css.save(cs);

        Client2Service tmp = css.findById(cs.getId());
        Assertions.assertEquals(cs, tmp);

        cs.setContract_num("1-11/02");
        css.update(cs);
        cs = css.findById(cs.getId());

        Assertions.assertEquals("1-11/02", cs.getContract_num());
        css.deleteById(cs.getId());
        cs = css.findById(cs.getId());
        Assertions.assertNull(cs);
    }
    @Test
    public void testFilter() {
        Client2ServiceService client2ServiceService = new Client2ServiceService();

        List<Client2Service> cls = client2ServiceService.filter("Всё за 500", null, null, true);
        Assertions.assertEquals(cls.getFirst().getContract_num(), "1-24/01");

        Date date = new GregorianCalendar(2024, Calendar.APRIL, 4).getTime();

        cls = client2ServiceService.filter(null, date, null, true);
        Assertions.assertEquals(cls.get(0).getContract_num(), "1-11/01");
        Assertions.assertEquals(cls.get(1).getContract_num(), "1-24/01");
        Assertions.assertEquals(cls.get(2).getContract_num(), "1-11/01");

        cls = client2ServiceService.filter(null, null, date, false);
        List<Client2Service> tmp = client2ServiceService.findAll();

        Assertions.assertEquals(cls.size(), tmp.size());
    }
}