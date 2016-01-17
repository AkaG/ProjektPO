package Guns;

import com.badlogic.gdx.graphics.Texture;

import PartsOfWorld.Player;

public class AssaultRifle extends Gun{

	public AssaultRifle(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		timePerShoot = 0.15f;
		bulletSpeed = 500;
		damage = 5;
		
		width = 30;
		height = 15;
		gunTexture = new Texture("m4a1.png");
		
	}

	
}