package com.leet.medium;

import java.util.*;

public class SubsetsII {

  List<List<Integer>> subsets2(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    List<List<Integer>> cur = List.of(List.of());
    var lastIdxs = List.of(-1);
    while (!cur.isEmpty()) {
      for (int s = 0; s < cur.size(); s++) {
        int lastIdx = lastIdxs.get(s);
        var set = new ArrayList<>(cur.get(s));
        for (int i = lastIdx+1; i < nums.length; i++) {
          set.add(nums[i]);
        }
      }
    }
    return List.of();
  }


  Set<String> dp = new HashSet<>();

  List<List<Integer>> dfs(int[] nums, int pos, List<Integer> set, List<List<Integer>> ans) {
    if (pos == nums.length) {
      String key = set.stream().reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append).toString();
      if (dp.contains(key)) return ans;
      dp.add(key);
      ans.add(new ArrayList<>(set));
      return ans;
    }
    dfs(nums, pos + 1, set, ans);
    set.add(nums[pos]);
    dfs(nums, pos + 1, set, ans);
    return ans;
  }

  List<List<Integer>> subsets(int[] nums) {
    return List.of();
  }

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    return dfs(nums, 0, new ArrayList<>(), new ArrayList<>());
  }
}
