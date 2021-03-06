package fr.uha.ensisa.anass.Jump.Game;

import fr.uha.ensisa.anass.Jump.Framework.MainActivity;
import android.content.res.Resources;
import android.graphics.Canvas;

public class Level {
	private Avatar avatar;
	private Map map;
	//private Cannon cannon;
	//private Door exitDoor;
	//private MovingSquares movingSquares;
	
	private boolean completed;

	public Level() {
		completed = false;
		avatar = new Avatar();
		map = new Map();
		//cannon = new Cannon();
		//exitDoor = new Door(742, 312);
		//movingSquares = new MovingSquares(map.getMovingSqaures());
	}

	public void upDate(long gameTime) {
		avatar.update(map);
		//exitDoor.update(this, avatar);
		//cannon.update(avatar, map);
		//movingSquares.update(avatar, map ,gameTime);
	}

	public void draw(Canvas canvas) {
		map.draw(canvas);
		//exitDoor.draw(g2d);
		//cannon.draw(g2d);
		//movingSquares.draw(g2d);
		avatar.draw(canvas);
	}

	public void restart(){
		//Avatar.isDead = false;
		//avatar.setX(180);
		//avatar.setY(324);
		//movingSquares.initialize(map);
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
