package main

/*
146. LRU Cache
https://leetcode.com/problems/lru-cache
Medium

Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:
    LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
    int get(int key) Return the value of the key if the key exists, otherwise return -1.
    void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the
		key-value pair to the cache. If the number of keys exceeds the capacity from this operation,
		evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.

Example 1:
Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4

Constraints:
    1 <= capacity <= 3000
    0 <= key <= 104
    0 <= value <= 105
    At most 2 * 105 calls will be made to get and put.
*/

import (
	"fmt"
	"sync"
)

type DoublyLinkedListNode struct {
	key   int
	value int
	prev  *DoublyLinkedListNode
	next  *DoublyLinkedListNode
}

func NewDoublyLinkedListNode(key int, value int) *DoublyLinkedListNode {
	return &DoublyLinkedListNode{
		key:   key,
		value: value,
	}
}

type LRUCache struct {
	capacity int
	size     int
	record   map[int]*DoublyLinkedListNode
	head     *DoublyLinkedListNode
	tail     *DoublyLinkedListNode
	sync.Mutex
}

func NewLRUCache(capacity int) LRUCache {
	return LRUCache{capacity: capacity, record: make(map[int]*DoublyLinkedListNode)}
}

func (this *LRUCache) print() {
	node := this.head
	fmt.Print("list :: ")
	for node != nil {
		fmt.Print(node.key, "->")
		node = node.next
	}
	fmt.Println(nil, "size ::", this.size, "cap ::", this.capacity)
}

func (this *LRUCache) remove(key int) {
	if node, ok := this.record[key]; ok {
		if node.prev != nil {
			node.prev.next = node.next
		} else {
			this.head = node.next
		}

		if node.next != nil {
			node.next.prev = node.prev
		} else {
			this.tail = node.prev
		}

		node.prev, node.next = nil, nil
		delete(this.record, key)
		this.size--
	}
}

func (this *LRUCache) insert(node *DoublyLinkedListNode) {
	if this.head == nil {
		this.head = node
		this.tail = node
	} else {
		node.next = this.head
		this.head.prev = node
		this.head = node
	}
	this.size++
	this.record[node.key] = node
}

func (this *LRUCache) Get(key int) int {
	this.Lock()
	defer this.Unlock()

	if node, ok := this.record[key]; ok {
		this.remove(key)
		this.insert(node)
		return node.value
	}
	return -1
}

func (this *LRUCache) Put(key, value int) {
	this.Lock()
	defer this.Unlock()

	if node, ok := this.record[key]; ok {
		node.value = value
		this.remove(key)
		this.insert(node)
	} else {
		if this.size == this.capacity {
			this.remove(this.tail.key)
		}
		node := NewDoublyLinkedListNode(key, value)
		this.insert(node)
	}
}
