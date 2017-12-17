package org.odin.aggregatedfollowers;

import io.reactivex.Flowable;
import org.odin.FollowersRepository;
import org.odin.PublicProfileRepository;

public class ConcatMapFollowersRepository implements AggregatedFollowersRepository {

  private final FollowersRepository followersRepository = new FollowersRepository();
  private final PublicProfileRepository publicProfileRepository = new PublicProfileRepository();

  @Override
  public Flowable<AggregatedFollower> getFollowers(String userId) {
    return followersRepository
        .getFollowers(userId)
        .concatMap(
            follower -> publicProfileRepository
                .getPublicProfile(follower.getId())
                .map(publicProfile -> new AggregatedFollower(follower, publicProfile))
                .toFlowable()
        );
  }
}
