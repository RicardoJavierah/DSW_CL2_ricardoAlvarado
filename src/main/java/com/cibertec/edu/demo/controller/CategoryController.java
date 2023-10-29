package com.cibertec.edu.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cibertec.edu.demo.exception.ResourceNotFoundException;
import com.cibertec.edu.demo.model.Producto;
import com.cibertec.edu.demo.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = {"https://cibertec.blackboard.com", "https://intranet.cibertec.edu.pe"})
//@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/producto")
public class CategoryController {

    private CategoryService categoryService;


    @GetMapping("")
    public ResponseEntity<List<Producto>> listarProducto(){
        List<Producto> productoList = new ArrayList<>();
        categoryService.listarCategorias()
                .forEach(productoList::add);
        if(productoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerCategoria(
            @PathVariable("id") Integer id){
        Producto producto = categoryService
                .obtenerCategoriaPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoria con el Id Nro. "+
                        id + " no existe."));

        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Producto> registrarCategoria(
            @RequestBody Producto producto
    ){
        return new ResponseEntity<>(
                categoryService.guardar(producto), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarCategoria(
            @PathVariable("id") Integer id,
            @RequestBody Producto producto
    ){
        Producto oldProducto = categoryService
                .obtenerCategoriaPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto con el Id Nro. "+
                        id + " no existe."));
        oldProducto.setNombre(producto.getNombre());
        oldProducto.setDescripcion(producto.getDescripcion());
        return new ResponseEntity<>(
                categoryService.guardar(oldProducto), HttpStatus.OK
        );
    }

}
