package org.ejemplo.servicios;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exceptions.ProductoException;
import org.ejemplo.repository.ProductoRepository;
import org.ejemplo.validations.ProductoValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    public String guardarProducto(Producto producto) throws ProductoException {
        ProductoValidations.validateProductoForCreate(productoRepository.findAll(), producto);
        return saveProduct(producto);
    }

    public String actualizarProducto(Producto producto) throws ProductoException {
        ProductoValidations.validateProductoForUpdate(productoRepository.findAll(), producto);
        return saveProduct(producto);
    }

    public List<Producto> retornarProductos(){
        return productoRepository.findAll();
    }

    public void borrarProductos(String codigo) throws ProductoException {
        ProductoValidations.validateProductoForDelete(productoRepository.findAll(), codigo);
        productoRepository.deleteById(codigo);
    }

    public Producto findProducto(String codigo) {
        return productoRepository.findById(codigo).orElse(null);
    }


    private String saveProduct(Producto producto) {
        producto.setFechaDeActualizacion(new Date());
        productoRepository.save(producto);
        return "producto cargado correctamente";
    }
}
