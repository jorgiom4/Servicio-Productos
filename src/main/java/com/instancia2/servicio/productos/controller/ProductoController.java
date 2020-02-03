package com.instancia2.servicio.productos.controller;

import com.instancia2.servicio.productos.model.entity.Producto;
import com.instancia2.servicio.productos.model.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class ProductoController {

    //@Autowired
    //private Environment env; // Para inyectar el Feing el valor del puerto

    @Value("${server.port}")
    private Integer port; // Para inyectar en RestTemplate el valor del puerto

    @Autowired
    private IProductoService productoService;

    @GetMapping("/saludar/{msg}")
    public String getSaludo(@PathVariable String msg){
        return "Hola desde Springboot " + msg;
    }

    @GetMapping("/listar")
    public List<Producto> listar(){
        // Obtenemos el listado de productos y asignamos el puerto
        return productoService.findAll().stream().map(prod -> {
            //prod.setPort(Integer.parseInt(env.getProperty("local.server.port"))); //--> Para usar con Feing
            prod.setPort(port); // Para usar con RestTemplate
            return prod;
        }).collect(Collectors.toList());
    }

    @GetMapping("/listar/{id}")
    public Producto findProducto(@PathVariable Long id) throws Exception {
        // Obtenemos el listado de productos y asignamos el puerto
        Producto producto = productoService.findById(id);
        //producto.setPort(Integer.parseInt(env.getProperty("local.server.port"))); //--> Para usar con Feing
        producto.setPort(port); // Para usar con RestTemplate

        /*
        // Simulamos un error para ver el funcionamiento de Hystrix
        boolean ok = false;
        if(!ok){
            throw new Exception("No se pudo cargar el producto");
        }
        */

        /*
        // Simulamos un timeout de 5 segundos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        return producto;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody Producto producto){
        return productoService.save(producto);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto editar(@RequestBody Producto producto, @PathVariable Long id){
        Producto productoDb = productoService.findById(id);
        productoDb.setNombre(producto.getNombre());
        productoDb.setPrecio(producto.getPrecio());
        productoDb.setCreateAt(producto.getCreateAt());

        productoService.save(productoDb);

        return productoDb;
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        productoService.deleteById(id);
    }

}
