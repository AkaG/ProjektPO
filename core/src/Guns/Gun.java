package Guns;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import PartsOfWorld.Bullet;
import PartsOfWorld.Player;


public abstract class Gun extends Rectangle {

	protected ArrayList<Bullet> bullets;
	protected int bulletCount;
	protected Player myPlayer;
    protected boolean canShoot;
    protected float bulletSpeed;
    protected float timePerShoot;
    protected float timer = 0;
    protected int damage;
   
    protected Texture bulletTexture;
    protected Texture gunTexture;
	

	public Gun(Player player)
	{
		this.myPlayer = player;
		this.x = player.x;
		this.y = player.y;
		this.width = 30;
		this.height = 15;
		this.canShoot = true;
		this.timePerShoot = 0.5f;
		this.bulletSpeed = 500;
		this.damage = 10;
		this.bullets = new ArrayList<Bullet>();
		
		bulletTexture = new Texture("bullet.png");
		gunTexture = new Texture("m4a1.png");
	}
	
	public void draw(SpriteBatch batch)
	{
		if(myPlayer.getDir() == Player.Direction.RUN_LEFT || (myPlayer.getDir() == Player.Direction.STAY_LEFT)){
		batch.draw(gunTexture, myPlayer.getX()+20 +width/4, myPlayer.getY()+myPlayer.getHeight()/2 - height/2, -width, height); 
		}
		else if(myPlayer.getDir() == Player.Direction.RUN_RIGHT || (myPlayer.getDir() == Player.Direction.STAY_RIGHT)){
			batch.draw(gunTexture, myPlayer.getX()+20, myPlayer.getY()+myPlayer.getHeight()/2 - height/2, width, height); 
		}
	}

	public Texture getGunTexture() {
		return gunTexture;
	}

	public void shoot() //na razie tylko
	{
		if(canShoot){
		bullets.add(new Bullet(myPlayer.x, myPlayer.y, myPlayer,bulletTexture,bulletSpeed,this.width));
		canShoot = false;
		}
	}
	
	public void update(float delta)
	{
		//USTAWIANIE CO ILE MOZNA STRZELAC
		timer += delta;
		
		if(timer >= timePerShoot)
    	{
    		canShoot = true;
    		timer -= timePerShoot;
    	}
		
		//PORUSZANIE POCISKAMI
    	for(Bullet b: getBullets())
    	{
    		b.move();
    	}
		
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
	
	public boolean isCanShoot() {
		return canShoot;
	}

	public void setCanShoot(boolean canShoot) {
		this.canShoot = canShoot;
	}
	
	public int getDamage() {
		return damage;
	}
	
}
