package fr.uha.ensisa.anass.Jump.Game;

import fr.uha.ensisa.anass.Jump.R;
import fr.uha.ensisa.anass.Jump.Framework.Animation;
import fr.uha.ensisa.anass.Jump.Framework.Game;
import android.graphics.Bitmap;
import android.graphics.Canvas;

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


	public Avatar() {
		this.initialize();
		this.loadContent();
	}

	private void initialize() {
		avatar_image = null;
		x = 180;
		y = 320;
		speedX = 0;
		speedY = 0;
		jumpcount = 0;
		canjump = true;
		isDead = false;

	}

	private void loadContent() {
		
		avatar_image = Game.resourceProvider.getImage(R.drawable.avatar);

	}

	public void draw(Canvas canvas) {

		canvas.drawBitmap(avatar_image, (int) x, (int) (y), null);

	}

	/**
	 * Here we move the avatar.
	 */
	public void update(Map map) {
		speedX = 0;
		// speedY = 0;
		str = "STOPED";
		if (Canvas.keyboardKeyState(KeyEvent.VK_RIGHT)) {
			str = "RIGHT";
			int uptile = map.getrightUptiletype(x, y + 3);
			int downtile = map.getrightDowntiletype(x, y - 2);

			if (uptile == 0 && downtile == 0)
				speedX = 1.8f;
			// TODO 7eyd else
			else if (uptile == 2 || downtile == 2)
				isDead = true;
			previous = "R";

		}

		if (Canvas.keyboardKeyState(KeyEvent.VK_LEFT)) {
			str = "LEFT";
			int uptile = map.getleftUptiletype(x, y + 3);
			int downtile = map.getleftDowntiletype(x, y - 2);

			if (uptile == 0 && downtile == 0)
				speedX = -1.8f;
			// TODO 7eyd else
			else if (uptile == 2 || downtile == 2)
				isDead = true;
			previous = "L";
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

		if (Canvas.keyboardKeyState(KeyEvent.VK_UP)) {
			str = "JUMP";

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

		avatar_annim_R.changeCoordinates((int) x, (int) y);
		avatar_annim_L.changeCoordinates((int) x, (int) y);

	}

	public void move() {
		x += speedX;
		y += speedY;

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
