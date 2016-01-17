package Guns;

import com.badlogic.gdx.graphics.Texture;

import PartsOfWorld.Player;

public class Pistol extends Gun{

	public Pistol(Player player) {
		super(player);
		
		timePerShoot = 0.5f;
		bulletSpeed = 500;
		damage = 10;
		
		width = 20;
		height = 5;
		gunTexture = new Texture("pistol.png");
		
	}

	
}
