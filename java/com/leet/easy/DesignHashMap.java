package com.leet.easy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DesignHashMap {
  static class MyHashMap {

    double loadFactor = 0.75;
    int bucketCount = 10;
    int size = 0;

    class ListNode {
      int key;
      int value;
      ListNode next;

      ListNode(int key, int value) {
        this.key = key;
        this.value = value;
      }

      @Override
      public String toString() {
        return "[" + key + "," + value + "]";
      }
    }

    class Bucket {
      ListNode head;
      ListNode tail;
      int size;
    }

    Bucket[] buckets;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
      this.buckets = new Bucket[bucketCount]; // initialize them lazily
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
      System.out.println("Put " + key + " " + value);
      int idx = Integer.hashCode(key) & (bucketCount - 1);
      Bucket bucket = buckets[idx];
      if (bucket == null || bucket.tail == null) {
        bucket = new Bucket();
        bucket.head = bucket.tail = new ListNode(key, value);
        bucket.size++;
        buckets[idx] = bucket;
        size++;
      } else {
        ListNode node = getNode(key);
        if (node == null) {
          ListNode tail = new ListNode(key, value);
          bucket.tail.next = tail;
          bucket.tail = tail;
          bucket.size++;
        } else {
          node.value = value;
        }
      }
    }

    private ListNode getNode(int key) {
      int idx = Integer.hashCode(key) & (bucketCount - 1);
      Bucket bucket = buckets[idx];
      if (bucket == null) return null;
      ListNode node = bucket.head;
      while (node != null) {
        if (node.key == key) {
          return node;
        }
        node = node.next;
      }
      return null;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
      ListNode node = getNode(key);
      int ret = node == null ? -1 : node.value;
      return ret;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
      int idx = Integer.hashCode(key) & (bucketCount - 1);
      Bucket bucket = buckets[idx];
      if (bucket == null) return;
      ListNode node = bucket.head;
      ListNode prev = null;
      while (node != null) {
        if (node.key == key) {
          break;
        }
        prev = node;
        node = node.next;
      }
      if (node == null) {
        return;
      }
      size--;
      bucket.size--;
      if (bucket.head == node) {
        bucket.head = bucket.head.next;
      }
      if (bucket.tail == node) {
        bucket.tail = prev;
      }
      if (prev != null) {
        prev.next = node.next;
      }
    }
  }

  public static void main(String[] args) {
    MyHashMap hashmap = new MyHashMap();
    hashmap.remove(27);
    hashmap.put(65, 65);
    hashmap.remove(19);
    hashmap.remove(0);
    hashmap.get(18);
    hashmap.remove(3);
    hashmap.put(42, 0);
    hashmap.get(19);
    hashmap.remove(42);
    hashmap.put(17, 90);
    hashmap.put(31, 76);
    hashmap.put(48, 71);
    hashmap.put(5, 50);
    hashmap.put(7, 68);
    hashmap.put(73, 74);
    hashmap.put(85, 18);
    hashmap.put(74, 95);
    hashmap.put(84, 82);
    hashmap.put(59, 29);
    hashmap.put(71, 71);
    hashmap.remove(42);
    hashmap.put(51, 40);
    hashmap.put(33, 76);
    hashmap.get(17);
    hashmap.put(89, 95);
    hashmap.get(95);
    hashmap.put(30, 31);
    hashmap.put(37, 99);
    hashmap.get(51);
    hashmap.put(95, 35);
    hashmap.remove(65);
    hashmap.remove(81);
    hashmap.put(61, 46);
    hashmap.put(50, 33);
    hashmap.get(59);
    hashmap.remove(5);
    hashmap.put(75, 89);
    hashmap.put(80, 17);
    hashmap.put(35, 94);
    hashmap.get(80);
    hashmap.put(19, 68);
    hashmap.put(13, 17);
    hashmap.remove(70);
    hashmap.put(28, 35);
    hashmap.remove(99);
    hashmap.remove(37);
    hashmap.remove(13);
    hashmap.put(90, 83);
    hashmap.remove(41);
    hashmap.get(50);
    hashmap.put(29, 98);
    hashmap.put(54, 72);
    hashmap.put(6, 8);
    hashmap.put(51, 88);
    hashmap.remove(13);
    hashmap.put(8, 22);
    hashmap.get(85);
    hashmap.put(31, 22);
    hashmap.put(60, 9);
    hashmap.get(96);
    hashmap.put(6, 35);
    hashmap.remove(54);
    hashmap.get(15);
    hashmap.get(28); // this
    hashmap.remove(51);
    hashmap.put(80, 69);
    hashmap.put(58, 92);
    hashmap.put(13, 12);
    hashmap.put(91, 56);
    hashmap.put(83, 52);
    hashmap.put(8, 48);
    hashmap.get(62);
    hashmap.get(54);
    hashmap.remove(25);
    hashmap.put(36, 4);
    hashmap.put(67, 68);
    hashmap.put(83, 36);
    hashmap.put(47, 58);
    hashmap.get(82);
    hashmap.remove(36);
    hashmap.put(30, 85);
    hashmap.put(33, 87);
    hashmap.put(42, 18);
    hashmap.put(68, 83);
    hashmap.put(50, 53);
    hashmap.put(32, 78);
    hashmap.put(48, 90);
    hashmap.put(97, 95);
    hashmap.put(13, 8);
    hashmap.put(15, 7);
    hashmap.remove(5);
    hashmap.remove(42);
    hashmap.get(20);
    hashmap.remove(65);
    hashmap.put(57, 9);
    hashmap.put(2, 41);
    hashmap.remove(6);
    hashmap.get(33);
    hashmap.put(16, 44);
    hashmap.put(95, 30);
  }
}
