package com.cibertec.edu.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "description")
    private String descripcion;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "fechaVencimiento")
    private String fechaVencimiento;
}
