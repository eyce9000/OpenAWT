package org.openawt.svg;

import org.openawt.geom.Ellipse2D;
import org.openawt.geom.Ellipse2D.Double;
import org.openawt.svg.transforms.Transform;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="ellipse")
public class SVGEllipse implements SVGShape<Ellipse2D>{
	
	private Style style;
	private Ellipse2D ellipse;
	private Transform transform;

	public SVGEllipse(
			@Attribute(name="cx") double cx, 
			@Attribute(name="cy") double cy, 
			@Attribute(name="rx") double rx, 
			@Attribute(name="ry") double ry){
		this((float)cx,(float)cy,(float)rx,(float)ry);
	}
	
	public SVGEllipse(float cx, float cy, float rx, float ry){
		this.ellipse = new Ellipse2D.Double(cx-rx, cy-ry, rx*2, ry*2);
	}
	
	public SVGEllipse(Ellipse2D ellipse){
		this.ellipse = ellipse;
	}
	
	@Attribute(name="cx")
	public double getCenterX(){
		return ellipse.getCenterX();
	}

	@Attribute(name="cy")
	public double getCenterY(){
		return ellipse.getCenterY();
	} 
	
	@Attribute(name="rx")
	public double getRadiusX(){
		return ellipse.getWidth() /2;
	}

	@Attribute(name="ry")
	public double getRadiusY(){
		return ellipse.getHeight() /2;
	} 

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
	public Ellipse2D getShape() {
		return ellipse;
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
