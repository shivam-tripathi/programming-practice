package com.leet.medium;

/**
 * 1110. Delete Nodes And Return Forest Medium
 * https://leetcode.com/problems/delete-nodes-and-return-forest/
 *
 * Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint
 * union of trees).
 *
 * Return the roots of the trees in the remaining forest. You may return the result in any order.
 *
 * Example 1:
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5] Output: [[1,2,null,4],[6],[7]]
 *
 * Example 2:
 * Input: root = [1,2,4,null,3], to_delete = [3] Output: [[1,2,4]]
 *
 * Constraints:
 * The number of nodes in the given tree is at most 1000. Each node has a distinct value between
 * 1 and 1000. to_delete.length <= 1000 to_delete contains distinct values between 1 and 1000.
 */

import com.leet.common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNodesAndReturnForest {
  // We check if the current node is marked to be deleted or not. If yes, we will return null. We will also update
  // the params such that children are marked to be added into the forest (if they are not marked for deletion).
  // If they are not marked for deletion, we will return the node. If they are marked to be added to the forest,
  // we will add them to the forest. After that we will process left and right children of the tree.
  public TreeNode delNodes(
          TreeNode node, List<TreeNode> forest, Set<Integer> toDel, boolean addCur) {
    if (node == null) return null;
    var addNext = false;

    TreeNode ret = node;

    if (toDel.contains(node.val)) {
      addNext = true;
      ret = null; // this node has been deleted
    } else if (addCur) {
      forest.add(node);
    }

    node.left = delNodes(node.left, forest, toDel, addNext);
    node.right = delNodes(node.right, forest, toDel, addNext);

    return ret;
  }

  public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
    Set<Integer> toDel = new HashSet<>();
    for (int val : to_delete) toDel.add(val);
    List<TreeNode> forest = new ArrayList<>();
    delNodes(root, forest, toDel, true);
    return forest;
  }
}
