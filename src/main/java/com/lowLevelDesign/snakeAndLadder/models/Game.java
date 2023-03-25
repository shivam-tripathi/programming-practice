package com.lowLevelDesign.snakeAndLadder.models;

import com.lowLevelDesign.snakeAndLadder.exceptions.GameOverException;
import lombok.Getter;

import java.util.*;

@Getter
public class Game {
  private Board board;
  private RandomDice dice;
  private Map<Integer, Player> results;
  private Queue<Player> playerQueue;

  public Game() {
    int rows = 10;
    board = new Board(rows);
    dice = new RandomDice();
    results = new HashMap<>();
    playerQueue = new ArrayDeque<>();
  }

  public void addPlayer(Player player) {
    playerQueue.offer(player);
  }

  private Player nextPlayer() {
    if (playerQueue.isEmpty()) {
      throw new GameOverException();
    }
    return playerQueue.remove();
  }

  public void play(Player player) {
    if (player.isOver()) {

    }
  }


}
