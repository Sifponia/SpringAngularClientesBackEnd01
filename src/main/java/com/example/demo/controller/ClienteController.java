package com.example.demo.controller;

import com.example.demo.clienteImplement.ImplementClient;
import com.example.demo.model.Cliente;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
El intercambio de recursos de origen cruzado (CORS) es un protocolo estándar que define
la interacción entre un navegador y un servidor para manejar de forma segura las solicitudes HTTP de origen cruzado
 */
//@CrossOrigin(origins = "http://localhost:4200/")
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/home")
public class ClienteController {

    @Autowired
    private ImplementClient implementClientService;

    //Devuelve todos los Clientes
    @GetMapping("/clientes")
    public List<Cliente> listClient() {
        return implementClientService.AllClient();
    }


    /*********************************************************************************************************
     * ESTE METODO NO CONTROLA LOS MENSAJES DE ERROR
     * //Buscar un cliente por ID
     *
     * @GetMapping("/clientes/{id}") public Cliente showClient(@PathVariable("id") Integer id) {
     * return implementClientService.findById(id);
     * }
     */
    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> showClient(@PathVariable("id") Integer id) {
        Cliente cliente = null;
        Map<String, Object> stringObjectMap = new HashMap<>();

        try {
            cliente = implementClientService.findById(id);

        } catch (DataAccessException e) {
            stringObjectMap.put("mensaje", "Error al realizar la consulta");
            stringObjectMap.put("error", e.getLocalizedMessage());

            return new ResponseEntity<Map<String, Object>>(stringObjectMap, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if (cliente == null) {
            stringObjectMap.put("mensaje", +id + " no encontrado");
            return new ResponseEntity<Map<String, Object>>(stringObjectMap, HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }


    //Crear Client

    /***************************************************************************************************
   //  @PostMapping("/clientes")
     //@ResponseStatus(HttpStatus.CREATED)//Si tod0 estab bien mostrata el codigo 201 'Create Contend'
     public Cliente saveClient(@RequestBody Cliente client) {
     return implementClientService.saveCliente(client);
     }**/
    //Con manejo de Error
    @PostMapping("/clientes")
    public ResponseEntity<?> saveClient(@RequestBody Cliente client) {

        Cliente clientNew;
        Map<String, Object> stringObjectMap = new HashMap<>();
        try {
           /* if (client.getNombre() == null) {
                stringObjectMap.put("error", "Ingrese un nombre");
                System.out.println("*************** " + client.getNombre() + " ************************");
                return new ResponseEntity<Map<String, Object>>(stringObjectMap, HttpStatus.FOUND);
            }*/

            clientNew = implementClientService.saveCliente(client);

        } catch (DataAccessException dataAccessException) {
            stringObjectMap.put("mensaje", "Error al realizar la consulta");
            stringObjectMap.put("error", dataAccessException.getLocalizedMessage() + "\n" + dataAccessException.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(stringObjectMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        stringObjectMap.put("mensaje", "Cliente creado con exito");
        stringObjectMap.put("clienteNew", clientNew);

        return new ResponseEntity<Map<String, Object>>(stringObjectMap, HttpStatus.CREATED);
    }


    //Update

    /*******************************************************************************************************
   //  @PutMapping("/clientes/{id}")
     //@ResponseStatus(HttpStatus.CREATED)//Si tod0 estab bien mostrata el codigo 201 'Create Contend'
     public Cliente updateCliente(@RequestBody Cliente cliente, @PathVariable Integer id) {
     Cliente clientExist = implementClientService.findById(id);

     clientExist.setNombre(cliente.getNombre());
     clientExist.setApellido(cliente.getApellido());
     clientExist.setDni(cliente.getDni());
     clientExist.setEmail(cliente.getEmail());
     return implementClientService.saveCliente(cliente);

     }*/
    @PutMapping("/clientes/{id}")
    //@ResponseStatus(HttpStatus.CREATED)//Si tod0 estab bien mostrata el codigo 201 'Create Contend'
    public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente, @PathVariable Integer id) {
        Cliente clientExist = implementClientService.findById(id);

        Cliente clientNew = null;
        Map<String, Object> stringObjectMap = new HashMap<>();


        if (clientExist == null) {
            stringObjectMap.put("mensaje", "El cliente con el el ID " + clientExist.getId() + " no existe");
            return new ResponseEntity<Map<String, Object>>(stringObjectMap, HttpStatus.FOUND);
        }


        try {

            clientExist.setNombre(cliente.getNombre());
            clientExist.setApellido(cliente.getApellido());
            clientExist.setDni(cliente.getDni());
            clientExist.setEmail(cliente.getEmail());


            clientNew = implementClientService.saveCliente(cliente);


        } catch (DataAccessException dataAccessException) {
            stringObjectMap.put("mensaje", "Error al realizar la consulta");
            stringObjectMap.put("error", dataAccessException.getLocalizedMessage() + "\n" + dataAccessException.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(stringObjectMap, HttpStatus.INTERNAL_SERVER_ERROR);

        }


        stringObjectMap.put("mensaje", "Cliente actualizado!");
        stringObjectMap.put("clientUpdate", clientNew);


        return new ResponseEntity<Map<String, Object>>(stringObjectMap, HttpStatus.CREATED);

    }


    //Delete
    /*
    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)//Si tod0 estab bien mostrata el codigo 201 'Create Contend'
    public void delete(@PathVariable Integer id) {
        implementClientService.deleteCliente(id);

    }*/
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> stringObjectMap = new HashMap<>();

        try {

            implementClientService.deleteCliente(id);

        } catch (DataAccessException dataAccessException) {
            stringObjectMap.put("mensaje", "Error al eliminar datos");
            stringObjectMap.put("error", dataAccessException.getLocalizedMessage() + "\n" + dataAccessException.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(stringObjectMap, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        stringObjectMap.put("mensaje", "Exito al eliminar Cliente");
        return new ResponseEntity<Map<String, Object>>(stringObjectMap, HttpStatus.OK);
    }

}


