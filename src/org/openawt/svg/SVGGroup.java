package org.openawt.svg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openawt.Rectangle;
import org.openawt.Shape;
import org.openawt.geom.AffineTransform;
import org.openawt.geom.GeneralPath;
import org.openawt.geom.PathIterator;
import org.openawt.geom.Point2D;
import org.openawt.geom.Rectangle2D;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

@Root(name="g")
public class SVGGroup extends SVGShapeCollection implements SVGShape<Shape>{
	private Style style;


	@Override
	@Attribute(required=false)
	public Style getStyle() {
		return style;
	}

	@Override
	@Attribute(required=false)
	public void setStyle(Style style) {
		this.style = style;
	}

	@Override
	public Shape getShape() {
		GeneralPath path = new GeneralPath();
		for(SVGShape shape:this){
			path.append(shape.getShape(), false);
		}
		return path;
	}
	
}
