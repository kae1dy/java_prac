package com.billing;

import com.billing.models.Client;
import com.billing.services.ClientService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ClientTests {
    private static Client client;

    @BeforeAll
    public static void save() {
        client = new Client("Морозов Илья Федорович");
        ClientService clientService = new ClientService();
        clientService.save(client);
    }

    @AfterAll
    public static void delete() {
        ClientService clientService = new ClientService();
        clientService.delete(client);
    }

    @Test
    public void test() {
        Client cl = new Client("Test");
        Assertions.assertEquals(cl.getName(), "Test");
    }

    @Test
    public void testFindById() {
        ClientService clientService = new ClientService();
        Client client = clientService.findById(1);
        Assertions.assertEquals(client.getName(), "Абрамов Михаил Андреевич");
        Assertions.assertEquals(client.getId(), 1);
    }

    @Test
    public void testFindByName() {
        ClientService clientService = new ClientService();
        Client client = clientService.findByName("Артамонова Юлия Максимовна");
        Assertions.assertEquals(client.getName(), "Артамонова Юлия Максимовна");
        Assertions.assertEquals(client.getId(), 5);
    }

    @Test
    public void testFindAll() {
        ClientService clientService = new ClientService();
        List<Client> client = clientService.findAll();

//        Assertions.assertEquals(client.size(), 5);
        Assertions.assertEquals(client.get(0).getName(), "Абрамов Михаил Андреевич");
        Assertions.assertEquals(client.get(1).getName(), "Валявкин Максим Александрович");
        Assertions.assertEquals(client.get(2).getName(), "Кабанов Иван Юрьевич");
        Assertions.assertEquals(client.get(3).getName(), "Митрофанов Алексей Антонович");
        Assertions.assertEquals(client.get(4).getName(), "Артамонова Юлия Максимовна");
    }

    @Test
    public void testService() {
        ClientService clientService = new ClientService();
        Client s = clientService.findByName("Test");
        if (s != null) {
            clientService.delete(s);
        }

        s = new Client("Test");
        clientService.save(s);
        Client tmp = clientService.findById(s.getId());
        Assertions.assertEquals(s, tmp);

        s = clientService.findByName("Test");
        s.setName("Test1");
        clientService.update(s);
        s = clientService.findById(s.getId());

        Assertions.assertEquals("Test1", s.getName());

        clientService.deleteById(s.getId());
        s = clientService.findById(s.getId());
        Assertions.assertNull(s);
    }
}