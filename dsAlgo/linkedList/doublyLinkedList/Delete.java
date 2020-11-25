import java.util.*;

class DeletableDLL extends InsertableDLL {
  public int deleteAt(int position) throws NullPointerException {
    if (position >= this.size) {
      throw new NullPointerException(String.format("Position out of bounds %d > %d", position, this.size - 1));
    }

    Node temp = this.head;
    int index = 0;
    while (temp != null && index != position) {
      temp = temp.next;
      index++;
    }

    Node prev = temp.prev;
    Node next = temp.next;

    if (next != null) {
      next.prev = prev;
    }

    if (prev != null) {
      prev.next = next;
    }

    if (position == 0) {
      this.head = next;
    }

    this.size--;

    int returnValue = temp.value;
    temp = null;
    return returnValue;
  }
}

public class Delete {
  public static void main(String[] args) {
    try {
      DeletableDLL ll = new DeletableDLL();
      for (int i = 0; i < 10; i++) {
        ll.append(i);
        ll.print();
      }

      Random random = new Random();
      int done = 0;
      for (int i = 0; done < 10; i++) {
        int r = random.nextInt(10 - done);
        ll.deleteAt(r);
        ll.print();
        done++;
      }

      // ll.remove();
      // ll.print();
      // ll.pop();
      // ll.print();
      // ll.remove();
      // ll.print();
      // ll.pop();
      // ll.print();
      // ll.deleteAt(5);
      // ll.print();
      // ll.deleteAt(3);
      // ll.print();

    } catch (Exception e) {
      System.out.println("Exception error " + e);
    }
  }
}
