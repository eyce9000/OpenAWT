package org.openawt.svg.serialization;

import org.openawt.Color;
import org.simpleframework.xml.transform.Transform;

public class ColorTransform implements Transform<Color>{


	@Override
	public Color read(String value) throws Exception {
		value = value.replaceAll("\\s", "");
		return Color.parse(value);
	}

	@Override
	public String write(Color color) throws Exception {
		int red,green,blue,alpha;
		red = color.getRed();
		green = color.getGreen();
		blue = color.getBlue();
		alpha = color.getAlpha();
		if(alpha==255){
			return "rgb("+red+","+green+","+blue+")";
		}
		else{
			return "rgba("+red+","+green+","+blue+","+alpha+")";
		}
	}
}
