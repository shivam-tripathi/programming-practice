package com.lowLevelDesign.snakeAndLadder.models;

public class RandomDice implements Dice {
  public int roll() {
    return 1 + (int) (Math.floor(Math.random() * 6));
  }
}
