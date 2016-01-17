package Guns;

import com.badlogic.gdx.graphics.Texture;

import PartsOfWorld.Bullet;
import PartsOfWorld.Player;

public class Shotgun extends Gun{

	public Shotgun(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		timePerShoot = 1.5f;
		bulletSpeed = 500;
		damage = 15;
		
		width = 40;
		height = 10;
		gunTexture = new Texture("shotgun.png");
	}
	
	public void shoot()
	{
		if(canShoot){
		bullets.add(new Bullet(myPlayer.x, myPlayer.y+5, myPlayer,bulletTexture,bulletSpeed,this.width));
		bullets.add(new Bullet(myPlayer.x, myPlayer.y-5, myPlayer,bulletTexture,bulletSpeed,this.width));
		bullets.add(new Bullet(myPlayer.x, myPlayer.y, myPlayer,bulletTexture,bulletSpeed,this.width));
		canShoot = false;
		}
	}

	
}
