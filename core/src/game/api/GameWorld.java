package game.api;

import java.io.Console;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.sun.javafx.geom.Vec2f;

import Guns.AssaultRifle;
import Guns.Pistol;
import Guns.Shotgun;
import Guns.SniperRifle;
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
import PartsOfWorld.Player.TypeOfGun;
import game.api.MyGame.GameMode;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;


public class GameWorld {

	private ArrayList<Player> players;
	private HumanPlayer player;
	private HumanPlayer2 player2;
	private KubaAI ai;
	private AdamAI adamAI;

	private ArrayList<Platform> platforms;
	private ArrayList<Vec2f> playersPosition;
	private ArrayList<Vec2f> bulletsPosition;
	
	
	Texture backgroundTexture;

	InputMultiplexer multiplexer;
	
	GameMode mode;
	
	private boolean gameOver;
	private String winnerName;

	public GameWorld() {
		
		players = new ArrayList<Player>();
		playersPosition = new ArrayList<Vec2f>();
		bulletsPosition = new ArrayList<Vec2f>();
		multiplexer = new InputMultiplexer();
		
		gameOver = false;
		winnerName = "";
		
		// WYBÓR TRYBU GRY
		mode = MyGame.mode;
		switch (mode) {
		case PLAYER_VS_PLAYER:
			
			// TWORZENIE GRACZY I DODAWANIE ICH NA LISTE
			player = new HumanPlayer(600, 100, Player.TypeOfGun.PISTOL);
			player2 = new HumanPlayer2(100, 100, Player.TypeOfGun.PISTOL);
			players.add(player);
			players.add(player2);
			
			// OBSLUGA KLAWIATURY
			multiplexer.addProcessor(player);
			multiplexer.addProcessor(player2);
			break;
			
		case PLAYER_VS_CPU:
			
			// TWORZENIE GRACZY I DODAWANIE ICH NA LISTE
			player = new HumanPlayer(100, 100, Player.TypeOfGun.PISTOL);
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
		playersPosition.clear();
		bulletsPosition.clear();
		
		Iterator<Player> pl = players.iterator();
		while (pl.hasNext()) {
			Player player = pl.next();
			
			ArrayList<Vec2f> tempPlayerPositions = new ArrayList<Vec2f>();
			ArrayList<Vec2f> tempBulletsPositions = new ArrayList<Vec2f>();
			
			//DODAWANIE POZYCJI INNYCH GRACZY I ICH POCISKOW
			for(Player p: players)
			{
				if(player != p)
				{
					for(Bullet b :p.getGun().getBullets())
					{
						if(b.getX() > 0 && b.getX() < Gdx.graphics.getWidth())
						{
							tempBulletsPositions.add(new Vec2f(b.getX(),b.getY()));
						}
					
					}
					
					tempPlayerPositions.add(new Vec2f(p.getX(),p.getY()));
				}
			}
			
			
			for(Vec2f t : playersPosition)
			{
				if(t.x != player.getX() && t.y != player.getY())
					tempPlayerPositions.add(t);
			}
			
			
			//AKTUALIZACJA PLAYEROW
			player.update(delta);
			player.updateEnemyPosition(tempPlayerPositions);
			player.updateEnemyBulletsPositions(tempBulletsPositions);

			//KOLIZJA Z PLATFORMAMI
			for (Platform platform : platforms)
				if (isOnPlatform(platform, player)) {
					player.setCanJump(true);
					player.setySpeed(0);
					player.setY(platform.getY() + platform.getHeight());
				}

			// SPRAWDZANIE CZY ZOSTALO ZYCIE
			if (player.getHealthPoints().width < 0) {
				// JESLI JESZCZE MA BRON DOSTAJE NASTEPNE ZYCIE
					player.decrementLifes();
					switch(player.getGunType()){
					case PISTOL:
						player.setHealthPoints(100);
						player.setGun(new SniperRifle(player));
						player.setGunType(TypeOfGun.SNIPER_RIFLE);
						player.setPosition(400, 500);
						break;
					case SNIPER_RIFLE:
						player.setHealthPoints(100);
						player.setGun(new AssaultRifle(player));
						player.setGunType(TypeOfGun.ASSAULT_RIFLE);
						player.setPosition(400, 500);
						break;
					case ASSAULT_RIFLE:
						player.setHealthPoints(100);
						player.setGun(new Shotgun(player));
						player.setGunType(TypeOfGun.SHOTGUN);
						player.setPosition(400, 500);
						break;
					case SHOTGUN:
						pl.remove();
						break;
				}
			}
			
			
			//AKTUALIZACJA CZASU PISTOLETOW
				player.getGun().update(delta);
			
		}
		
		// JESLI ZOSTAL 1 GRACZ KONIEC GRY
		if(players.size() == 1){
			gameOver = true;
			winnerName = players.get(0).getName();
		}

		
		//KOLIZJA Z POCISKAMI
		for (int i = 0; i < players.size(); i++) {
			Iterator<Bullet> b = players.get(i).getGun().getBullets().iterator();
			while (b.hasNext()) {
				Bullet bullet = b.next();
				for (int j = 0; j < players.size(); j++) {
					if (players.get(j).contains(bullet.getX(), bullet.getY()) && j != i) {
						players.get(j).takeDamage(players.get(i).getGun().getDamage());
						bullet.setY(-100);
					}
				}
			}
		}
		
		for(Player p: players)
		{
			//USUWANIE POCISKOW POZA MAPA
			for(int i = 0;i<p.getGun().getBullets().size();)
			{
				if(p.getGun().getBullets().get(i).getX() < 0 || p.getGun().getBullets().get(i).getX() > Gdx.graphics.getWidth())
				{
					p.getGun().getBullets().remove(i);
					
				}
				else
				{
					i++;
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
	
	public boolean getGameOver(){
		return gameOver;
	}
	
	public String getWinnerName(){
		return winnerName;
	}

}
