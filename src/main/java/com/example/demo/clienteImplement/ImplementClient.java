package com.example.demo.clienteImplement;

import com.example.demo.iCliente.ICliente;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImplementClient implements ICliente {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Cliente> AllClient() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional
    public Cliente saveCliente(Cliente cliente) {

         return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);

    }


}
