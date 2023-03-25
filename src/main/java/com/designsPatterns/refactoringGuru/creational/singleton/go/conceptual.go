package main

import (
  "fmt"
  "sync"
)

var lock = &sync.Mutex{}

type single struct {
  val int
}

var singleInstance *single

func getInstance(val int) *single {
  if singleInstance == nil {
    lock.Lock()
    defer lock.Unlock()
    if singleInstance == nil {
      singleInstance = &single{val: val}
    }
  }
  return singleInstance
}

func main() {
  var wg sync.WaitGroup
  limit, done := 15, 0
  wg.Add(1)
  for i := 0; i < limit; i++ {
    go func() {
      fmt.Println("singleton value:", getInstance(i).val)
      done++
      if done == limit {
        wg.Done()
      }
    }()
  }
  wg.Wait()
}
