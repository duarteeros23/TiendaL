package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Clientes;
import com.erosduarte.Tienda.exception.ResourceNotFoundException;
import com.erosduarte.Tienda.repository.ClientesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesServiceImp implements   ClientesService {
    private final ClientesRepository clientesRepository;

    public ClientesServiceImp(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @Override
    public List<Clientes> listar() {
        return clientesRepository.findAll();
    }

    @Override
    public Clientes crear(Integer id, Clientes clientes) {
       clientes.setDpíCliente(id);
       return clientesRepository.save(clientes);
    }

    @Override
    public Clientes actualizar(Integer id, Clientes clientes) {
        Clientes existentes = buscarPorid(id);
        existentes.setNombreCliente(clientes.getNombreCliente());
        existentes.setApellidoCliente(clientes.getApellidoCliente());
        existentes.setEstadoCliente(clientes.getEstadoCliente());
        existentes.setDireccion(clientes.getDireccion());
        return clientesRepository.save(existentes);
    }

    @Override
    public Clientes buscarPorid(Integer id) {
        return clientesRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cliente con id no encontrado " + id));
    }

    @Override
    public void eliminar(Integer id) {
        if(!clientesRepository.existsById(id)){
            throw new ResourceNotFoundException(("Id de cliente no encontrado " + id));
        }
        clientesRepository.deleteById(id);
    }

}
