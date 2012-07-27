package org.openawt;


public class Color {
	private int hex;
	
	public static final Color RED = new Color(0xff0000);
	public static final Color WHITE = new Color(0xffffff);
	public static final Color BLACK = new Color(0x000000);
	public static final Color BLUE = new Color(0x0000ff);
	public static final Color GRAY = new Color(0xcccccc);
	
	public final boolean hasAlpha;
	
	public Color(int rgb){
		this(rgb,false);
	}
	public Color(int rgba, boolean hasAlpha){
		this.hex = rgba;
		this.hasAlpha = hasAlpha;
	}
	public Color(android.graphics.Color c){
		this(c.hashCode());
	}
	public Color(int red, int green, int blue){
		this.hex = red<<32 & green<<16 & blue;
		this.hasAlpha = false;
	}
	public Color(int red, int green, int blue, int alpha){
		this.hex = alpha<<48 & red<<32 & green<<16 & blue;
		this.hasAlpha = true;
	}
	public int getRed(){
		return hex & 0x00ff0000;
	}
	public int getGreen(){
		return hex & 0x0000ff00;
	}
	public int getBlue(){
		return hex & 0x000000ff;
	}
	public int getAlpha(){
		return hex & 0xff000000;
	}
	public int getRGB(){
		return hex & 0x00ffffff;
	}
	public int getRGBA(){
		return hex;
	}
	public String toString(){
		return String.format("#%02x%02x%02x",getRed(),getBlue(),getGreen());
	}
	public static Color parse(String value){
		int red = Integer.parseInt(value.substring(1, 3));
		int green = Integer.parseInt(value.substring(3, 5));
		int blue = Integer.parseInt(value.substring(5, 7));
		return new Color(red,green,blue);
		
	}
}
