package com.example.todosimple.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {
  public static final String TABLE_NAME = "tasks";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true)
  private Long id;

  @Column(name = "description", length = 200, nullable = false)
  @NotEmpty
  @NotNull
  @Size(min = 3, max = 200)
  private String description;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;


  public Task() {
  }

  public Task(Long id, String description, User user) {
    this.id = id;
    this.description = description;
    this.user = user;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Task id(Long id) {
    setId(id);
    return this;
  }

  public Task description(String description) {
    setDescription(description);
    return this;
  }

  public Task user(User user) {
    setUser(user);
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof Task)) 
      return false;

    Task other = (Task) obj;
    if (this.id == null)
      if (other.id != null)
        return false;
      else if (!this.id.equals(other.id))
        return false;

    return Objects.equals(this.id, other.id) && Objects.equals(this.user, other.user)
        && Objects.equals(this.description, other.description);
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
  //   return "{" +
  //     " id='" + getId() + "'" +
  //     ", description='" + getDescription() + "'" +
  //     ", user='" + getUser() + "'" +
  //     "}";
  // }
  
}
