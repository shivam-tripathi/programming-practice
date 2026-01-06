package com.lowLevelDesign.snakeAndLadder.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Player {
  public Position position;
  public String name;
  private final RandomDice dice;
  private final List<Integer> moves;
  private boolean isOver;

  private Player(String name, RandomDice dice) {
    this.name = name;
    this.dice = dice;
    this.moves = new ArrayList<>();
  }

  public static Player from(String name, RandomDice dice) {
    return new Player(name, dice);
  }

  public int makeMove() {
    int roll = dice.roll();
    moves.add(roll);
    return roll;
  }
}
