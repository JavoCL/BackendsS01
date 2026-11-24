package com.telovendo.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telovendo.backend.model.Producto;
import com.telovendo.backend.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    //#region REGLAS DE NEGOCIO

    // 1. Obtencion de todos los elementos de la coleccion de Productos
    public List<Producto> obtenerTodos(){
        return productoRepository.findAll();
    }

    // 2. Obtencion de 1 producto por su id
    public Producto obtenerPorId(Long id){
        return productoRepository.findById(id).orElseThrow(()-> new RuntimeException("Proucto no encontrado con ID: " + id));
    }

    // 3. Guardar 1 producto nuevo
    public Producto guardar(Producto producto){
        // Si el producto no se entrega, lanza excepcion
        if(producto == null)
            throw new IllegalArgumentException("El producto debe existir para enviarse a la BD");
        
        // Si el nombre del producto esta nulo o vacío, lanza excepcion
        if(producto.getNombre() == null || producto.getNombre().trim().isEmpty())
            throw new IllegalArgumentException("El nombre del producto no debe ser ni nulo ni vacío");

        // Si el precio es 0 o negativo, lanza excepcion
        if(producto.getPrecio() <= 0)
            throw new IllegalArgumentException("El precio del producto debe ser positivo");

        // Si no tiene direccion de la imagen del producto, lanza excepcion
        if(producto.getUrlImagen() == null || producto.getUrlImagen().trim().isEmpty())
            throw new IllegalArgumentException("El url de la imagen del producto no debe ser ni nulo ni vacío");

        // En caso de que todo se validó correctamente, guarda el producto en la BD
        return productoRepository.save(producto);
    }

    // 4. Actualizamos con valores nuevos a un producto
    // identificado por su id
    public Producto actualizar(Long id, Producto detalles){

        // Buscamos el producto en la BD. Si no existe, esto va a arrojar excepcion
        // y termina el metodo 'actualizar'
        Producto producto = obtenerPorId(id);

        if(detalles.getNombre() != null && detalles.getNombre().trim().isEmpty() == false)
            producto.setNombre(detalles.getNombre());

        if(detalles.getDescripcion() != null && detalles.getDescripcion().trim().isEmpty() == false)
            producto.setDescripcion(detalles.getDescripcion());

        if(detalles.getPrecio() > 0f)
            producto.setPrecio(detalles.getPrecio());

        if(detalles.getUrlImagen() != null && detalles.getUrlImagen().trim().isEmpty() == false)
            producto.setUrlImagen(detalles.getUrlImagen());

        return productoRepository.save(producto);
    }

    public void eliminar(Long id){
        // Si no existe el producto en BD, arrojo excepcion
        if(productoRepository.existsById(id) == false)
            throw new RuntimeException("Producto no encontrado con id: " + id);

        // En caso que exista, invoco el metodo de eliminacion a traves del repository
        productoRepository.deleteById(id);
    }

    //#endregion
    

}
