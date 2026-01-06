/*
641. Design Circular Deque
https://leetcode.com/problems/design-circular-deque/
Medium

Design your implementation of the circular double-ended queue (deque).

Implement the MyCircularDeque class:
    1. MyCircularDeque(int k) Initializes the deque with a maximum size of k.
    2. boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false otherwise.
    3. boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false otherwise.
    4. boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful, or false otherwise.
    5. boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or false otherwise.
    6. int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
    7. int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
    8. boolean isEmpty() Returns true if the deque is empty, or false otherwise.
    9. boolean isFull() Returns true if the deque is full, or false otherwise.

Example 1:
Input
["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull",
"deleteLast", "insertFront", "getFront"]
[[3], [1], [2], [3], [4], [], [], [], [4], []]
Output
[null, true, true, true, false, 2, true, true, true, 4]

Explanation
MyCircularDeque myCircularDeque = new MyCircularDeque(3);
myCircularDeque.insertLast(1);  // return True
myCircularDeque.insertLast(2);  // return True
myCircularDeque.insertFront(3); // return True
myCircularDeque.insertFront(4); // return False, the queue is full.
myCircularDeque.getRear();      // return 2
myCircularDeque.isFull();       // return True
myCircularDeque.deleteLast();   // return True
myCircularDeque.insertFront(4); // return True
myCircularDeque.getFront();     // return 4

Constraints:
    1 <= k <= 1000
    0 <= value <= 1000
    At most 2000 calls will be made to insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull.
*/

type MyCircularDeque struct {
	queue    []int
	size     int
	capacity int
	front    int
	end      int
}

func Constructor(k int) MyCircularDeque {
	return MyCircularDeque{
		queue:    make([]int, k),
		capacity: k,
	}
}

func (this *MyCircularDeque) InsertFront(value int) bool {
	if this.size == this.capacity {
		return false
	}
	// in this case, as front is inclusive - we change front and then add the new element
	this.front = (this.front - 1 + this.capacity) % this.capacity
	this.queue[this.front] = value
	this.size++
	return true
}

func (this *MyCircularDeque) InsertLast(value int) bool {
	if this.size == this.capacity {
		return false
	}
	this.queue[this.end] = value
	this.end = (this.end + 1) % this.capacity
	this.size++
	return true
}

func (this *MyCircularDeque) DeleteFront() bool {
	if this.size == 0 {
		return false
	}
	this.front = (this.front + 1) % this.capacity
	this.size--
	return true
}

func (this *MyCircularDeque) DeleteLast() bool {
	if this.size == 0 {
		return false
	}
	this.end = (this.end - 1 + this.capacity) % this.capacity
	this.size--
	return true
}

func (this *MyCircularDeque) GetFront() int {
	if this.size == 0 {
		return -1
	}
	return this.queue[this.front]
}

func (this *MyCircularDeque) GetRear() int {
	if this.size == 0 {
		return -1
	}
	loc := (this.end - 1 + this.capacity) % this.capacity
	return this.queue[loc]
}

func (this *MyCircularDeque) IsEmpty() bool {
	return this.size == 0
}

func (this *MyCircularDeque) IsFull() bool {
	return this.size == this.capacity
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * obj := Constructor(k);
 * param_1 := obj.InsertFront(value);
 * param_2 := obj.InsertLast(value);
 * param_3 := obj.DeleteFront();
 * param_4 := obj.DeleteLast();
 * param_5 := obj.GetFront();
 * param_6 := obj.GetRear();
 * param_7 := obj.IsEmpty();
 * param_8 := obj.IsFull();
 */