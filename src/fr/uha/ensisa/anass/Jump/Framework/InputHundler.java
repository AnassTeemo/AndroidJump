package fr.uha.ensisa.anass.Jump.Framework;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import fr.uha.ensisa.anass.Jump.Game.Avatar;

public class InputHundler {
	
	public static KeyState KeyLeft;
	public static KeyState KeyRight;
	public static KeyState KeyUp;
	
	private ImageView left;
	private ImageView right;
	private ImageView up;
	
	public InputHundler(ImageView left, ImageView right, ImageView up) {
		this.left = left;
		this.right = right;
		this.up = up;
		KeyLeft = KeyState.UP;
		KeyRight = KeyState.UP;
		KeyUp = KeyState.UP;
		
		HundleAvatarMouvement();
	}

	public void HundleAvatarMouvement() {
		
		left.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {			
				if(event.getAction() == MotionEvent.ACTION_DOWN)
					KeyLeft = KeyState.DOWN;
				if(event.getAction() == MotionEvent.ACTION_UP)
					KeyLeft = KeyState.UP;								
				return true;
			}
		});
		
		right.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN)
					KeyRight = KeyState.DOWN;
				if(event.getAction() == MotionEvent.ACTION_UP)
					KeyRight = KeyState.UP;
				return true;
			}
		});
		
		up.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN)
					KeyUp = KeyState.DOWN;
				if(event.getAction() == MotionEvent.ACTION_UP)
					KeyUp = KeyState.UP;
				return true;
			}
		});
	}
	
	

}
