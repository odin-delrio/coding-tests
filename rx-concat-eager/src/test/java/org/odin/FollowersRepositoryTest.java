package org.odin;

import org.junit.Before;
import org.junit.Test;
import org.odin.aggregatedfollowers.AggregatedFollower;
import org.odin.aggregatedfollowers.AggregatedFollowersRepository;
import org.odin.aggregatedfollowers.ConcatEagerFollowersRepository;
import org.odin.aggregatedfollowers.ConcatMapFollowersRepository;
import org.odin.aggregatedfollowers.FlatMapFollowersRepository;
import org.odin.aggregatedfollowers.FlatMapWithManualOrderFollowersRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FollowersRepositoryTest {

  private static final String TARGET_USER_ID = "1";

  private static final List<AggregatedFollower> EXPECTED_AGGREGATED_FOLLOWERS = Arrays.asList(
      new AggregatedFollower(
          Fixtures.FOLLOWER_2,
          Fixtures.PUBLIC_PROFILE_2
      ),
      new AggregatedFollower(
          Fixtures.FOLLOWER_3,
          Fixtures.PUBLIC_PROFILE_3
      ),
      new AggregatedFollower(
          Fixtures.FOLLOWER_4,
          Fixtures.PUBLIC_PROFILE_4
      )
  );

  @Before
  public void setUpRepositories() {
    FollowersRepository.FOLLOWERS = new HashMap<String, List<Follower>>() {{
      put(TARGET_USER_ID, Fixtures.getAllFollowers());
    }};

    PublicProfileRepository.PUBLIC_PROFILES = new HashMap<>();
    Fixtures
        .getAllPublicProfiles()
        .forEach(profile -> PublicProfileRepository.PUBLIC_PROFILES.put(profile.getId(), profile));
  }

  @Test
  public void getFollowersWithFlatMap() {
    List<AggregatedFollower> aggregatedFollowers = getAggregatedFollowers(
        new FlatMapFollowersRepository()
    );

    thenAggregatedFollowersAreTheExpectedInAnyOrder(aggregatedFollowers);
  }

  @Test
  public void getFollowersWithConcatMap() {
    List<AggregatedFollower> aggregatedFollowers = getAggregatedFollowers(
        new ConcatMapFollowersRepository()
    );

    thenAggregatedFollowersAreTheExpectedInTheSameOrder(aggregatedFollowers);
  }

  @Test
  public void getFollowersWithFlatMapAndCustomOrdering() {
    List<AggregatedFollower> aggregatedFollowers = getAggregatedFollowers(
        new FlatMapWithManualOrderFollowersRepository()
    );

    thenAggregatedFollowersAreTheExpectedInTheSameOrder(aggregatedFollowers);
  }

  @Test
  public void getFollowersWithConcatEager() {
    List<AggregatedFollower> aggregatedFollowers = getAggregatedFollowers(
        new ConcatEagerFollowersRepository()
    );

    thenAggregatedFollowersAreTheExpectedInTheSameOrder(aggregatedFollowers);
  }

  private List<AggregatedFollower> getAggregatedFollowers(AggregatedFollowersRepository repository) {
    return repository
        .getFollowers(TARGET_USER_ID)
        .toList()
        .blockingGet();
  }

  private void thenAggregatedFollowersAreTheExpectedInTheSameOrder(List<AggregatedFollower> aggregatedFollowers) {
    assertThat(aggregatedFollowers).containsSequence(EXPECTED_AGGREGATED_FOLLOWERS);
  }

  private void thenAggregatedFollowersAreTheExpectedInAnyOrder(List<AggregatedFollower> aggregatedFollowers) {
    assertThat(aggregatedFollowers).hasSameElementsAs(EXPECTED_AGGREGATED_FOLLOWERS);
  }
}