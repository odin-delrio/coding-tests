package org.odin;

import java.util.Arrays;
import java.util.List;

public class Fixtures {

  public static final Follower FOLLOWER_2 = new Follower("2", "follower 2");
  public static final Follower FOLLOWER_3 = new Follower("3", "follower 3");
  public static final Follower FOLLOWER_4 = new Follower("4", "follower 4");

  public static final PublicProfile PUBLIC_PROFILE_1 = new PublicProfile("1", 10, 100);
  public static final PublicProfile PUBLIC_PROFILE_2 = new PublicProfile("2", 20, 200);
  public static final PublicProfile PUBLIC_PROFILE_3 = new PublicProfile("3", 30, 300);
  public static final PublicProfile PUBLIC_PROFILE_4 = new PublicProfile("4", 40, 400);
  public static final PublicProfile PUBLIC_PROFILE_5 = new PublicProfile("5", 50, 500);

  public static List<Follower> getAllFollowers() {
    return Arrays.asList(
      FOLLOWER_2,
      FOLLOWER_3,
      FOLLOWER_4
    );
  }

  public static List<PublicProfile> getAllPublicProfiles() {
    return Arrays.asList(
        PUBLIC_PROFILE_1,
        PUBLIC_PROFILE_2,
        PUBLIC_PROFILE_3,
        PUBLIC_PROFILE_4,
        PUBLIC_PROFILE_5
    );
  }
}
