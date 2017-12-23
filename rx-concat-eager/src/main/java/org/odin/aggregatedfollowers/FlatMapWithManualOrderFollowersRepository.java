package org.odin.aggregatedfollowers;

import io.reactivex.Flowable;
import org.odin.Follower;
import org.odin.FollowersRepository;
import org.odin.PublicProfileRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapWithManualOrderFollowersRepository implements AggregatedFollowersRepository {

  private final FollowersRepository followersRepository = new FollowersRepository();
  private final PublicProfileRepository publicProfileRepository = new PublicProfileRepository();

  @Override
  public Flowable<AggregatedFollower> getFollowers(String userId) {
    return followersRepository
        .getFollowers(userId)
        .toList()
        .toFlowable()
        .flatMap(
            followersList -> {
              List<String> orderedIds = followersList
                  .stream()
                  .map(Follower::getId)
                  .collect(Collectors.toList());

              return Flowable
                  .fromIterable(followersList)
                  .flatMap(follower ->
                      publicProfileRepository
                          .getPublicProfile(follower.getId())
                          .map(publicProfile -> new AggregatedFollower(follower, publicProfile))
                          .toFlowable()
                  )
                  .toSortedList(Comparator.comparingInt(o -> orderedIds.indexOf(o.getFollower().getId())))
                  .toFlowable()
                  .flatMap(Flowable::fromIterable);
            }
        );
  }
}
