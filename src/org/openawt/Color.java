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
	 * Creates a new color with alpha value of 255 (opaque)
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
	 * <p>
	 * Returns the color as a hexadecimal encoded integer.
	 * </p>
	 * <p>
	 * The encoded integer will be in this format:
	 * 
	 * <blockquote><tt>
	 * 0xrrggbb</tt>
	 * </blockquote>
	 * </p>
	 * <p>Some examples:
	 * <ul>
	 * <li>white : <code>0xffffff</code></li>
	 * <li>red : <code>0xff0000</code></li>
	 * <li>blue : <code>0x0000ff</code></li>
	 * <li>green : <code>0x00ff00</code></li>
	 * <li>maroon : <code>0x6C1420</code></li>
	 * </ul>
	 * </p>
	 * @return the hexadecimal encoded integer
	 */
	public int getRGB(){
		return red<<16 & green<<8 & blue;
	}

	/**
	 * <p>Returns the color as a hexadecimal encoded integer including alpha.
	 * </p
	 * <p>
	 * The encoded integer will be in this format:
	 * 
	 * <blockquote><tt>
	 * 0xaarrggbb
	 * </tt></blockquote>
	 * </p>
	 * <p>
	 * Some examples:
	 * <ul>
	 * <li>half opaque white : <code>0x80ffffff</code></li>
	 * <li>half opaque red : <code>0x80ff0000</code></li>
	 * <li>quarter opaque blue : <code>0x400000ff</code></li>
	 * <li>quarter opaque green : <code>0x4000ff00</code></li>
	 * <li>quarter opaque maroon : <code>0x406C1420</code></li>
	 * </ul>
	 * </p>
	 * @return the hexadecimal encoded integer
	 */
	public int getARGB(){
		return alpha<<24 & red<<16 & green<<8 & blue;
	}
	
	/**
	 * <p>Returns a string representation of this color. 
	 * If the color is opaque the RGB representation is used, otherwise the RGBA representation is used.</p>
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
	 * <p>Returns a string representation of this color formatted as a standard RGB hexadecimal string.</p>
	 * <p>Examples:
	 * <ul>
	 * <li>white : <code>#ffffff</code></li>
	 * <li>red : <code>#ff0000</code></li>
	 * <li>blue : <code>#0000ff</code></li>
	 * <li>green : <code>#00ff00</code></li>
	 * <li>maroon : <code>#6C1420</code></li>
	 * </ul>
	 * <p/>
	 * @return the formatted string representation
	 */
	public String toRGBHexString(){
		return String.format("#%02x%02x%02x",getRed(),getGreen(),getBlue());
	}
	/**
	 * <p>Returns a string representation of this color formatted as an ARGB hexadecimal string.</p>
	 * <p>Examples:
	 * <ul>
	 * <li>white : <code>#ffffff</code></li>
	 * <li>red : <code>#ff0000</code></li>
	 * <li>blue : <code>#0000ff</code></li>
	 * <li>green : <code>#00ff00</code></li>
	 * <li>maroon : <code>#6C1420</code></li>
	 * </ul>
	 * </p>
	 * @return the formatted string representation
	 */
	public String toARGBHexString(){
		return String.format("#%02x%02x%02x%02x",getAlpha(),getRed(),getGreen(),getBlue());
	}
	/**
	 * <p>Returns a string representation of the color in rgb format.</p>
	 * <p>Examples
	 * <ul>
	 * <li>white : <code>rgb(255,255,255)</code></li>
	 * <li>red : <code>rgb(255,0,0)</code></li>
	 * <li>blue : <code>rgb(0,0,255)</code></li>
	 * <li>green : <code>rgb(0,255,0)</code></li>
	 * <li>maroon : <code>rgb(108,20,32)</code></li>
	 * </ul>
	 * </p>
	 * @return the formatted string representation
	 */
	public String toRGBString(){
		return "rgb("+red+","+green+","+blue+")";
	}
	/**
	 * <p>Returns a string representation of the color in rgba format.</p>
	 * <p>Examples
	 * <ul>
	 * <li>half opaque white : <code>rgba(255,255,255,128)</code></li>
	 * <li>half opaque red : <code>rgba(255,0,0,128)</code></li>
	 * <li>quarter opaque blue : <code>rgba(0,0,255,64)</code></li>
	 * <li>quarter opaque green : <code>rgba(0,255,0,64)</code></li>
	 * <li>quarter opaque maroon : <code>rgba(108,20,32,64)</code></li>
	 * </ul>
	 * <p/>
	 * @return the formatted string representation
	 */
	public String toRGBAString(){
		return "rgba("+red+","+green+","+blue+","+alpha+")";
	}
	
	/**
	 * <p>Parses a string representation of a color and returns the resulting <code>Color</code>.</p>
	 * <p>
	 * Strings can be in one of the following formats:
	 * </p>
	 * <ul>
	 * <li><p><b>Short Hexadecimal</b><br/><code>Color.parse("#cf0")</code></p></li>
	 * <li><p><b>Full Hexadecimal</b><br/><code>Color.parse("#6C1420")</code></p></li>
	 * <p>Note that when parsed, the short hexadecimal is expanded such that
	 * the following two hexadecimal statements are treated equivalently:<br/>
	 * <code>Color.parse("#cf0")</code><br/>
	 * <code>Color.parse("#ccff00")</code>
	 * </p>
	 * <li><p><b>Integer RGB</b><br /><code>Color.parse("rgb(108,20,32)")</code></p></li>
	 * <li><p><b>Integer RGBA</b><br /><code>Color.parse("rgba(108,20,32,64)")</code></p></li>
	 * </ul>
	 * @param value a formatted string representation of a color
	 * @return the parsed color
	 */
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
		else if(value.equals("none")){
			return new Color(0,0,0,0);
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
	
	
	
	public static final Color RED = fromRGB(0xff0000);
	public static final Color WHITE = fromRGB(0xffffff);
	public static final Color BLACK = fromRGB(0x000000);
	public static final Color BLUE = fromRGB(0x0000ff);
	public static final Color GREEN = fromRGB(0x00ff00);
	public static final Color GRAY = fromRGB(0xcccccc);
	public static final Color NONE = new Color(0,0,0,0);

}
