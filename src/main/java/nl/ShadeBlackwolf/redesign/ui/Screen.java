package nl.ShadeBlackwolf.redesign.ui;

import java.awt.Color;

import static nl.ShadeBlackwolf.redesign.ui.ImageLayerFactory.Layer.*;
public class Screen {

	ImageLayer defaultBackground = ImageLayerFactory.getImageLayer(DEFAULT_BACKGROUND);
	ImageLayer background = ImageLayerFactory.getImageLayer(BACKGROUND);
	ImageLayer fieldTiles = ImageLayerFactory.getImageLayer(FIELD_TILES);
	ImageLayer objectTiles = ImageLayerFactory.getImageLayer(OBJECT_TILES);
	ImageLayer player = ImageLayerFactory.getImageLayer(PLAYER);
	ImageLayer foregroundTiles = ImageLayerFactory.getImageLayer(FOREGROUND_TILES);
	ImageLayer foreground = ImageLayerFactory.getImageLayer(FOREGROUND);
	ImageLayer textBackground = ImageLayerFactory.getImageLayer(TEXT_BACKGROUND);
	ImageLayer text = ImageLayerFactory.getImageLayer(TEXT);

	public Color[][] getImage() {
		ImageLayer image = defaultBackground.deepClone();
		image.stackOn(background);
		image.stackOn(fieldTiles);
		image.stackOn(objectTiles);
		image.stackOn(player);
		image.stackOn(foregroundTiles);
		image.stackOn(foreground);
		image.stackOn(textBackground);
		image.stackOn(text);
		return image.getPixels();
	}
}
