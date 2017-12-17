package org.odin;

import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PublicProfileRepository {

  public static Map<String, PublicProfile> PUBLIC_PROFILES = new HashMap<>();

  public Maybe<PublicProfile> getPublicProfile(String userId) {
    return Maybe
        .fromCallable(() -> blockingGetPublicProfile(userId))
        .subscribeOn(Schedulers.io())
        .filter(Objects::nonNull);
  }

  private PublicProfile blockingGetPublicProfile(String userId) {
    sleepSafely();
    return PUBLIC_PROFILES.get(userId);
  }

  private void sleepSafely() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
