package com.telovendo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telovendo.backend.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
 // Se conectara con la BD mediante JpaRepository utilizando las estructuras de datos
 // que indicamos en la herencia (en este caso, <Producto, Long>)
}
