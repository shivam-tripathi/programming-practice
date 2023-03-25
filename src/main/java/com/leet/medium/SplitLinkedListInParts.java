package com.leet.medium;

import com.leet.common.ListNode;

public class SplitLinkedListInParts {

  // 4 3 3
  // 10 size, number of chunks = 3, chunkSize = 10/3 = 3 + remainder 1

  // 15 4 -> 15/4 = 3
  // 15%4 = 3 (3 chunks from beginning need extra values)
  // 4 4 4 3

  int parse(ListNode node, int pos, int chunks, ListNode[] nodes) {
    if (node == null)
      return 1;
    int size = parse(node.next, pos + 1, chunks, nodes);
    int chunkSize = size / chunks;
    int chunksWithExtraElem = size % chunks;
    int boundary = chunksWithExtraElem * (chunkSize + 1);
    int chunkNumber = pos / (chunkSize + 1);
    if (pos >= boundary) {
      chunkNumber = ((pos - boundary) / chunkSize) + chunksWithExtraElem;
    }
    node.next = null;
    if (nodes[chunkNumber] != null)
      nodes[chunkNumber].next = node;
    else
      nodes[chunkNumber] = node;
    return size;
  }

  public ListNode[] splitListToParts(ListNode head, int k) {
    var nodes = new ListNode[k];
    parse(head, 0, k, nodes);
    return nodes;
  }
}
