package org.openawt.svg;

import org.openawt.geom.Line2D;
import org.openawt.svg.transforms.Transform;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@SuppressWarnings("serial")
@Root(name="line")
public class SVGLine implements SVGShape<Line2D>{
	private Line2D line;
	private Style style;
	private Transform transform;
	
	public SVGLine(
			@Attribute(name="x1") double x1,
			@Attribute(name="y1") double y1,
			@Attribute(name="x2") double x2,
			@Attribute(name="y2") double y2){
		line = new Line2D.Double((float)x1,(float)y1,(float)x2,(float)y2);
	}
	
	public SVGLine(Line2D line){
		this.line = line;
	}

	
	@Attribute(name="x1")
	public double getX1(){
		return line.getX1();
	}
	
	@Attribute(name="y1")
	public double getY1(){
		return line.getY1();
	}

	@Attribute(name="x2")
	public double getX2(){
		return line.getX2();
	}

	@Attribute(name="y2")
	public double getY2(){
		return line.getY2();
	}
	
	@Override
	public Style getStyle() {
		return this.style;
	}

	@Override
	public void setStyle(Style style) {
		this.style = style;
	}

	@Override
	public Line2D getShape() {
		return line;
	}

	@Override
	@Attribute(required = false)
	public void setTransform(Transform transform) {
		this.transform = transform;
	}

	@Override
	@Attribute(required = false)
	public Transform getTransform() {
		return this.transform;
	}
}
