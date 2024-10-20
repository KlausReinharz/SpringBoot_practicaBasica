package com.klaus.core.controllers;

import com.klaus.core.domain.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteRestControllerResponse {
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

    /*********************************ResponseEntity****************************************************/

    @GetMapping("/{userName}")
    public ResponseEntity<?> getCliente1(@PathVariable String userName){
        return ResponseEntity.ok(clientes.stream().
                filter(cliente -> cliente.getUsername().equalsIgnoreCase(userName)).
                findFirst().orElseThrow());
    }
    /***************************************************************************************/


    /****************************ResponseEntity**********************************************/
    @PostMapping
    public ResponseEntity<?>agregarCLiente(@RequestBody Cliente cliente){
        clientes.add(cliente);
        //obteniendo URL de servicio

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userName}")
                .buildAndExpand(cliente.getNombre())
                .toUri();
        return ResponseEntity.created(location).body(cliente);
    }
    /*****************************************************************************/

    /***************************************ResponseEntity**************************************************/
    @PutMapping
    public  ResponseEntity<?> modificarCliente1(@RequestBody Cliente cliente){
        Cliente clienteEncontrado = clientes.stream().
                filter(cli->cli.getUsername().equalsIgnoreCase(cliente.getUsername())).
                findFirst().orElseThrow();
        clienteEncontrado.setPassword(cliente.getPassword());
        clienteEncontrado.setNombre(cliente.getNombre());

        return ResponseEntity.ok(clienteEncontrado);
    }
    /*****************************************************************************************/

    @DeleteMapping("/clientes/{userName}")
    public void deleteCliente (@PathVariable String userName){
        Cliente clienteEncontrado = clientes.stream().
                filter(cli -> cli.getUsername().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();
        clientes.remove(clienteEncontrado);
    }

    @DeleteMapping("{userName}")
    public void deleteC1iente (@PathVariable String userName){
        Cliente clienteEncontrado = clientes.stream().
                filter(cli -> cli.getUsername().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();
        clientes.remove(clienteEncontrado);
    }
    /*******************************************************************************/
    @DeleteMapping("/{userName}")
    public ResponseEntity<?> deleteCliente1(@PathVariable String userName){
        Cliente clienteEcontrado = clientes.stream().
                filter(cli-> cli.getUsername().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();
        clientes.remove(clienteEcontrado);
        return ResponseEntity.noContent().build();
    }

}
