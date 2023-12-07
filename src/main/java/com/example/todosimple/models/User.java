package com.example.todosimple.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.lang.Override;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = User.TABLE_NAME)
public class User {

  // para garantir que as regras das anotações aconteçam com o contrato da
  // interface(ñ será implementado contrato).
  public interface CreateUser {
  }

  public interface UpdateUser {
  }

  public static final String TABLE_NAME = "users";

  @Id
  // Para declarar que será a estratégia do identificador único.
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true)
  private Long id;

  @Column(name = "username", length = 100, nullable = false)
  @NotNull(groups = CreateUser.class)
  @NotEmpty(groups = CreateUser.class)
  @Size(groups = CreateUser.class, min = 2, max = 100) // define caracteres.
  private String username;

  @Column(name = "password", length = 50, nullable = false)
  @JsonProperty(access = Access.WRITE_ONLY) // modo escrita e não leitura(ñ retorna a senha para o front)
  @NotNull(groups = { CreateUser.class, UpdateUser.class })
  @NotEmpty(groups = { CreateUser.class, UpdateUser.class }) // array para verificar, também, a regra na atualização de
                                                             // senha
  @Size(groups = { CreateUser.class, UpdateUser.class }, min = 6, max = 50)
  private String password;

  // Não é passado coleções em construtores do spring porque afeta a performance
  // private List<Task> tasks = new ArrayList<Task>();

  public User() {
  }

  public User(Long id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof User)) {
      return false;
    }

    User other = (User) obj;
    if (this.id == null)
      if (other.id != null)
        return false;
      else if (!this.id.equals(other.id))
        return false;

    return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username)
        && Objects.equals(this.password, other.password);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
    return result;
  }

  // @Override
  // public String toString() {
  // return "{" +
  // " id='" + getId() + "'" +
  // ", username='" + getUsername() + "'" +
  // ", password='" + getPassword() + "'" +
  // "}";
  // }

}
