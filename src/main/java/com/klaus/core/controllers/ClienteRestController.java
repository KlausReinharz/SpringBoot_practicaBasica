package com.klaus.core.controllers;

import com.klaus.core.domain.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteRestController {
    private List<Cliente> clientes = new ArrayList<>(Arrays.asList(
            new Cliente("arm", "1234", "Armstrong"),
            new Cliente("ald", "1234", "Aldrin"),
            new Cliente("col", "1234", "Collins")
        )
    );


    @GetMapping("/clientes")
    public List<Cliente> getClientes() {
        return clientes;
    }

    //recuperar usuario UserName
    @GetMapping("/clientes/{userName}")
    public Cliente getCliente(@PathVariable String userName){
        return clientes.stream().
                filter(cliente -> cliente.getUsername().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();
    }

    /*
    @GetMapping("/clientes/{userName}")
    public Cliente getCliente(@PathVariable String userName){
        for(Cliente cli: clientes){
            if(cli.getUsername().equalsIgnoreCase(userName)){
                return cli;
            }
        }
        return null;
    }*/
    /*RequestBody los datos pasados son Json y los transforma en objecto*/
    @PostMapping("/clientes")
    public Cliente altaCliente(@RequestBody Cliente cliente){
        clientes.add(cliente);
        return cliente;
    }

    /*Para mdodificar primero se debe de buscar el usuario por el userName -> similar a recuperar userName*/

    @PutMapping("/clientes")
    public Cliente madificacionCliente(@RequestBody Cliente cliente){
        Cliente clienteEncontrado = clientes.stream()
                .filter(cli -> cli.getUsername().equalsIgnoreCase(cliente.getUsername())).
                findFirst().orElseThrow();
        clienteEncontrado.setPassword(cliente.getPassword());
        clienteEncontrado.setNombre(cliente.getNombre());
        return  cliente;

        /*el identificar unico sera cliente que es imutable es el unicio que se modificara
        * lo que sera clave para localizar el cliente que se necesita modificar
        * en caso de que el cliente existe en el sistemas se le setean los nuevos
        * valores para los atributos password y nombre*/
    }
}
