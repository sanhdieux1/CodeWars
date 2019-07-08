package com.kail.rock_paper_scissors;

public interface RockPaperScissorsPlayer {
    // Used by playground to notify you that a new match will start.
    void setNewMatch(String opponentName);
    
    // Used by playground to get your game shape (values: "R", "P" or "S").
    String getShape();
    
    // Used by playground to inform you about the shape your opponent played in the game.
    void setOpponentShape(String shape);
}
