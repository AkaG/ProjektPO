package game.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/*
 * 
 *  	TUTAJ SOBIE ODWZOROWUJEMY CALY SWIAT
 * 
 */

public class GameWorld {

	Player player; 
	
	public GameWorld()
	{
		 player = new Player(33, 500, 50, 50);
	}

    public void update(float delta) {
        player.update(delta);
    }
    
    /////////////////////// SETTERY I GETTERY

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
