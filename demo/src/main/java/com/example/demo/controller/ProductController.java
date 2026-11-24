package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //#region METODOS (Operaciones CRUD)

    // 1. Listar todos los productos (GET)
    @GetMapping
    public List<Product> listarProductos(){
        return productService.obtenerTodos();
    }

    // 2. Obtener un producto por su id (GET)
    @GetMapping("/{id}")
    public Product obtenerPorId(Long id){
        return productService.obtenerPorId(id);
    }

    // 3. Creamos un producto en la BD (POST)
    @PostMapping
    public Product crearProducto(Product product){
        return productService.guardar(product);
    }
    
    // 4. Actualizar un producto existente por ID (PUT)
    @PutMapping("/{id}")
    public Product actualizarProducto(@PathVariable Long id,@RequestBody Product productDetails){
        return productService.actualizar(id, productDetails);
    }

    // 5. Eliminar un producto existente por ID (DELETE)
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id){
        productService.eliminar(id);
    }

    //#endregion
    
}
