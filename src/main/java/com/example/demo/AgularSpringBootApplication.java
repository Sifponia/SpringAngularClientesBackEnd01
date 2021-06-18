package com.example.demo;

import com.example.demo.clienteImplement.ImplementClient;
import com.example.demo.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AgularSpringBootApplication implements CommandLineRunner {

    @Autowired
    private ImplementClient servicesClient;

    public static void main(String[] args) {
        SpringApplication.run(AgularSpringBootApplication.class, args);
    }


    //Realizar operaciones por consola
    @Override
    public void run(String... args) throws Exception {

        //saveliente();


       //allClientes();


    }

    private void allClientes() {
        List<Cliente> listClient = servicesClient.AllClient();

        listClient.forEach(x -> {
            System.out.println(x.toString());
        });

    }


    private void saveliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Marloy");
        cliente.setApellido("Test");
        cliente.setCreateAt("02/04/2021");
        cliente.setDni("12345678A");
        cliente.setEmail("marlon@hotmail.com");
        servicesClient.saveCliente(cliente);

        System.out.println("********************** DATA SAVE CLIENT *************************");
    }
}
