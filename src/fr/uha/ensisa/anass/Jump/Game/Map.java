package fr.uha.ensisa.anass.Jump.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import fr.uha.ensisa.anass.Jump.R;
import fr.uha.ensisa.anass.Jump.Framework.Game;
import fr.uha.ensisa.anass.Jump.Framework.GamePanel;
import fr.uha.ensisa.anass.Jump.Framework.Point2D;


public class Map {
	private final int tileWidth;
	private final int tileHeight;
	private static Bitmap tileSet;
	private static Bitmap chouk;
	private int nbTilesX;
	private int nbTilesY;
	private Rect destRect;
	
	private int[][] map;



	public Map() {
		tileSet = Game.resourceProvider.getImage(R.drawable.square);
		chouk = Game.resourceProvider.getImage(R.drawable.chook);
		this.loadMapFile();
		Log.e("tilex", nbTilesX+"");
		Log.e("tiley", nbTilesY+"");
		tileWidth = GamePanel.screenWidth / nbTilesX;
		tileHeight= GamePanel.screenHeight / nbTilesY;
		destRect = new Rect();
	}

	public void loadMapFile()  {
		BufferedReader reader;
		try {
			reader = new BufferedReader( new InputStreamReader(Game.resourceProvider.getFile("maps/lvl1.txt")));
		
		String line = null;
		String[] parts = null;
		
		parts = reader.readLine().split(" ");
		this.nbTilesX = Integer.valueOf(parts[0]);
		this.nbTilesY = Integer.valueOf(parts[1]);

		map = new int[nbTilesY][nbTilesX];
		int i = 0;
		while ((line = reader.readLine()) != null) {
			parts = line.split(" ");
			for (int j = 0; j < nbTilesX; j++)
				map[i][j] = Integer.valueOf(parts[j]);
			i++;

		}
		
		reader.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void draw(Canvas canvas) {

		int drawpositionX;
		int drawpositionY = 0;

		for (int i = 0; i < nbTilesY; i++) {
			drawpositionX = 0;
			for (int j = 0; j < nbTilesX; j++) {
				destRect.set(drawpositionX, drawpositionY, drawpositionX+tileWidth, drawpositionY+tileHeight);
				
				if (map[i][j] == 1)					
					canvas.drawBitmap(this.tileSet, null, this.destRect, null);
				//drawImage(tileSet, drawpositionX, drawpositionY, tileWidth, tileHeight,null);
				if (map[i][j] == 2){
					canvas.drawBitmap(this.chouk, null, this.destRect, null);
					//drawImage(chouk, drawpositionX, drawpositionY, tileWidth, tileHeight,null);
				}
				drawpositionX += tileWidth;
			}

			drawpositionY += tileHeight;

		}
	}
	
	public ArrayList<Point2D> getMovingSqaures(){
		ArrayList<Point2D> tmp = new ArrayList<Point2D>();
		for(int i = 0; i < nbTilesY; i++)
			for(int j = 0; j < nbTilesX; j++)
				if(map[i][j] == 3)
					tmp.add(new Point2D(j, i));
		return tmp;
	}
	
	public void removeTile(Point2D p) {
		map[(int) p.y][(int) p.x] = 0;
	}
	
	public void initalizeTile(Point2D p){
		map[(int) p.y][(int) p.x] = 3;
	}
	
	public int getleftUptiletype(float x, float y){
		return map[(int)(y/18)][(int)(x/18)];
	}
	
	public int getleftDowntiletype(float x, float y){
		return map[(int)((y+18)/18)][(int)(x/18)];
	}
	
	public int getrightUptiletype(float x, float y){
		return map[(int)(y/18)][(int)((x+18)/18)];
	}
	
	public int getrightDowntiletype(float x, float y){
		return map[(int)((y+18)/18)][(int)((x+18)/18)];
	}
		
	public int[][] getMap() {
		return map;
	}

	public int getNbTilesX() {
		return nbTilesX;
	}

	public int getNbTilesY() {
		return nbTilesY;
	}
}
