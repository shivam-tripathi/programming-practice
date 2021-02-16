package com.leet.medium;

/**
 *  Kill Process
 *  https://algods-cracker.blogspot.com/2021/02/leetcode-kill-process.html
 *  Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
 *
 * Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.
 *
 * We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.
 *
 * Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.
 *
 * Example:
 *
 * Input:
 * pid =  [1, 3, 10, 5]
 * ppid = [3, 0, 5, 3]
 * kill = 5
 * Output: [5,10]
 * Explanation:
 *            3
 *          /   \
 *         1     5
 *              /
 *             10
 * Kill 5 will also kill 10.
 */

import java.util.*;

public class KillProcess {
  static void dfs(List<Integer> toKill, Map<Integer, List<Integer>> graph, int id) {
    var adjList = graph.getOrDefault(id, new LinkedList<>());
    for (Integer integer : adjList) {
      dfs(toKill, graph, integer);
    }
    toKill.add(id);
  }

  public static List<Integer> killProcess(int[] pid, int[] ppid, int killId) {
    var graph = new HashMap<Integer, List<Integer>>();
    boolean killIdExists = false;
    for (int i = 0; i < pid.length; i++) {
      int processId = pid[i];
      int parentProcessId = ppid[i];
      if (parentProcessId == 0) continue;
      if (processId == killId) killIdExists = true;
      if (!graph.containsKey(parentProcessId)) graph.put(parentProcessId, new ArrayList<>());
      graph.get(parentProcessId).add(processId);
    }
    List<Integer> toKill = new ArrayList<>();
    if (!killIdExists) return toKill;
    dfs(toKill, graph, killId);
    return toKill;
  }

  public static void main(String[] args) {
    int[] pid = new int[] {1, 3, 10, 5};
    int[] ppid = new int[] {3, 0, 5, 3};
//    int[] pid = new int[]{};
//    int[] ppid = new int[]{};
    int killId = 5;
    System.out.println(killProcess(pid, ppid, killId));
  }
}
