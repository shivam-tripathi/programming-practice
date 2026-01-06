package com.dsAlgo.array;

import java.util.*;

class PascalTriangleSolution {
    public ArrayList<ArrayList<Integer>> solve(int A) {
        ArrayList<ArrayList<Integer>> pascalsTriangle = new ArrayList<>();

        while(pascalsTriangle.size() < A) {
            ArrayList<Integer> rowN = new ArrayList<>();
            rowN.add(1);

            if (pascalsTriangle.size() > 0) {
                ArrayList<Integer> prevRow = pascalsTriangle.get(pascalsTriangle.size() - 1);
                int prev = prevRow.get(0);
                for (int i = 1; i < prevRow.size(); i++) {
                    int pres = prevRow.get(i);
                    rowN.add(prev + pres);
                    prev = pres;
                }

                rowN.add(1);
            }

            pascalsTriangle.add(rowN);
        }

        return pascalsTriangle;
    }
}

public class PascalTriangle {
    public static void main(String[] args) {
        PascalTriangleSolution solution = new PascalTriangleSolution();
        ArrayList<ArrayList<Integer>> pascalsTriangle = solution.solve(10);

        for (ArrayList<Integer> row: pascalsTriangle) {
            for (Integer i: row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}