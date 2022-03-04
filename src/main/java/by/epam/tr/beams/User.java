package by.epam.tr.beans;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс, описывающий обе роли водителя и пассажира
 */
public class User implements Serializable{
  private static final long serialVersionUID = -1818397082467608155L;
  private String name;
  private String surname;
  private NavigationMap map;

  public User(String name, String surname) {
    this.name = name;
    this.surname = surname;
    this.map = new NavigationMap();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public NavigationMap getMap() {
    return map;
  }

  public void setMap(NavigationMap map) {
    this.map = map;
  }

  @Override
  public int hashCode() {
    return Objects.hash(map, name, surname);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    return Objects.equals(map, other.map) && Objects.equals(name, other.name)
        && Objects.equals(surname, other.surname);
  }

  @Override
  public String toString() {
    return "User [name=" + name + ", surname=" + surname + ", map=" + map + "]";
  }


}
