package org.odin;

import java.util.Objects;

public class PublicProfile {

  private final String id;
  private final int numberOfFollowers;
  private final int numberOfFollowing;

  public PublicProfile(String id, int numberOfFollowers, int numberOfFollowing) {
    this.id = id;
    this.numberOfFollowers = numberOfFollowers;
    this.numberOfFollowing = numberOfFollowing;
  }

  public String getId() {
    return id;
  }

  public int getNumberOfFollowers() {
    return numberOfFollowers;
  }

  public int getNumberOfFollowing() {
    return numberOfFollowing;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PublicProfile that = (PublicProfile) o;
    return numberOfFollowers == that.numberOfFollowers &&
        numberOfFollowing == that.numberOfFollowing &&
        Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, numberOfFollowers, numberOfFollowing);
  }
}
