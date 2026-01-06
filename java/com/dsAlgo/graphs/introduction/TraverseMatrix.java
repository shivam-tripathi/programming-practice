package com.dsAlgo.graphs.introduction;

import java.beans.Visibility;
import java.util.*;

public class TraverseMatrix {
  static class Graph {
    static class Node {
      int value;
      int x;
      int y;

      Node(int v, int x, int y) {
        this.value = v;
        this.x = x;
        this.y = y;
      }
    }

    static class SortNode implements Comparator<Node> {
      public int compare(Node a, Node b) {
        return b.value - a.value;
      }
    }

    int[][] matrix;

    public Graph(int[][] matrix) {
      this.matrix = matrix;
    }

    boolean isValidIndex(int x, int y) {
      int size = this.matrix.length;
      return x >= 0 && y >= 0 && x < size && y < size;
    }

    int getLinearIndex(int x, int y) {
      return x * this.matrix.length + y;
    }

    List<Node> getNeighbours(int x, int y) {
      int[][] positions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
      int presValue = this.matrix[x][y];
      List<Node> neighbours = new ArrayList<>();
      for (int[] position : positions) {
        int xPos = x + position[0];
        int yPos = y + position[1];
        if (this.isValidIndex(xPos, yPos) && this.matrix[xPos][yPos] <= presValue) {
          neighbours.add(new Node(this.matrix[xPos][yPos], xPos, yPos));
        }
      }
      return neighbours;
    }

    void BFS(int x, int y, boolean[] visited) {
      Queue<Node> queue = new LinkedList<>();
      queue.add(new Node(this.matrix[x][y], x, y));
      visited[this.getLinearIndex(x, y)] = true;

      while (!queue.isEmpty()) {
        Node node = queue.remove();
        List<Node> neighbours = this.getNeighbours(node.x, node.y);
        neighbours.sort(new SortNode());
        for (Node neighbour : neighbours) {
          node = neighbour;
          int index = this.getLinearIndex(node.x, node.y);
          if (!visited[index]) {
            visited[index] = true;
            queue.add(node);
          }
        }
      }
    }

    List<Node> traverse() {
      int rowSize, columnSize, size;
      rowSize = columnSize = this.matrix.length;
      size = rowSize * columnSize;
      Node[] nodes = new Node[size];
      boolean[] visited = new boolean[size];

      for (int i = 0; i < rowSize; i++) {
        for (int j = 0; j < columnSize; j++) {
          int index = (i * rowSize) + j;
          nodes[index] = new Node(this.matrix[i][j], i, j);
        }
      }

      Arrays.sort(nodes, new SortNode());
      List<Node> requiredNodes = new LinkedList<>();
      for (int i = 0; i < size; i++) {
        Node node = nodes[i];
        if (!visited[this.getLinearIndex(node.x, node.y)]) {
          requiredNodes.add(node);
          this.BFS(node.x, node.y, visited);
          System.out.println();
        }
      }
      return requiredNodes;
    }

  }

  public static void main(String[] args) {
    int[][] matrix = {{1, 2, 3}, {2, 3, 1}, {1, 1, 1}};
    Graph graph = new Graph(matrix);
    List<Graph.Node> required = graph.traverse();
    for (Graph.Node node : required) {
      System.out.println(node.value + " (" + node.x + ", " + node.y + ")");
    }
    System.out.println();
  }
}
