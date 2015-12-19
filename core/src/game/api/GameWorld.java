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

	private ArrayList<Player> players;
	private HumanPlayer player;
	private HumanPlayer2 player2;
	private KubaAI ai;

	private ArrayList<Platform> platforms;

	InputMultiplexer multiplexer;

	public GameWorld() {
		// TWORZENIE GRACZY
		player = new HumanPlayer(600, 100);
		player2 = new HumanPlayer2(100, 100);
		ai = new KubaAI(400, 100);
		
		// DODAWANIE GRACZY NA LISTE
		players = new ArrayList<Player>();
		players.add(player);
		players.add(player2);
		players.add(ai);
		
		// DODAWANIE PLATFORM
		platforms = new ArrayList<Platform>();
		platforms.add(new Platform(250, 150, 100, 20));
		platforms.add(new Platform(450, 250, 50, 20));
		platforms.add(new Platform(150, 350, 400, 20));

		// DODAWANIE OBSLUGI KLAWIATURY
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(player);
		multiplexer.addProcessor(player2);

		Gdx.input.setInputProcessor(multiplexer);
	}

	public void update(float delta) {

		Iterator<Player> pl = players.iterator();
		while (pl.hasNext()) {
			Player player = pl.next();
			
			//AKTUALIZACJA PLAYEROW
			player.update(delta);
			
			//KOLIZJA Z PLATFORMAMI
			for (Platform platform : platforms)
				if (isOnPlatform(platform, player)) {
					player.setCanJump(true);
					player.setySpeed(0);
					player.setY(platform.getY() + platform.getHeight());
				}

			// SPRAWDZANIE CZY ZOSTALO ZYCIE
			if (player.getHealthPoints().width < 0) {
				pl.remove();
			}
		}

		
		//KOLIZJA Z POCISKAMI
		for (int i = 0; i < players.size(); i++) {
			Iterator<Bullet> b = players.get(i).getGun().getBullets().iterator();
			while (b.hasNext()) {
				Bullet bullet = b.next();
				for (int j = 0; j < players.size(); j++) {
					if (players.get(j).contains(bullet.getX(), bullet.getY()) && j != i) {
						players.get(j).takeDamage(6);
						b.remove();
					}
				}
			}
		}

	}

	public ArrayList<Platform> getPlatforms() {
		return platforms;
	}

	private boolean isOnPlatform(Platform p, Player player) {
		return player.getySpeed() <= 0 && player.overlaps(p) && !(player.y <= p.y);
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

	public ArrayList<Player> getPlayers() {
		return players;
	}

}
