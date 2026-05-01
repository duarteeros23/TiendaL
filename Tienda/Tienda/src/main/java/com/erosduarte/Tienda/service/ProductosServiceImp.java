package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Productos;
import com.erosduarte.Tienda.exception.ResourceNotFoundException;
import com.erosduarte.Tienda.repository.ProductosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServiceImp implements ProductosService {

    private final ProductosRepository productosRepository;

    public ProductosServiceImp(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    @Override
    public List<Productos> listar() {
        return productosRepository.findAll();
    }

    @Override
    public Productos crear(Productos productos) {
        productos.setCodigoProducto(null);
        return productosRepository.save(productos);
    }

    @Override
    public Productos actualizar(Integer id, Productos productos) {
        Productos existente = buscarPorId(id);
        existente.setNombreProducto(productos.getNombreProducto());
        existente.setPrecioProducto(productos.getPrecioProducto());
        existente.setStockProducto(productos.getStockProducto());
        existente.setEstadoProducto(productos.getEstadoProducto());
        return productosRepository.save(existente);
    }

    @Override
    public Productos buscarPorId(Integer id) {
        return productosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con id " + id + " no encontrado"));
    }

    @Override
    public void eliminar(Integer id) {
        if (!productosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id de producto no encontrado: " + id);
        }
        productosRepository.deleteById(id);
    }
}