package game.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import PartsOfWorld.KubaAI;
import PartsOfWorld.Platform;
import PartsOfWorld.Player;

/*
 * 
 *  	TUTAJ SOBIE ODWZOROWUJEMY CALY SWIAT
 * 
 */

public class GameWorld {

	Player player; 
	public	Platform platform;
	KubaAI ai;
	
	public GameWorld()
	{
		 player = new Player(33, 500);
		 platform = new Platform();
		 ai = new KubaAI(40, 500);
		 Gdx.input.setInputProcessor(player);
	}

    public void update(float delta) {
        player.update(delta);
        ai.AIUpdate(delta);
       
        if (isOnPlatform(platform)) {
			player.setCanJump(true);
			player.setySpeed(0);
			player.y = platform.y + platform.height;
		}
        
        
       
	}
    
    private boolean isOnPlatform(Platform p) {
		return player.getySpeed() <= 0 && player.overlaps(p)
				&& !(player.y <= p.y);
	}
    
    /////////////////////// SETTERY I GETTERY

	public Player getPlayer() {
		return player;
	}
	
	public Player getAI() {
		return ai;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
