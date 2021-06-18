package com.sample;


public class Cliente {

  private long id;
  private String nombre;
  private String apellido;
  private String dni;
  private String createAt;
  private String email;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }


  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }


  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }


  public String getCreateAt() {
    return createAt;
  }

  public void setCreateAt(String createAt) {
    this.createAt = createAt;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
