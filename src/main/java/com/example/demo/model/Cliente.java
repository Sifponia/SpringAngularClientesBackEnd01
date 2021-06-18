package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Size(min = 2, max = 20, message = "Ingrese un nombre válido")
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni")
    private String dni;

    @Column(name = "create_at")
    private String createAt;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    //Insertar fecha de creacion
    @PrePersist
    public void prepareCreate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        createAt = sdf.format(new Date());

    }

}
