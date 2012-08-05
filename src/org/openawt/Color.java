package org.openawt;

import java.util.Formattable;
import java.util.Formatter;

/**
 * An 8 bit <a href="http://www.w3.org/TR/SVG/refs.html#ref-SRGB">SRGB</a> Color
 * @author George Lucchese
 *
 */
public class Color{	
	private int red = 0;
	private int green = 0;
	private int blue = 0;
	private int alpha = 255;
	private String value;
	
	/**
	 * Creates a new color with alpha value 255 (opaque)
	 * @param red an 8 bit value (0-255)
	 * @param green an 8 bit value (0-255)
	 * @param blue an 8 bit value (0-255)
	 */
	public Color(int red, int green, int blue){
		this(red,green,blue,255);
	}
	
	/**
	 * Creates a new color
	 * @param red an 8 bit value (0-255)
	 * @param green an 8 bit value (0-255)
	 * @param blue an 8 bit value (0-255)
	 * @param alpha an 8 bit value (0-255)
	 */
	public Color(int red, int green, int blue, int alpha){
		this.red = Math.max(Math.min(red, 255),0);
		this.green = Math.max(Math.min(green, 255),0);
		this.blue = Math.max(Math.min(blue, 255),0);
		this.alpha = Math.max(Math.min(alpha, 255),0);
		this.value = this.toString();
	}
	/**
	 * @return the 8-bit red component of this color
	 */
	public int getRed(){
		return red;
	}
	/**
	 * @return the 8-bit green component of this color
	 */
	public int getGreen(){
		return green;
	}
	/**
	 * @return the 8-bit blue component of this color
	 */
	public int getBlue(){
		return blue;
	}
	/**
	 * @return the 8-bit alpha component of this color
	 */
	public int getAlpha(){
		return alpha;
	}
	/**
	 * Indicates whether the color is opaque (alpha == 255)
	 * @return if the alpha value is 255 true, otherwise false
	 */
	public boolean isOpaque(){
		return alpha == 255;
	}
	/**
	 * Returns the color as a hexadecimal encoded integer with this format:<br/>
	 * 0x<b>&lt;red&gt;</b><b>&lt;green&gt;</b><b>&lt;blue&gt;</b><br/>
	 * e.g. <br/>
	 * <ul>
	 * <li>white : 0xffffff</li>
	 * <li>red : 0xff0000</li>
	 * <li>blue : 0x0000ff</li>
	 * <li>green : 0x00ff00</li>
	 * <li>maroon : 0x6C1420</li>
	 * </ul>
	 * @return the hexadecimal encoded integer
	 */
	public int getRGB(){
		return red<<16 & green<<8 & blue;
	}

	/**
	 * Returns the color as a hexadecimal encoded integer with this format:<br/>
	 * 0x<b>&lt;alpha&gt;</b><b>&lt;red&gt;</b><b>&lt;green&gt;</b><b>&lt;blue&gt;</b><br/>
	 * e.g. <br/>
	 * <ul>
	 * <li>half opaque white : 0x80ffffff</li>
	 * <li>half opaque red : 0x80ff0000</li>
	 * <li>quarter opaque blue : 0x400000ff</li>
	 * <li>quarter opaque green : 0x4000ff00</li>
	 * <li>quarter opaque maroon : 0x406C1420</li>
	 * </ul>
	 * @return the hexadecimal encoded integer
	 */
	public int getRGBA(){
		return alpha<<24 & red<<16 & green<<8 & blue;
	}
	
