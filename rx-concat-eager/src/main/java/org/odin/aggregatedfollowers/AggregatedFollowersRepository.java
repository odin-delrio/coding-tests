package org.odin.aggregatedfollowers;

import io.reactivex.Flowable;

public interface AggregatedFollowersRepository {
  Flowable<AggregatedFollower> getFollowers(String userId);
}
