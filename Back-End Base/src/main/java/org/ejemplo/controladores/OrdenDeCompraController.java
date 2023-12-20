package org.ejemplo.controladores;

import org.ejemplo.modelos.Autentication;
import org.ejemplo.servicios.AuthenticationService;
import org.ejemplo.servicios.OrdenDeCompra;
import org.ejemplo.servicios.OrdenDeCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.naming.AuthenticationException;
import java.util.List;

@RestController
@RequestMapping("/ordenes-de-compra")
public class OrdenDeCompraController {

    private  org.ejemplo.servicios.OrdenDeCompraService OrdenDeCompraService;
    private OrdenDeCompraService ordenDeCompraService;
    private AuthenticationService authenticationService;

    @Autowired
    public OrdenDeCompraController(OrdenDeCompraService ordenDeCompraService, AuthenticationService authenticationService){
        this.OrdenDeCompraService = ordenDeCompraService;
        this.authenticationService = authenticationService;
    }
        @PostMapping
        public <OrdenDeCompra, OrdenDeCompraRequest> ResponseEntity<OrdenDeCompra> crearOrdenDeCompra(@RequestBody OrdenDeCompraRequest request, @RequestHeader String token) throws AuthenticationException {
            Autentication autentication = authenticationService.validarToken(token);
            Long ordenDeCompra = null;
            return ordenDeCompraService.crearOrdenDeCompra(ordenDeCompra);
        }
        @PutMapping("/{id}")
        public <ActualizarOrdenDeCompraRequest> ResponseEntity<OrdenDeCompra> actualizarOrdenDeCompra(@PathVariable Long id, @RequestBody ActualizarOrdenDeCompraRequest request, @RequestHeader String token ) throws AuthenticationException {
            Autentication autentication = authenticationService.validarToken(token);
            Long ordenDeCompra = null;
            return ordenDeCompraService.actualizarOrdenDeCompra(ordenDeCompra);
        }
        @GetMapping("/{id}")
        public ResponseEntity<OrdenDeCompra> buscarOrdenDeCompra(@PathVariable Long id, @RequestHeader String token) throws AuthenticationException {
            Autentication autentication = authenticationService.validarToken(token);
            return ordenDeCompraService.buscarTodasLasOrdenes();
        }
    }

