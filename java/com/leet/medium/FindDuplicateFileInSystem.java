package com.leet.medium;

/**
 * 609. Find Duplicate File in System
 * https://leetcode.com/problems/find-duplicate-file-in-system/
 *
 * Medium
 *
 * You mayGiven a list paths of directory info, including the directory path,
 * and all the files with contents in this directory, * return all the duplicate
 * files in the file system in terms of their paths. return the answer in any
 * order.
 *
 * A group of duplicate files consists of at least two files that have the same
 * content.
 *
 * A single directory info string in the input list has the following format:
 *
 * "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ...
 * fn.txt(fn_content)"
 *
 * It means there are n files (f1.txt, f2.txt ... fn.txt) with content
 * (f1_content, f2_content ... fn_content) respectively in the directory
 * "root/d1/d2/.../dm". Note that n >= 1 and m >= 0. If m = 0, it means the
 * directory is just the root directory.
 *
 * The output is a list of groups of duplicate file paths. For each group, it
 * contains all the file paths of the files that have the same content. A file
 * path is a string that has the following format:
 *
 * "directory_path/file_name.txt"
 *
 *
 *
 * Example 1:
 *
 * Input: paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c
 * 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"] Output:
 * [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
 *
 * Example 2:
 *
 * Input: paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c
 * 3.txt(abcd)","root/c/d 4.txt(efgh)"] Output:
 * [["root/a/2.txt","root/c/d/4.txt"],["root/a/1.txt","root/c/3.txt"]]
 *
 *
 *
 * Constraints:
 *
 * 1 <= paths.length <= 2 * 104 1 <= paths[i].length <= 3000 1 <=
 * sum(paths[i].length) <= 5 * 105 paths[i] consist of English letters, digits,
 * '/', '.', '(', ')', and ' '. You may assume no files or directories share the
 * same name in the same directory. You may assume each given directory info
 * represents a unique directory. A single blank space separates the directory
 * path and file info.
 *
 *
 *
 * Follow up:
 *
 * Imagine you are given a real file system, how will you search files? DFS or
 * BFS? If the file content is very large (GB level), how will you modify your
 * solution? If you can only read the file by 1kb each time, how will you modify
 * your solution? What is the time complexity of your modified solution? What is
 * the most time-consuming part and memory-consuming part of it? How to
 * optimize? How to make sure the duplicated files you find are not false
 * positive?
 *
 * 1. Imagine you are given a real file system, how will you search files? DFS
 * or BFS ? In general, BFS will use more memory then DFS. However BFS can take
 * advantage of the locality of files in inside directories, and therefore will
 * probably be faster
 *
 * 2. If the file content is very large (GB level), how will you modify your
 * solution? In a real life solution we will not hash the entire file content,
 * since it's not practical. Instead we will first map all the files according
 * to size. Files with different sizes are guaranteed to be different. We will
 * than hash a small part of the files with equal sizes (using MD5 for example).
 * Only if the md5 is the same, we will compare the files byte by byte
 *
 * 3. If you can only read the file by 1kb each time, how will you modify your
 * solution? This won't change the solution. We can create the hash from the 1kb
 * chunks, and then read the entire file if a full byte by byte comparison is
 * required.
 *
 * What is the time complexity of your modified solution? What is the most time
 * consuming part and memory consuming part of it? How to optimize? Time
 * complexity is O(n^2 * k) since in worse case we might need to compare every
 * file to all others. k is the file size
 *
 * How to make sure the duplicated files you find are not false positive? We
 * will use several filters to compare: File size, Hash and byte by byte
 * comparisons.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindDuplicateFileInSystem {
  public List<List<String>> findDuplicate(String[] paths) {
    var map = new HashMap<String, List<String>>();
    for (String path : paths) {
      StringBuilder sb = new StringBuilder();
      String[] files = path.split(" ");
      sb.append(files[0]).append("/");
      int size = sb.length();
      for (int i = 1; i < files.length; i++) {
        String[] nameAndContent = files[i].split("\\(");
        String name = nameAndContent[0], content = nameAndContent[1];
        String fullPath = sb.append(name).toString();
        sb.setLength(size);
        if (!map.containsKey(content)) {
          map.put(content, new ArrayList<>());
        }
        map.get(content).add(fullPath);
      }
    }

    var ans = new ArrayList<List<String>>();
    for (List<String> lis : map.values()) {
      if (lis.size() > 1)
        ans.add(lis);
    }
    return ans;
  }
}
