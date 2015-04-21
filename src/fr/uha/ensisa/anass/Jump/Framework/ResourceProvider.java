package fr.uha.ensisa.anass.Jump.Framework;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ResourceProvider {

	private Resources resources;
	
	public ResourceProvider(Resources rsc){
		this.resources = rsc;
	}
	
	public Bitmap getImage(int id){
		
		return BitmapFactory.decodeResource(this.resources, id);
		
	}
	
	public InputStream getFile(String filename) throws IOException{
		return resources.getAssets().open(filename);
	}
}
