package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

// Capa de servicio: encargada de la logica del negocio
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // 1. Obtener todos los elementos de productos desde la BD
    public List<Product> obtenerTodos(){

        List<Product> productos = productRepository.findAll();

        return productos;
    }

    // 2. Obtener un elemento por ID
    public Product obtenerPorId(Long id){
        return productRepository.findById(id)
            .orElseThrow(()->new RuntimeException("Producto no encontrado con id: " + id));
    }

    // 3. Guardar un nuevo elemento
    public Product guardar(Product product){
        // Si no asigne nada, no puedo guardar
        if(product == null)
            throw new IllegalArgumentException("Los datos del producto no pueden ser nulos");

        // Si no tiene nombre, no puedo guardar
        if(product.getName() == null || product.getName().trim().isEmpty())
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo");

        // Si asigne un precio negativo, no puedo guardar
        if(product.getPrice() <= 0)
            throw new IllegalArgumentException("El precio del producto debe ser positivo");        

        // Si todo esta bien, guardarmos en la BD mediante el repositorio
        return productRepository.save(product);
    }

    // 4. Actualizar un producto (con validacion)
    public Product actualizar(Long id, Product productDetails){

        // Primero se busca el producto en la BD
        Product product = obtenerPorId(id);

        // Actualizamos los campos desde los nuevos detalles
        // SOLO si vienen bien formateados
        if(productDetails.getName() != null && productDetails.getName().trim().isEmpty() == false)
            product.setName(productDetails.getName());

        if(productDetails.getPrice() > 0)
            product.setPrice(productDetails.getPrice());

        return productRepository.save(product);
    }

    // 5. Eliminacion de un producto con id indicado
    public void eliminar(Long id){
        // Buscamos si existe el producto indicado por id
        // Si no, lanzamos excepcion e informamos
        if(productRepository.existsById(id) == false)
            throw new RuntimeException("No se puede eliminar. Producto no encontrado con id: " + id);

        // Si lo encontró, lo eliminamos a traves del repositorio
        productRepository.deleteById(id);
    }
    

}
