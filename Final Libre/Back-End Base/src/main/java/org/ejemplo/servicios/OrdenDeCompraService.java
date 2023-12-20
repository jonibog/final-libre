package org.ejemplo.servicios;

import org.ejemplo.repository.OrdenDeCompraRepository;
import org.ejemplo.repository.ProductoRepository;
import org.ejemplo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenDeCompraService {
    private OrdenDeCompraRepository ordenDeCompraRepository;
    private ProductoRepository productoRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public OrdenDeCompraService(OrdenDeCompraRepository ordenDeCompraRepository, ProductoRepository productoRepository,  UsuarioRepository usuarioRepository) {
        this.ordenDeCompraRepository = ordenDeCompraRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }
        public <EstadoOrdenCompra> OrdenDeCompra crearOrdenDeCompra(Long idProducto, int cantidad, EstadoOrdenCompra estado) {
            Object ordenDeCompra = null;
            return (OrdenDeCompra) ordenDeCompraRepository.save(ordenDeCompra);
        }

        public <EstadoOrdenCompra> OrdenDeCompra actualizarOrdenDeCompra(Long idOrden, int nuevaCantidad, EstadoOrdenCompra nuevoEstado) {
            Object ordenDeCompra = null;
            return (OrdenDeCompra) ordenDeCompraRepository.save(ordenDeCompra);
        }

        public OrdenDeCompra buscarOrdenDeCompra(Long idOrden) {
        return (OrdenDeCompra) ordenDeCompraRepository.findAll();
        }

    public ResponseEntity<OrdenDeCompra> buscarTodasLasOrdenes() {
        return null;
    }

    public <OrdenDeCompra> ResponseEntity<OrdenDeCompra> crearOrdenDeCompra(Long ordenDeCompra) {
        return null;
    }

    public ResponseEntity<OrdenDeCompra> actualizarOrdenDeCompra(Long ordenDeCompra) {
        return null;
    }
}


