package org.openawt.svg;

import org.openawt.Shape;
import org.openawt.svg.transforms.Transform;
import org.simpleframework.xml.Attribute;

public interface SVGShape<T extends Shape>{

	@Attribute(required=false)
    public Style getStyle();

	@Attribute(required=false)
    public void setStyle(Style style);
	
	@Attribute(required=false)
	public void setTransform(Transform transform);
	
	@Attribute(required=false)
	public Transform getTransform();
	
	public T getShape();
}
