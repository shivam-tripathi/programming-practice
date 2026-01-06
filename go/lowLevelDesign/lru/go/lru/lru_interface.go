package lru

import (
	"container/list"
	"errors"
)

type LRUCache interface {
	Add(key, value interface{}) (evicted bool)
	Remove(key interface{}) (ok bool)
	RemoveOldest() (key interface{}, value interface{}, found bool)
	Get(key interface{}) (value interface{}, ok bool)
	Contains(key interface{}) (ok bool)
	Peek(key interface{}) (value interface{}, ok bool)
	Keys() []interface{}
	Len() int
	Purge()
	Resize(size int) (evicted int)
}

// Callback called when an entry is evicted from cache
type EvictCallback func(key interface{}, value interface{})

// LRU concrete
type LRU struct {
	size      int
	evictList *list.List
	items     map[interface{}]*list.Element
	onEvict   EvictCallback
}

// Entry
type Entry struct {
	key   interface{}
	value interface{}
}

// Constructor
func NewLRU(size int, onEvict EvictCallback) (*LRUCache, error) {
	if size <= 0 {
		return nil, errors.New("must provide a positive size")
	}

	lru := &LRU{size: size, evictList: list.New(), items: map[interface{}]*list.Element{}, onEvict: onEvict}
	return lru, nil
}

func (lru *LRU) Purge() {
	for k, v := range lru.items {
		if lru.onEvict != nil {
			lru.onEvict(k, v)
			delete(lru.items, k)
		}
	}
	lru.evictList.Init()
}

func (lru *LRU) Get(key interface{}) (value interface{}, found bool) {
	if entry, ok := lru.items[key]; ok {
		lru.evictList.MoveToFront(entry)
		return entry.Value.(*Entry).value, true
	}
	return nil, false
}

func (lru *LRU) Add(key interface{}, value interface{}) {
	if entry, ok := lru.items[key]; ok {

	}
}
