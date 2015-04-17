package fr.uha.ensisa.anass.Jump.Framework;

import fr.uha.ensisa.anass.Jump.R;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;

public class GamePanel extends SurfaceView implements Callback {
	public static int screenWidth;
	public static int screenHeight;
	public static float screenDensity;

	private Game game;
	private GameLoopThread gameLoopThread;

	public GamePanel(Context context) {
		super(context);
		Log.e("Where am I?: ", "First Constructor");
		// Focus must be on GamePanel so that events can be handled.
		this.setFocusable(true);
		// For intercepting events on the surface.
		this.getHolder().addCallback(this);
		this.getHolder().setFixedSize(810, 360);
	}

	public GamePanel( Context context, AttributeSet attrs) {
		super(context, attrs);

		Log.e("Where am I?: ", "Second Constructor");
		// TODO Auto-generated constructor stub
		// Focus must be on GamePanel so that events can be handled.
		this.setFocusable(true);
		// For intercepting events on the surface.
		this.getHolder().addCallback(this);
		this.getHolder().setFixedSize(810, 360);

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		System.out.println("Created");
		GamePanel.screenWidth = 810;
		GamePanel.screenHeight = 360;

		Log.e("svX: ", getWidth() + "");
		Log.e("svY: ", getHeight() + "");

		GamePanel.screenDensity = getResources().getDisplayMetrics().density;
		// We can now safely start the game loop.
		startGame();

	}

	private void startGame() {
		game = new Game(getResources());

		gameLoopThread = new GameLoopThread(this.getHolder(), game);

		gameLoopThread.running = true;
		gameLoopThread.start();

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		Log.e("sv changed X: ", width + "");
		Log.e("sv changed Y: ", height + "");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		gameLoopThread.running = false;

		// Shut down the game loop thread cleanly.
		boolean retry = true;
		while (retry) {
			try {
				gameLoopThread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();

		if (action == MotionEvent.ACTION_DOWN) {
			System.out.println("Down");
			game.touchEvent_actionDown(event);
		}

		if (action == MotionEvent.ACTION_MOVE) {
			System.out.println("Move");
			game.touchEvent_actionMove(event);
		}

		if (action == MotionEvent.ACTION_UP) {
			System.out.println("Up");
			game.touchEvent_actionUp(event);
		}

		return true;
	}

}
