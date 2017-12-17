package org.odin.aggregatedfollowers;

import org.odin.Follower;
import org.odin.PublicProfile;

import java.util.Objects;

public class AggregatedFollower {

  private final Follower follower;
  private final PublicProfile publicProfile;

  public AggregatedFollower(Follower follower, PublicProfile publicProfile) {
    this.follower = follower;
    this.publicProfile = publicProfile;
  }

  public Follower getFollower() {
    return follower;
  }

  public PublicProfile getPublicProfile() {
    return publicProfile;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AggregatedFollower that = (AggregatedFollower) o;
    return Objects.equals(follower, that.follower) && Objects.equals(publicProfile, that.publicProfile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(follower, publicProfile);
  }
}
