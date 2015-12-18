package game.api;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

import PartsOfWorld.Bullet;
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

	ArrayList<Player> players;
	HumanPlayer player; 
	HumanPlayer2 player2;
	KubaAI ai;
	
	public	Platform platform;

	InputMultiplexer multiplexer;
	
	public GameWorld()
	{
		 player = new HumanPlayer(600, 100);
		 player2 = new HumanPlayer2(100, 100);
		 platform = new Platform();
		 ai = new KubaAI(400, 100);
		 
		 players = new ArrayList<Player>();
		 players.add(player);
		 players.add(player2);
		 players.add(ai);
		 
		 multiplexer = new InputMultiplexer();
		 
		 multiplexer.addProcessor(player);
		 multiplexer.addProcessor(player2);
		 
		 Gdx.input.setInputProcessor(multiplexer);
	}

    public void update(float delta) {
    	
    	for(Player p: players)
    	{
    		p.update(delta);
    		
    		
    		if(isOnPlatform(platform, p))
    			{
    				p.setCanJump(true);
    				p.setySpeed(0);
    				p.setY(platform.getY()+platform.getHeight());
    			}
    		
    	}
    	
        
        ///////////////////////////Kolizja z pociskami
    	
    	
    	//Przepisze to, bo nie s¹ pociski usuwane,
    	
    	for(int i = 0 ;i < players.size();i++)
    	{
        	for(Bullet b: players.get(i).getGun().getBullets())
        	{
        		for(int j = 0;j<players.size();j++)
        		if(players.get(j).contains(b.getX(), b.getY()) && j!=i)
    			{
    				players.get(j).takeDamage(20);
    				//players.get(i).getGun().getBullets().remove(b);
    				b.y = -1000; //bardzo profesjonalne rozwiazanie,
    			}
        	}
    	}
    	
    	 
        
    }
    	
    
    private boolean isOnPlatform(Platform p, Player player) {
		return player.getySpeed() <= 0 && player.overlaps(p)
				&& !(player.y <= p.y);
	}
    
    /////////////////////// SETTERY I GETTERY

	public Player getPlayer(int playerNr) {
		
		Player tmp = null;
		
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
