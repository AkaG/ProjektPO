package PartsOfWorld;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;

// to mia³a byæ jakaœ abstrakcyjna klasa?

public class Gun extends Rectangle {

	int bulletCount;
	
	ArrayList<Bullet> bullets;
	
	Player myPlayer;
	
	Gun(Player player)
	{
		myPlayer = player;
		this.x = player.x;
		this.y = player.y;
		bullets = new ArrayList<Bullet>();
	}
	
	public void shoot() //na razie tylko
	{
		bullets.add(new Bullet(myPlayer.x, myPlayer.y, myPlayer));
	}
	
	public int getBulletCount() {
		return bulletCount;
	}

	public void setBulletCount(int bulletCount) {
		this.bulletCount = bulletCount;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}

	public Player getMyPlayer() {
		return myPlayer;
	}

	public void setMyPlayer(Player myPlayer) {
		this.myPlayer = myPlayer;
	}

	public void shootAtAngle() {
		
	}
	
	public void shootAtPosition(){
		
	}
	
}
