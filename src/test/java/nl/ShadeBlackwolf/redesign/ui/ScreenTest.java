package nl.ShadeBlackwolf.redesign.ui;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;

import static java.awt.Color.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

import static nl.ShadeBlackwolf.redesign.ui.ImageLayerFactory.Layer.*;

@RunWith(HierarchicalContextRunner.class)
public class ScreenTest {

	private Screen screen;

	@Before
	public void setupScreen(){
		screen = new Screen();
	}
	
	private void assertColorEquals(Color[][] image, int y, int x, Color expected) {
		assertEquals("["+y+"]["+x+"]", expected, image[y][x]);
	}

	@Test
	public void screenHasDefaultBackground(){
		assertDefaultImageCorrect(screen.getImage());
	}
	
	private void assertDefaultImageCorrect(Color[][] image) {
		for(Color[] colors : image){
			for(Color color : colors){
				assertEquals(black, color);
			}
		}
	}
	
	public class BackgroundLayerContext{
		
		@Before
		public void setupBackground(){
			ImageLayerFactory.getImageLayer(BACKGROUND).stackOn(
					createBackground());
		}
		
		@Test
		public void setBackgroundToBlueLeft(){
			assertImageWithBackgroundCorrect(screen.getImage());
		}
	
		private void assertImageWithBackgroundCorrect(Color[][] image) {
			for(int y = 0; y<image.length; y++){
				for(int x = 0; x < image[y].length; x++){
					if(y<300){
						assertColorEquals(image, y, x, blue);
					} else {
						assertColorEquals(image, y, x, black);
					}
				}
			}
		}
		
		private Color[][] createBackground() {
			Color[][] background = new Color[300][800];
			for(int y = 0; y<background.length; y++){
				for(int x = 0; x < background[y].length; x++){
					background[y][x] = blue;
				}
			}
			return background;
		}
		
		public class FieldTestLayerContext{
			
			@Before
			public void setupFieldTiles(){
				ImageLayerFactory.getImageLayer(FIELD_TILES).stackOn(
						createFieldTileLayer());
			}
			
			@Test
			public void setTilesToRedBottom(){
				assertImageWithTilesCorrect(screen.getImage());
			}
			
			private void assertImageWithTilesCorrect(Color[][] image) {
				for(int y = 0; y<image.length; y++){
					for(int x = 0; x < image[y].length; x++){
						if(x<400){
							assertColorEquals(image, y, x, red);
						} else if(y<300){
							assertColorEquals(image, y, x, blue);
						} else {
							assertColorEquals(image, y, x, black);
						}
					}
				}
			}

			private Color[][] createFieldTileLayer() {
				Color[][] fieldTiles = new Color[600][400];
				for(int y = 0; y<fieldTiles.length; y++){
					for(int x = 0; x < fieldTiles[y].length; x++){
						fieldTiles[y][x] = red;
					}
				}
				return fieldTiles;
			}
			public class ObjectLayerTestContext{
				@Before
				public void setupObjectTileLayer(){
					ImageLayerFactory.getImageLayer(OBJECT_TILES).stackOn(
							createObjectTileLayer(), 100, 200);
				}
				
				@Test
				public void yellowTilesSetToCenter() throws IOException{
					assertImageWithObjectTilesCorrect(screen.getImage());
					
				}
				
				private void assertImageWithObjectTilesCorrect(Color[][] image) {
					for(int y = 0; y<image.length; y++){
						for(int x = 0; x < image[y].length; x++){
							if(x>=200&&x<600&&y>=100&&y<500){
								assertColorEquals(image, y, x, yellow);
							} else if(x<400){
								assertColorEquals(image, y, x, red);
							} else if(y<300){
								assertColorEquals(image, y, x, blue);
							} else {
								assertColorEquals(image, y, x, black);
							}
						}
					}
				}

				private Color[][] createObjectTileLayer() {
					Color[][] objectTiles = new Color[400][400];
					for(int y = 0; y<objectTiles.length; y++){
						for(int x = 0; x < objectTiles[y].length; x++){
							objectTiles[y][x] = yellow;
						}
					}
					return objectTiles;
				}
				
				public class playerLayerContext{
					@Before
					public void setupPlayerTileLayer(){
						ImageLayerFactory.getImageLayer(PLAYER).stackOn(
								createPlayerTileLayer());
					}

					@Test
					public void pinkTilesSetToCorner() throws IOException{
						assertImageWithPlayerCorrect(screen.getImage());
						
					}

					private void assertImageWithPlayerCorrect(Color[][] image) {
						for(int y = 0; y<image.length; y++){
							for(int x = 0; x < image[y].length; x++){
								if(x*3<y*4){
									assertColorEquals(image, y, x, pink);
								} else if(x>=200&&x<600&&y>=100&&y<500){
									assertColorEquals(image, y, x, yellow);
								} else if(x<400){
									assertColorEquals(image, y, x, red);
								} else if(y<300){
									assertColorEquals(image, y, x, blue);
								} else {
									assertColorEquals(image, y, x, black);
								}
							}
						}
					}

					private Color[][] createPlayerTileLayer() {
						Color[][] player = new Color[600][800];
						for(int y = 0; y<player.length; y++){
							for(int x = 0; x < player[y].length; x++){
								if(x*3<y*4){
									player[y][x] = pink;
								}
							}
						}
						return player;
					}
					
					public class ForegroundTilesLayerContext{
						@Before
						public void setupForegroundTileLayer(){
							ImageLayerFactory.getImageLayer(FOREGROUND_TILES).stackOn(
									createForegroundTileLayer());
						}

