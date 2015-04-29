package fr.uha.ensisa.anass.Jump.Game;

import fr.uha.ensisa.anass.Jump.R;
import fr.uha.ensisa.anass.Jump.Framework.Animation;
import fr.uha.ensisa.anass.Jump.Framework.Game;
import fr.uha.ensisa.anass.Jump.Framework.InputHundler;
import fr.uha.ensisa.anass.Jump.Framework.KeyState;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Avatar {
	private final int WIDTH = 18;
	private final int HEIGHT = 18;

	/**
	 * X coordinate of the avatar.
	 */
	private float x;

	/**
	 * Y coordinate of the avatar.
	 */
	private float y;

	private float speedX;
	private float speedY;

	private boolean canjump;
	private int jumpcount;

	public static boolean isDead;

	/**
	 * image of the Avatar.
	 */
	private Bitmap avatar_image;
	private Rect destRect;


	public Avatar() {
		this.initialize();
		this.loadContent();
	}

	private void initialize() {
		avatar_image = null;
		x = 180;
		y = 324;
		speedX = 0;
		speedY = 0;
		jumpcount = 0;
		canjump = true;
		isDead = false;
		destRect = new Rect();

	}

	private void loadContent() {
		
		avatar_image = Game.resourceProvider.getImage(R.drawable.avatar);

	}

	public void draw(Canvas canvas) {
		destRect.set((int)this.x, (int)this.y, (int)(this.x+this.WIDTH),(int)( this.y+this.HEIGHT));
		canvas.drawBitmap(avatar_image, null,this.destRect,null);

	}

	
	
	public void update(Map map) {
		
		speedX = 0;
		// speedY = 0;
		
		if (InputHundler.KeyRight == KeyState.DOWN) {
			
			int uptile = map.getrightUptiletype(x, y + 3);
			int downtile = map.getrightDowntiletype(x, y - 2);

			if (uptile == 0 && downtile == 0)
				speedX = 1.8f;
			// TODO 7eyd else
			else if (uptile == 2 || downtile == 2)
				isDead = true;
			

		}

		if (InputHundler.KeyLeft == KeyState.DOWN) {
			
			int uptile = map.getleftUptiletype(x, y + 3);
			int downtile = map.getleftDowntiletype(x, y - 2);

			if (uptile == 0 && downtile == 0)
				speedX = -1.8f;
			// TODO 7eyd else
			else if (uptile == 2 || downtile == 2)
				isDead = true;
			
		}

		int ldowntile = map.getleftDowntiletype(x + 2, y);
		int rdowntile = map.getrightDowntiletype(x - 2, y);

		if (ldowntile == 0 && rdowntile == 0) {
			speedY += 0.5f;
			if (speedY > 2.0f)
				speedY = 2.0f;
		} else if (ldowntile == 1 || rdowntile == 1 || ldowntile == 3 || rdowntile == 3) {
			speedY = 0;
			jumpcount = 0;
		}
		// TODO 7eyd else
		else if (ldowntile == 2 || rdowntile == 2)
			isDead = true;

		if (InputHundler.KeyUp == KeyState.DOWN) {
			

			if (canjump && jumpcount == 0) {
				speedY = -6.0f;
				canjump = false;
				jumpcount++;
				System.out.println("here1");
			}

			if (canjump && jumpcount == 1) {
				speedY = -5.0f;
				jumpcount++;
				canjump = false;
				System.out.println("here2");
			}
			canjump = false;

		} else {
			canjump = true;

		}

		int luptile;
		int ruptile;
		float i;
		for (i = y; i > y + speedY; i--) {
			luptile = map.getleftUptiletype(x + 2, i);
			ruptile = map.getrightUptiletype(x - 2, i);
			if (luptile == 1 || ruptile == 1 || luptile == 3 || ruptile == 3) {
				speedY = i - y;
				break;
			}
			// TODO 7eyd else
			else if (luptile == 2 || ruptile == 2) {
				speedY = i - y;
				isDead = true;
				break;
			}
		}

		move();
	}

	public void move() {
		x += speedX;
		y += speedY;

	}
	
	public  void startMoveLeft() {
		// TODO Auto-generated method stub
		speedX = speedX - 1.0f;
//		if(speedX < -4)
//			speedX = -5;
		Log.e("Mvt", "start left, speedX="+speedX);
	}

	public  void stopMoveLeft() {
		// TODO Auto-generated method stub
		speedX = 0;
		Log.e("Mvt", "stop left");
	}

	public  void startMoveRight() {
		// TODO Auto-generated method stub
//		speedX += 0.2f;
//		if(speedX > 4)
			speedX = 5;
		Log.e("Mvt", "start right");
	}

	public  void stopMoveRight() {
		// TODO Auto-generated method stub
		speedX = 0;
		Log.e("Mvt", "stop right");
	}

	public  void startMoveUp() {
		// TODO Auto-generated method stub
		Log.e("Mvt", "start up");
	}

	public  void stopMoveUp() {
		// TODO Auto-generated method stub
		Log.e("Mvt", "stop up");
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
