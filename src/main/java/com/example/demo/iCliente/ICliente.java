package com.example.demo.iCliente;

import com.example.demo.model.Cliente;

import java.util.List;

public interface ICliente {

     //Listar
     List<Cliente> AllClient();
     //Guardar
      Cliente saveCliente(Cliente cliente);
     //Buscar por Id
     Cliente findById(Integer id);
     //Eliminar
     void deleteCliente(Integer id);

}
