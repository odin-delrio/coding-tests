package org.odin;

import io.reactivex.Flowable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FollowersRepository {

  public static Map<String, List<Follower>> FOLLOWERS = new HashMap<>();

  public Flowable<Follower> getFollowers(String userId) {
    return Flowable.fromIterable(
        blockingGetFollowers(userId)
    );
  }

  private List<Follower> blockingGetFollowers(String userId) {
    return Optional.ofNullable(FOLLOWERS.get(userId)).orElse(Collections.emptyList());
  }
}
