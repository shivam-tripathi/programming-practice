package com.lint.oops;

/**
 * 1786 · Pub Sub Pattern
 * https://www.lintcode.com/problem/1786
 * OOD
 * Easy
 * Accepted Rate80%
 * Description
 * Solution
 * Notes
 * Discuss
 * Leaderboard
 * Description
 *
 * Pub/Sub pattern is a wide used pattern in system design. In this problem, you need to implement a pub/sub pattern to
 * support user subscribes on a specific channel and get notification messages from subscribed channels.
 *
 * There are 3 methods you need to implement:
 *     subscribe(channel, user_id): Subscribe the given user to the given channel.
 *     unsubscribe(channel, user_id): Unsubscribe the given user from the given channel.
 *     publish(channel, message): You need to publish the message to the channel so that everyone subscribed on the
 *     channel will receive this message. Call PushNotification.notify(user_id, message) to push the message to the user.
 *
 * Example
 * subscribe("group1",  1)
 * publish("group1", "hello")
 * >> user 1 received "Hello"
 * subscribe("group1", 2)
 * publish("group1", "thank you")
 * >> user 1 received "thank you"
 * >> user 2 received "thank you"
 * unsubscribe("group2", 3)
 * >> user 3 is not in group2, do nothing
 * unsubscribe("group1", 1)
 * publish("group1", "thank you very much")
 * >> user 2 received "thank you very much"
 * publish("group2", "are you ok?")
 * >> # you don't need to push this message to anyone
 * If there are more than 1 user subscribed on the same channel, it doesn't matter the order of time users receiving the
 * message. It's ok if you push the message to user 2 before user 1.
 */

import java.util.*;

class PushNotification {
  static void notify(int user_id, String the_message) {
  }
}

public class PubSubPattern {
  Map<String, Set<Integer>> eventsMap = new HashMap<>();

  public PubSubPattern() {
    // Write your code here
  }

  /**
   * @param channel: the channel's name
   * @param user_id: the user who subscribes the channel
   * @return: nothing
   */
  public void subscribe(String channel, int user_id) {
    if (!eventsMap.containsKey(channel)) {
      eventsMap.put(channel, new HashSet<>());
    }
    eventsMap.get(channel).add(user_id);
  }

  /**
   * @param channel: the channel's name
   * @param user_id: the user who unsubscribes the channel
   * @return: nothing
   */
  public void unsubscribe(String channel, int user_id) {
    if (eventsMap.containsKey(channel)) {
      eventsMap.get(channel).remove(user_id);
    }
  }

  /**
   * @param channel: the channel's name
   * @param message: the message need to be delivered to the channel's subscribers
   * @return: nothing
   */
  public void publish(String channel, String message) {
    if (eventsMap.containsKey(channel)) {
      Set<Integer> userIds = eventsMap.get(channel);
      for (int user_id : userIds) {
        PushNotification.notify(user_id, message);
      }
    }
  }
}
