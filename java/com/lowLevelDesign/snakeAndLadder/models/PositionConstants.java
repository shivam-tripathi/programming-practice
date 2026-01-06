package com.lowLevelDesign.snakeAndLadder.models;

public enum PositionConstants {
  FINAL_POSITION(Position.from(100, 100)),
  START_POSITION(Position.from(0, 0));


  Position position;

  private PositionConstants(Position position) {
    this.position = position;
  }
}
