package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Ventas;
import com.erosduarte.Tienda.exception.ResourceNotFoundException;
import com.erosduarte.Tienda.repository.VentasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasServiceImp implements VentasService {

    private final VentasRepository ventasRepository;

    public VentasServiceImp(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @Override
    public List<Ventas> listar() {
        return ventasRepository.findAll();
    }

    @Override
    public Ventas crear(Ventas ventas) {
        ventas.setCodigoVenta(null);
        return ventasRepository.save(ventas);
    }

    @Override
    public Ventas actualizar(Integer id, Ventas ventas) {
        Ventas existente = buscarPorId(id);
        existente.setFechaVenta(ventas.getFechaVenta());
        existente.setTotal(ventas.getTotal());
        existente.setEstado(ventas.getEstado());
        existente.setIdCliente(ventas.getIdCliente());
        existente.setIdUsuario(ventas.getIdUsuario());
        return ventasRepository.save(existente);
    }

    @Override
    public Ventas buscarPorId(Integer id) {
        return ventasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta con id " + id + " no encontrada"));
    }

    @Override
    public void eliminar(Integer id) {
        if (!ventasRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id de venta no encontrado: " + id);
        }
        ventasRepository.deleteById(id);
    }
}