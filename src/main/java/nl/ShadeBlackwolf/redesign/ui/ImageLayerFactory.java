package nl.ShadeBlackwolf.redesign.ui;

import static java.awt.Color.black;

public class ImageLayerFactory {
	private static ImageLayer defaultBackground = new ImageLayer(getHeight(), getWidth(), black);
	private static ImageLayer background = new ImageLayer(getHeight(), getWidth());
	private static ImageLayer fieldTiles = new ImageLayer(getHeight(), getWidth());
	private static ImageLayer objectTiles = new ImageLayer(getHeight(), getWidth());
	private static ImageLayer player = new ImageLayer(getHeight(), getWidth());
	private static ImageLayer foregroundTiles = new ImageLayer(getHeight(), getWidth());
	private static ImageLayer foreground = new ImageLayer(getHeight(), getWidth());
	private static ImageLayer textBackground = new ImageLayer(getHeight(), getWidth());
	private static ImageLayer text = new ImageLayer(getHeight(), getWidth());

	public static int getHeight() {
		return 600;
	}

	public static int getWidth() {
		return 800;
	}

	public static ImageLayer getImageLayer(Layer layer) {
		switch(layer){
		case DEFAULT_BACKGROUND: return defaultBackground;
		case BACKGROUND: return background;
		case FIELD_TILES: return fieldTiles;
		case OBJECT_TILES: return objectTiles;
		case PLAYER: return player;
		case FOREGROUND_TILES: return foregroundTiles;
		case FOREGROUND: return foreground;
		case TEXT_BACKGROUND: return textBackground;
		case TEXT: return text;
			default: throw new NoSuchLayer(layer.toString());
		}
	}

	public static class NoSuchLayer extends RuntimeException{
		public NoSuchLayer(String layerName) {
			super(layerName);
		}
	}
	public static enum Layer{
		DEFAULT_BACKGROUND, BACKGROUND, FIELD_TILES, OBJECT_TILES, PLAYER, 
		FOREGROUND_TILES, FOREGROUND, TEXT_BACKGROUND, TEXT
	}
}
