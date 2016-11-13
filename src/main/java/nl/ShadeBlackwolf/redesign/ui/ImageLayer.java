package nl.ShadeBlackwolf.redesign.ui;

import java.awt.Color;
import java.util.Arrays;

public class ImageLayer {
	Color[][] pixels;
	
	public ImageLayer(int height, int width){
		pixels = new Color[height][width];
		for(int i = 0; i < pixels.length; i++){
			Arrays.fill(pixels[i], null);
		}
	}
	
	public ImageLayer(int height, int width, Color color){
		this(height, width);
		floodColor(color);
	}
	

	public ImageLayer(ImageLayer imageLayer) {
		this.pixels = imageLayer.pixels.clone();
	}

	public void floodColor(Color color){
		for(int i = 0; i < pixels.length; i++){
			Arrays.fill(pixels[i], color);
		}
	}
	
	public ImageLayer deepClone(){
		return new ImageLayer(this);
	}

	public void stackOn(ImageLayer upperLayer) {
		stackOn(upperLayer.pixels);
	}

	public void stackOn(Color[][] image) {
		stackOn(image, 0, 0);
	}
	
	public void stackOn(Color[][] image, int bottomLeftY, int bottomLeftX){
		for(int y= bottomLeftY; y-bottomLeftY<image.length; y++){
			for(int x = bottomLeftX; x-bottomLeftX < image[y-bottomLeftY].length; x++){
				if (image[y-bottomLeftY][x-bottomLeftX] != null){
					pixels[y][x] = image[y-bottomLeftY][x-bottomLeftX];
				}
			}
		}
	}

	public Color[][] getPixels() {
		return pixels;
	}

	public void setPixels(Color[][] pixels) {
		this.pixels = pixels;
	}
}
