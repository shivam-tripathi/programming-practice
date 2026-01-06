package main

type DoublyListNode struct {
  val  int
  prev *DoublyListNode
  next *DoublyListNode
}

type DoublyLinkedList struct {
  head *DoublyListNode
  tail *DoublyListNode
  mid  *DoublyListNode
  size int
  midPos  int
}

func (this *DoublyLinkedList) adjustMid() {
  if this.size == 0 {
    this.mid = nil
    this.midPos = 0
  } else if this.size == 1 {
    this.mid = this.head
    this.midPos = 1
  }

  expected := this.size / 2

  for this.midPos > expected {
    this.mid = this.mid.prev
    this.midPos -= 1
  }

  for this.midPos < expected {
    this.mid = this.mid.next
    this.midPos += 1
  }
}

func (this *DoublyLinkedList) addToTail(item int) {
  node := &DoublyListNode{val: item}
  if (this.size == 0) {
    this.head, this.tail = node, node
  } else {
    node.prev = this.tail
    this.tail.next = node
    this.tail = node
  }
  this.size++
  this.adjustMid()
}

func (this *DoublyLinkedList) removeFromTail() int {
  if (this.size == 0) {
    return -1
  }
  item := this.tail.val
  if (this.size == 1) {
    this.head, this.tail = nil, nil
  } else {
    this.tail = this.tail.prev
    this.tail.next = nil
  }
  this.size--
  this.adjustMid()
  return item
}

func (this *DoublyLinkedList) addToHead(item int) {
  node := &DoublyListNode{val: item}
  if (this.size == 0) {
    this.head, this.tail = node, node
  } else {
    node.next = this.head
    this.head.prev = node
    this.head = node
  }
  this.size++
}

func (this *DoublyLinkedList) removeFromHead() int {
  if (this.size == 0) {
    return -1;
  }
  item := this.head.val;
  if (this.head == this.tail) {
    this.head = this.tail = nil
  } else {
    this.head = this.head.next;
    this.head.prev = nil;
  }
  this.size--
  this.adjustMid()
  return item;
}

func (this *DoublyLinkedList) removeFromMid() int {
  if (this.size == 0) {
    return -1
  }
  item := this.mid.val
  if (this.mid.prev != nil) {
    this.mid.prev = this.mid.next
		if this.size % 2 != 0 {
			this.mid--
		}
  } else {
		this.head, this.tail = nil, nil
		this.mid = 0
	}
  this.size--
  return mid
}

func Constructor() FrontMiddleBackQueue {
  return FrontMiddleBackQueue {
    front: &
  }
}


func (this *FrontMiddleBackQueue) PushFront(val int)  {

}


func (this *FrontMiddleBackQueue) PushMiddle(val int)  {

}


func (this *FrontMiddleBackQueue) PushBack(val int)  {

}


func (this *FrontMiddleBackQueue) PopFront() int {

}


func (this *FrontMiddleBackQueue) PopMiddle() int {

}


func (this *FrontMiddleBackQueue) PopBack() int {

}


/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * obj := Constructor();
 * obj.PushFront(val);
 * obj.PushMiddle(val);
 * obj.PushBack(val);
 * param_4 := obj.PopFront();
 * param_5 := obj.PopMiddle();
 * param_6 := obj.PopBack();
 */