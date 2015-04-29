package fr.uha.ensisa.anass.Jump.Framework;

import fr.uha.ensisa.anass.Jump.R;
import fr.uha.ensisa.anass.Jump.Game.Level;
import fr.uha.ensisa.anass.Jump.Game.Map;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Game {


	private int gameTimeSec;
	
	private Rect destBackgroundImage;
	private Bitmap background;
	
	private Level lvl;
	public static ResourceProvider resourceProvider;

	public Game(Resources resources) {
		
		this.Initialize(resources);

		this.LoadContent();

		this.ResetGame();
	}
	
	private void Initialize(Resources resources){
		resourceProvider = new ResourceProvider(resources);
		lvl = new Level();
		destBackgroundImage = new Rect(0, 0, GamePanel.screenWidth, GamePanel.screenHeight);
	}

	/**
	 * Load files.
	 */
	private void LoadContent() {
		background = Game.resourceProvider.getImage(R.drawable.backgound);
	}

	/**
	 * For (re)setting some game variables before game can start.
	 */
	private void ResetGame() {

	}

	/**
	 * Game update method.
	 * 
	 * @param gameTime
	 *            Elapsed game time in milliseconds.
	 */
	public void Update(long gameTime) {
		this.gameTimeSec = (int) (gameTime / 1000);
		lvl.upDate(gameTime);
	}

	/**
	 * Draw the game to the screen.
	 * 
	 * @param canvas
	 *            Canvas on which we will draw.
	 */
	public void Draw(Canvas canvas) {
		// First we need to erase everything we draw before.
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(background, null, this.destBackgroundImage, null);
		
		lvl.draw(canvas);
		
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setTextSize(20);

		canvas.drawText("Game is running: " + gameTimeSec + " sec",
				GamePanel.screenWidth / 4, GamePanel.screenHeight / 2, paint);
		
		
	}

	
	
}
