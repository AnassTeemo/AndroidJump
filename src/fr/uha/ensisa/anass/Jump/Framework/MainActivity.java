package fr.uha.ensisa.anass.Jump.Framework;


import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import fr.uha.ensisa.anass.Jump.R;
import fr.uha.ensisa.anass.Jump.Game.Avatar;

public class MainActivity extends Activity {

	private LayoutInflater layoutinflater;
	private  InputHundler inputhundler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layoutinflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		setContentView(R.layout.activity_main);
	}

	public void onClickStartGame(View v){
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);

		RelativeLayout fl = (RelativeLayout) layoutinflater.inflate(R.layout.surface_layout, null);
		SurfaceView sv = (SurfaceView) fl.findViewById(R.id.sv1);
		//LinearLayout ll = (LinearLayout) fl.findViewById(R.id.ll1);
		//FrameLayout.LayoutParams flp = (FrameLayout.LayoutParams) sv.getLayoutParams();
		//Log.e("layoutparams is: ", "("+flp.width + "," + flp.height + ")");
		sv.setLayoutParams(new RelativeLayout.LayoutParams(size.x, (int) (size.y*0.85)));
		//ll.setLayoutParams(new FrameLayout.LayoutParams(size.x, (int) (size.y*0.15)));
		ImageView ivl = (ImageView) fl.findViewById(R.id.ivLeftArrow);
		ImageView ivr = (ImageView) fl.findViewById(R.id.ivRightArrow);
		ImageView ivu = (ImageView) fl.findViewById(R.id.ivUpArrow);
		
		inputhundler = new InputHundler(ivl, ivr, ivu);

		
		setContentView(fl);
		
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