	/**
	 * Returns a string representation of this color. 
	 * If the color is opaque the RGB representation is used, otherwise the RGBA representation is used.
	 * @return the RGB or RGBA encoded string
	 * @see Color#toRGBString()
	 * @see Color#toRGBAString()
	 */
	@Override
	public String toString(){
		if(alpha==255)
			return toRGBString();
		else
			return toRGBAString();
			
	}
	/**
	 * Returns a string representation of this color formatted as a standard RGB hexadecimal string.<br/>
	 * e.g.<br/>
	 * <ul>
	 * <li>white : <b>#ffffff</b></li>
	 * <li>red : <b>#ff0000</b></li>
	 * <li>blue : <b>#0000ff</b></li>
	 * <li>green : <b>#00ff00</b></li>
	 * <li>maroon : <b>#6C1420</b></li>
	 * </ul>
	 * @return the formatted string representation
	 */
	public String toRGBHexString(){
		return String.format("#%02x%02x%02x",getRed(),getGreen(),getBlue());
	}
	/**
	 * Returns a string representation of this color formatted as an ARGB hexadecimal string.<br/>
	 * e.g.<br/>
	 * <ul>
	 * <li>white : <b>#ffffff</b></li>
	 * <li>red : <b>#ff0000</b></li>
	 * <li>blue : <b>#0000ff</b></li>
	 * <li>green : <b>#00ff00</b></li>
	 * <li>maroon : <b>#6C1420</b></li>
	 * </ul>
	 * @return the formatted string representation
	 */
	public String toARGBHexString(){
		return String.format("#%02x%02x%02x%02x",getAlpha(),getRed(),getGreen(),getBlue());
	}
	/**
	 * Returns a string representation of the color in rgb format.<br/>
	 * e.g.<br/>
	 * <ul>
	 * <li>white : <b>rgb(255,255,255)</b></li>
	 * <li>red : <b>rgb(255,0,0)</b></li>
	 * <li>blue : <b>rgb(0,0,255)</b></li>
	 * <li>green : <b>rgb(0,255,0)</b></li>
	 * <li>maroon : <b>rgb(108,20,32)</b></li>
	 * </ul>
	 * @return the formatted string representation
	 */
	public String toRGBString(){
		return "rgb("+red+","+green+","+blue+")";
	}
	/**
	 * Returns a string representation of the color in rgba format.<br/>
	 * e.g.<br/>
	 * <ul>
	 * <li>half opaque white : <b>rgba(255,255,255,128)</b></li>
	 * <li>half opaque red : <b>rgba(255,0,0,128)</b></li>
	 * <li>quarter opaque blue : <b>rgba(0,0,255,64)</b></li>
	 * <li>quarter opaque green : <b>rgba(0,255,0,64)</b></li>
	 * <li>quarter opaque maroon : <b>rgba(108,20,32,64)</b></li>
	 * </ul>
	 * @return the formatted string representation
	 */
	public String toRGBAString(){
		return "rgba("+red+","+green+","+blue+","+alpha+")";
	}
	public static Color parse(String value){
		int red,green,blue,alpha;
		value = value.replaceAll("\\s", "");
		if(value.startsWith("#")){
			//HEX Digit
			if(value.length() == 4){
				String newValue = "#";
				newValue += value.charAt(1)+""+value.charAt(1);
				newValue += value.charAt(2)+""+value.charAt(2);
				newValue += value.charAt(3)+""+value.charAt(3);
				value = newValue;
			}
			if(value.length() == 7){
				red = Integer.parseInt(value.substring(1, 3),16);
				green = Integer.parseInt(value.substring(3, 5),16);
				blue = Integer.parseInt(value.substring(5, 7),16);
				return new Color(red,green,blue);
			}
			else{
				throw new IllegalArgumentException("Value '"+value+"' is not a recognized hex value color.");
			}
		}
		else if(value.startsWith("rgb(")){
			value = value.replace("rgb", "").replace("(","").replace(")","");
			String[] parts = value.split(",");
			red = Integer.parseInt(parts[0]);
			green = Integer.parseInt(parts[1]);
			blue = Integer.parseInt(parts[2]);
			return new Color(red,green,blue);
		}
		else if(value.startsWith("rgba(")){
			value = value.replace("rgba", "").replace("(","").replace(")","");
			String[] parts = value.split(",");
			red = Integer.parseInt(parts[0]);
			green = Integer.parseInt(parts[1]);
			blue = Integer.parseInt(parts[2]);
			alpha = Integer.parseInt(parts[3]);
			return new Color(red,green,blue,alpha);
		}
		else{
			throw new IllegalArgumentException("Value '"+value+"' is not a recognized color.");
		}
	}
	
	public static Color fromARGB(int hex){
		return new Color(
				(int)((hex & 0x00ff0000l)>>16),
				(int)((hex & 0x0000ff00l)>>8),
				(int)((hex & 0x000000ffl)>>0),
				(int)((hex & 0xff000000l)>>24)
				);
	}
	public static Color fromRGB(int hex){
	return new Color(
			(int)((hex & 0x00ff0000l)>>16),
			(int)((hex & 0x0000ff00l)>>8),
			(int)((hex & 0x000000ffl)>>0)
			);
	}
	public static Color fromAndroidColor(android.graphics.Color color){
		return fromARGB(color.hashCode());
	}
	
	public boolean equals(Color color){
		return color.red==this.red && color.blue==this.blue && color.green == this.green && color.alpha == this.alpha;
	}
	
	
	
	public static final Color RED = fromARGB(0xffff0000);
	public static final Color WHITE = fromARGB(0xffffffff);
	public static final Color BLACK = fromARGB(0xff000000);
	public static final Color BLUE = fromARGB(0xff0000ff);
	public static final Color GRAY = fromARGB(0xffcccccc);

}
