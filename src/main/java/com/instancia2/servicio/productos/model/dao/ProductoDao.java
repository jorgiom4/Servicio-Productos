package com.instancia2.servicio.productos.model.dao;

import com.instancia2.servicio.productos.model.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoDao extends CrudRepository<Producto, Long> {

}
