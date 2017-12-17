package org.odin;

import java.util.Objects;

public class Follower {

  private final String id;
  private final String name;

  public Follower(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Follower follower = (Follower) o;
    return Objects.equals(id, follower.id) && Objects.equals(name, follower.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
