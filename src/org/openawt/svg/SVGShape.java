package org.openawt.svg;

import org.openawt.Shape;
import org.simpleframework.xml.Attribute;

public interface SVGShape<T extends Shape>{

	@Attribute(required=false)
    public Style getStyle();

	@Attribute(required=false)
    public void setStyle(Style style);
	
	public T getShape();
}
