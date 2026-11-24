package com.telovendo.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telovendo.backend.model.Producto;
import com.telovendo.backend.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listarProductos(){
        return productoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Producto obtenerPorId(Long id){
        return productoService.obtenerPorId(id);
    }

    @PostMapping
    public Producto creaProducto(@RequestBody Producto producto){
        return productoService.guardar(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detalles){
        return productoService.actualizar(id, detalles);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id){
        productoService.eliminar(id);
    }
}
