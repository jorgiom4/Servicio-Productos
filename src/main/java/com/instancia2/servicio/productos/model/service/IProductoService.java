package com.instancia2.servicio.productos.model.service;

//import com.instancia2.servicio.productos.model.entity.Producto; --> Lo quitamos por usar el de commons
import com.instancia2.servicio.commons.model.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> findAll();
    public Producto findById(Long id);
    public Producto save(Producto producto);
    public void deleteById(Long id);

}
