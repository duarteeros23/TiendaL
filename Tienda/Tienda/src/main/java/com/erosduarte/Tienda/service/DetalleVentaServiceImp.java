package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.DetalleVenta;
import com.erosduarte.Tienda.exception.ResourceNotFoundException;
import com.erosduarte.Tienda.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImp implements DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaServiceImp(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public List<DetalleVenta> listar() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta crear(DetalleVenta detalleVenta) {
        detalleVenta.setCodigoDetalleVenta(null);
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public DetalleVenta actualizar(Integer id, DetalleVenta detalleVenta) {
        DetalleVenta existente = buscarPorId(id);
        existente.setCantidad(detalleVenta.getCantidad());
        existente.setPrecioUnitario(detalleVenta.getPrecioUnitario());
        existente.setSubtotal(detalleVenta.getSubtotal());
        existente.setIdProducto(detalleVenta.getIdProducto());
        existente.setIdVenta(detalleVenta.getIdVenta());
        return detalleVentaRepository.save(existente);
    }

    @Override
    public DetalleVenta buscarPorId(Integer id) {
        return detalleVentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de venta con id " + id + " no encontrado"));
    }

    @Override
    public void eliminar(Integer id) {
        if (!detalleVentaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id de detalle de venta no encontrado: " + id);
        }
        detalleVentaRepository.deleteById(id);
    }
}