package model;



/**
 * Stores the current state of the game and controls the main game rules.
 * 
 * This is where the game keeps track of objects such as the player,
 * walls, gems, zombies, score, lives, and levels.
 * 
 * GameModel should update the game state, but it should not draw anything.
 * Drawing belongs in GameComponent.
 */

public class GameModel {
	
	// Work on the lab to complete GameModel and Player for initial setup

	    private Player player;

	    public GameModel() {
	        this.player = new Player(5, 5);
	    }

	    public Player getPlayer() { return player; }

	    public void movePlayerUp()    { player.moveBy(-1, 0); }
	    public void movePlayerDown()  { player.moveBy(1, 0);  }
	    public void movePlayerLeft()  { player.moveBy(0, -1); }
	    public void movePlayerRight() { player.moveBy(0, 1);  }
	    
	   
	}


