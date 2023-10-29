package com.cibertec.edu.demo.repository;

import com.cibertec.edu.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    Optional<Producto> findByCategoryname(String categoryName);

    List<Producto>findByCategorynameContainingIgnoreCase(String filtro);

    @Query("SELECT c FROM Producto c WHERE c.nombre LIKE %:filtro%")
    List<Producto> filtrarCategoriasPorNombre(@Param("filtro") String filtro);

    @Query(value = "SELECT * FROM Producto WHERE nombre LIKE %:filtro%",
    nativeQuery = true)
    List<Producto> filtrarCategoriasPorNombreSQL(@Param("filtro") String filtro);




}
