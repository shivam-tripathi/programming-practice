package com.leet.medium;

import lombok.AllArgsConstructor;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedListIterator {
  public interface NestedInteger {
    public boolean isInteger();

    public Integer getInteger();

    public List<NestedInteger> getList();
  }

  @AllArgsConstructor
  static class NestedIntegerSingleImpl implements NestedInteger {
    int num;

    public boolean isInteger() {
      return true;
    }

    public Integer getInteger() {
      return num;
    }

    public List<NestedInteger> getList() {
      return null;
    }
  }

  @AllArgsConstructor
  static class NestedIntegerListImpl implements NestedInteger {
    List<NestedInteger> nestedIntegers;

    public boolean isInteger() {
      return false;
    }

    public Integer getInteger() {
      return null;
    }

    public List<NestedInteger> getList() {
      return nestedIntegers;
    }
  }

  public class NestedIterator implements Iterator<Integer> {
    Iterator<NestedInteger> nestedListIter;
    Stack<Iterator<NestedInteger>> stack = new Stack<>();
    NestedInteger top;

    public NestedIterator(List<NestedInteger> nestedList) {
      this.nestedListIter = nestedList.iterator();
    }

    @Override
    public Integer next() {
      int val = top.getInteger();
      top = null;
      return val;
    }

    @Override
    public boolean hasNext() {
      if (!stack.isEmpty()) {
        var top = stack.peek();
        if (top.hasNext()) {
          var topVal = top.next();
          if (topVal.isInteger()) {
            this.top = topVal;
            return true;
          }
          stack.push(topVal.getList().iterator());
          return hasNext();
        }
        stack.pop();
        return hasNext();
      }
      if (nestedListIter.hasNext()) {
        var nextVal = nestedListIter.next();
        if (nextVal.isInteger()) {
          top = nextVal;
          return true;
        }
        stack.push(nextVal.getList().iterator());
        return hasNext();
      }
      return false;
    }

    public boolean hasNext(Iterator<NestedInteger> curIter, Iterator<NestedInteger> parentIter) {
      NestedInteger val;
      if (!curIter.hasNext()) {
        if (stack.size() > 0) {
          return hasNext(stack.pop(), parentIter);
        } else {
          val = parentIter.next();
        }
      } else {
        val = curIter.next();
      }
      if (val.isInteger()) {
        this.top = val;
        stack.push(curIter);
        return true;
      }
      var nextIter = val.getList().iterator();
      stack.push(nextIter);
      return hasNext(nextIter, parentIter);
    }
  }
}