						@Test
						public void greenTilesSetToCorner() throws IOException{
							assertImageWithForegroundTilesCorrect(screen.getImage());
							
						}

						private void assertImageWithForegroundTilesCorrect(Color[][] image) {
							for(int y = 0; y<image.length; y++){
								for(int x = 0; x < image[y].length; x++){
									if(x*3+y*4>3600){
										assertColorEquals(image, y, x, green);
									} else if(x*3<y*4){
										assertColorEquals(image, y, x, pink);
									} else if(x>=200&&x<600&&y>=100&&y<500){
										assertColorEquals(image, y, x, yellow);
									} else if(x<400){
										assertColorEquals(image, y, x, red);
									} else if(y<300){
										assertColorEquals(image, y, x, blue);
									} else {
										assertColorEquals(image, y, x, black);
									}
								}
							}
						}

						private Color[][] createForegroundTileLayer() {
							Color[][] foregroundTiles = new Color[600][800];
							for(int y = 0; y<foregroundTiles.length; y++){
								for(int x = 0; x < foregroundTiles[y].length; x++){
									if(x*3+y*4>3600){
										foregroundTiles[y][x] = green;
									}
								}
							}
							return foregroundTiles;
						}
						
						public class ForegroundLayerContext{
							@Before
							public void setupForegroundLayer(){
								ImageLayerFactory.getImageLayer(FOREGROUND).stackOn(
										createForegroundLayer());
							}

							@Test
							public void whiteTilesSetToBottomStrip() throws IOException{
								assertImageWithForegroundCorrect(screen.getImage());
								
							}

							private void assertImageWithForegroundCorrect(Color[][] image) {
								for(int y = 0; y<image.length; y++){
									for(int x = 0; x < image[y].length; x++){
										if(y<50){
											assertColorEquals(image, y, x, white);
										} else if(x*3+y*4>3600){
											assertColorEquals(image, y, x, green);
										} else if(x*3<y*4){
											assertColorEquals(image, y, x, pink);
										} else if(x>=200&&x<600&&y>=100&&y<500){
											assertColorEquals(image, y, x, yellow);
										} else if(x<400){
											assertColorEquals(image, y, x, red);
										} else if(y<300){
											assertColorEquals(image, y, x, blue);
										} else {
											assertColorEquals(image, y, x, black);
										}
									}
								}
							}

							private Color[][] createForegroundLayer() {
								Color[][] foreground = new Color[600][800];
								for(int y = 0; y<50; y++){
									for(int x = 0; x < foreground[y].length; x++){
										foreground[y][x] = white;
									}
								}
								return foreground;
							}
							
							public class TextBackgroundContext{
								@Before
								public void setupTextBgLayer(){
									ImageLayerFactory.getImageLayer(TEXT_BACKGROUND).stackOn(
											createTextBackgroundLayer());
								}

								@Test
								public void grayTextBGSetToV() throws IOException{
									assertImageWithTextBgCorrect(screen.getImage());
									
								}

								private void assertImageWithTextBgCorrect(Color[][] image) {
									for(int y = 0; y<image.length; y++){
										for(int x = 0; x < image[y].length; x++){
											if(y*2+x*3<1200||(x-400)*3>y*2){
												assertColorEquals(image, y, x, gray);
											} else if(y<50){
												assertColorEquals(image, y, x, white);
											} else if(x*3+y*4>3600){
												assertColorEquals(image, y, x, green);
											} else if(x*3<y*4){
												assertColorEquals(image, y, x, pink);
											} else if(x>=200&&x<600&&y>=100&&y<500){
												assertColorEquals(image, y, x, yellow);
											} else if(x<400){
												assertColorEquals(image, y, x, red);
											} else if(y<300){
												assertColorEquals(image, y, x, blue);
											} else {
												assertColorEquals(image, y, x, black);
											}
										}
									}
								}

								private Color[][] createTextBackgroundLayer() {
									Color[][] textBackground = new Color[600][800];
									for(int y = 0; y<textBackground.length; y++){
										for(int x = 0; x < textBackground[y].length; x++){
											if(y*2+x*3<1200||(x-400)*3>y*2){
												textBackground[y][x] = gray;
											}
										}
									}
									return textBackground;
								}
								public class TextContext{
									@Before
									public void setupTextLayer(){
										ImageLayerFactory.getImageLayer(TEXT).stackOn(
												createTextLayer(), 25, 0);
									}

									@Test
									public void cyanTextBar() throws IOException{
										assertImageWithTextCorrect(screen.getImage());
										
									}

									private void assertImageWithTextCorrect(Color[][] image) {
										for(int y = 0; y<image.length; y++){
											for(int x = 0; x < image[y].length; x++){
												if(y>=25 && y<275){
													assertColorEquals(image, y, x, cyan);
												} else if(y*2+x*3<1200||(x-400)*3>y*2){
													assertColorEquals(image, y, x, gray);
												} else if(y<50){
													assertColorEquals(image, y, x, white);
												} else if(x*3+y*4>3600){
													assertColorEquals(image, y, x, green);
												} else if(x*3<y*4){
													assertColorEquals(image, y, x, pink);
												} else if(x>=200&&x<600&&y>=100&&y<500){
													assertColorEquals(image, y, x, yellow);
												} else if(x<400){
													assertColorEquals(image, y, x, red);
												} else if(y<300){
													assertColorEquals(image, y, x, blue);
												} else {
													assertColorEquals(image, y, x, black);
												}
											}
										}
									}

									private Color[][] createTextLayer() {
										Color[][] text = new Color[250][800];
										for(int y = 0; y<text.length; y++){
											for(int x = 0; x < text[y].length; x++){
													text[y][x] = cyan;
											}
										}
										return text;
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
