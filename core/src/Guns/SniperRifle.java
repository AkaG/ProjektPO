package Guns;

import com.badlogic.gdx.graphics.Texture;

import PartsOfWorld.Player;

public class SniperRifle extends Gun{

	public SniperRifle(Player player) {
		super(player);
		
		timePerShoot = 2f;
		bulletSpeed = 1500;
		damage = 30;
		
		width = 50;
		height = 10;
		gunTexture = new Texture("sniperrifle.png");
		
		
	}
	
	

}
