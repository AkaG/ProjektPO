package game.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Rectangle;

import PartsOfWorld.HumanPlayer;
import PartsOfWorld.HumanPlayer2;
import PartsOfWorld.KubaAI;
import PartsOfWorld.Platform;
import PartsOfWorld.Player;

/*
 * 
 *  	TUTAJ SOBIE ODWZOROWUJEMY CALY SWIAT
 * 
 */

public class GameWorld {

	HumanPlayer player; 
	HumanPlayer2 player2;
	
	public	Platform platform;
	KubaAI ai;
	
	InputMultiplexer multiplexer;
	
	public GameWorld()
	{
		 player = new HumanPlayer(600, 100);
		 player2 = new HumanPlayer2(100, 100);
		 platform = new Platform();
		 ai = new KubaAI(400, 100);
		 
		 multiplexer = new InputMultiplexer();
		 
		 multiplexer.addProcessor(player);
		 multiplexer.addProcessor(player2);
		 
		 Gdx.input.setInputProcessor(multiplexer);
	}

    public void update(float delta) {
        player.update(delta);
        player2.update(delta);
        ai.AIUpdate(delta);
       
        if (isOnPlatform(platform, player)) {
			player.setCanJump(true);
			player.setySpeed(0);
			player.y = platform.y + platform.height;
		}
        
        if (isOnPlatform(platform, player2)) {
			player2.setCanJump(true);
			player2.setySpeed(0);
			player2.y = platform.y + platform.height;
		}
       
	}
    
    private boolean isOnPlatform(Platform p, Player player) {
		return player.getySpeed() <= 0 && player.overlaps(p)
				&& !(player.y <= p.y);
	}
    
    /////////////////////// SETTERY I GETTERY

	public Player getPlayer(int playerNr) {
		
		Player tmp = new Player(1, 1);
		
		switch (playerNr) {
		case 1:
			tmp = player;
			break;

		case 2:
			tmp = player2;
			break;
			
		default:
			break;
		}	
		
		return tmp;
	}

	
	public Player getAI() {
		return ai;
	}

}
