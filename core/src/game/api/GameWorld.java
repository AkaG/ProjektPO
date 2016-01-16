package game.api;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;

import PartsOfWorld.AdamAI;
import PartsOfWorld.Bullet;
import PartsOfWorld.HumanPlayer;
import PartsOfWorld.HumanPlayer2;
import PartsOfWorld.KubaAI;
import PartsOfWorld.MarcinAI;
import PartsOfWorld.MateuszAI;
import PartsOfWorld.MikolajAI;
import PartsOfWorld.Platform;
import PartsOfWorld.Player;
import game.api.MyGame.GameMode;


public class GameWorld {

	private ArrayList<Player> players;
	private HumanPlayer player;
	private HumanPlayer2 player2;
	private KubaAI ai;
	private AdamAI adamAI;

	private ArrayList<Platform> platforms;
	
	Texture backgroundTexture;

	InputMultiplexer multiplexer;
	
	GameMode mode;

	public GameWorld() {
		
		players = new ArrayList<Player>();
		multiplexer = new InputMultiplexer();
		
		// WYBÓR TRYBU GRY
		mode = MyGame.mode;
		switch (mode) {
		case PLAYER_VS_PLAYER:
			
			// TWORZENIE GRACZY I DODAWANIE ICH NA LISTE
			player = new HumanPlayer(600, 100, Player.TypeOfGun.SHOTGUN);
			player2 = new HumanPlayer2(100, 100, Player.TypeOfGun.SNIPER_RIFLE);
			players.add(player);
			players.add(player2);
			
			// OBSLUGA KLAWIATURY
			multiplexer.addProcessor(player);
			multiplexer.addProcessor(player2);
			break;
			
		case PLAYER_VS_CPU:
			
			// TWORZENIE GRACZY I DODAWANIE ICH NA LISTE
			player = new HumanPlayer(100, 100, Player.TypeOfGun.SNIPER_RIFLE);
			players.add(player);
			players.add(new AdamAI(600, 500, Player.TypeOfGun.PISTOL));
			
			// OBSLUGA KLAWIATURY
			multiplexer.addProcessor(player);
			break;
			
		case CPU_VS_CPU:
			
			// TWORZENIE GRACZY I DODAWANIE ICH NA LISTE
			players.add(new AdamAI(600, 500, Player.TypeOfGun.PISTOL));
			players.add(new KubaAI(400, 500, Player.TypeOfGun.PISTOL));
			players.add(new MateuszAI(600, 200, Player.TypeOfGun.PISTOL));
			players.add(new MarcinAI(150, 400, Player.TypeOfGun.PISTOL));
			players.add(new MikolajAI(200, 200, Player.TypeOfGun.PISTOL));			
			break;

		}

		Gdx.input.setInputProcessor(multiplexer);
		
		// DODAWANIE PLATFORM
		platforms = new ArrayList<Platform>();
		platforms.add(new Platform(0, -15, 800, 25));
		platforms.add(new Platform(100, 100, 600, 20));
		platforms.add(new Platform(550, 250, 200, 20));
		platforms.add(new Platform(50, 250, 200, 20));
		platforms.add(new Platform(225, 400, 350, 20));
		
		backgroundTexture = new Texture("background.png");

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
			
			
			//AKTUALIZACJA CZASU PISTOLETOW
				player.getGun().update(delta);
			
		}

		
		//KOLIZJA Z POCISKAMI
		for (int i = 0; i < players.size(); i++) {
			Iterator<Bullet> b = players.get(i).getGun().getBullets().iterator();
			while (b.hasNext()) {
				Bullet bullet = b.next();
				for (int j = 0; j < players.size(); j++) {
					if (players.get(j).contains(bullet.getX(), bullet.getY()) && j != i) {
						players.get(j).takeDamage(players.get(i).getGun().getDamage());
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
	

	public Texture getBackgroundTexture() {
		return backgroundTexture;
	}


}
