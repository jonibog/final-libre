package org.ejemplo.servicios;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exceptions.DetalleFacturaException;
import org.ejemplo.modelos.DetalleFactura;
import org.ejemplo.repository.DetalleFacturaRepository;
import org.ejemplo.repository.ProductoRepository;
import org.ejemplo.validations.DetalleFacturaValidations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DetalleFacturaService {
    DetalleFacturaRepository detalleFacturaRepository;

    ProductoRepository productoRepository;

    DetalleFacturaService(DetalleFacturaRepository detalleFacturaRepository, ProductoRepository productoRepository){
        this.detalleFacturaRepository = detalleFacturaRepository;
        this.productoRepository = productoRepository;
    }
    public DetalleFactura guardar(DetalleFactura detalleFactura) throws DetalleFacturaException {
        DetalleFacturaValidations.validateForCreate(productoRepository,detalleFacturaRepository.findAll(), detalleFactura);

        return saveDetalle(detalleFactura);
    }


    public DetalleFactura actualizar(DetalleFactura detalleFactura) throws DetalleFacturaException {
        DetalleFacturaValidations.validateForUpdate(productoRepository, detalleFacturaRepository.findAll(), detalleFactura);
        return saveDetalle(detalleFactura);
    }

    public List<DetalleFactura> retornar(){
        return detalleFacturaRepository.findAll();
    }

    public void borrar(Integer id) {
        detalleFacturaRepository.deleteById(id);
    }

    private DetalleFactura saveDetalle(DetalleFactura detalleFactura) {
        Optional<Producto> optionalProducto = productoRepository.findById(detalleFactura.getProducto().getCodigo());
        Double precio = optionalProducto.get().getPrecio();
        detalleFactura.setPrecioUnitario(precio);
        detalleFactura.setPrecioTotal(precio * detalleFactura.getCantidad());
        return detalleFacturaRepository.save(detalleFactura);
    }
}
