package com.cibertec.edu.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cibertec.edu.demo.exception.ResourceNotFoundException;
import com.cibertec.edu.demo.model.Producto;
import com.cibertec.edu.demo.service.ProductoService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/producto")
public class ProductoController {

    private ProductoService productoService;


    @GetMapping("")
    public ResponseEntity<List<Producto>> listarProducto(){
        List<Producto> productoList = new ArrayList<>();
        productoService.listarProducto()
                .forEach(productoList::add);
        if(productoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerCategoria(
            @PathVariable("id") Integer id){
        Producto producto = productoService
                .obtenerProductoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoria con el Id Nro. "+
                        id + " no existe."));

        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Producto> registrarCategoria(
            @RequestBody Producto producto
    ){
        return new ResponseEntity<>(
                productoService.guardar(producto), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarCategoria(
            @PathVariable("id") Integer id,
            @RequestBody Producto producto
    ){
        Producto oldProducto = productoService
                .obtenerProductoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto con el Id Nro. "+
                        id + " no existe."));
        oldProducto.setNombre(producto.getNombre());
        oldProducto.setDescripcion(producto.getDescripcion());
        return new ResponseEntity<>(
                productoService.guardar(oldProducto), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(
            @PathVariable("id") Integer id
    ) {
        productoService.eliminarCategoriaPorId(id);
        return ResponseEntity.noContent().build();
    }

}
