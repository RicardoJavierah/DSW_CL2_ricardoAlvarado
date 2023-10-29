package com.cibertec.edu.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.cibertec.edu.demo.model.Producto;
import com.cibertec.edu.demo.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private ProductoRepository productoRepository;

    public List<Producto> listarCategorias(){
        return productoRepository.findAll();
    }
    public Producto guardar(Producto producto){
        return productoRepository.save(producto);
    }
    public Optional<Producto> obtenerCategoriaPorId(Integer id){
        Optional<Producto> category = productoRepository.findById(id);
        if(category.isEmpty()){
            return Optional.empty();
        }else
            return category;
    }

    public Optional<Producto> obtenerCatogoriaPorNombre(String categoryName){
        Optional<Producto> category = productoRepository.findByCategoryname(categoryName);
        if(category.isEmpty())
            return  Optional.empty();
        else
            return category;
    }

    public List<Producto> obtenerCategoriasPorFiltro(String filtro){
        return productoRepository.filtrarCategoriasPorNombreSQL(filtro);
    }


}
