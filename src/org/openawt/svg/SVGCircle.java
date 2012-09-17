package org.openawt.svg;

import org.openawt.geom.Ellipse2D;
import org.openawt.svg.transforms.Transform;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;


@Root(name="circle")
public class SVGCircle implements SVGShape<Ellipse2D>{
	private Ellipse2D ellipse;
	private Style style;
	private Transform transform;
	
	public SVGCircle(
			@Attribute(name="cx") double cx, 
			@Attribute(name="cy") double cy,
			@Attribute(name="r") double r){
		this((float)cx,(float)cy,(float)r);
	}
	
	public SVGCircle(float cx,float cy, float r){
		ellipse = new Ellipse2D.Double(cx-r, cy-r, r*2, r*2);
	}
	
	public SVGCircle(Ellipse2D ellipse){
		
	}

	@Attribute(name="cx")
	public double getCenterX(){
		return ellipse.getCenterX();
	}

	@Attribute(name="cy")
	public double getCenterY(){
		return ellipse.getCenterY();
	} 
	
	@Attribute(name="r")
	public double getRadius(){
		return ellipse.getWidth() /2;
	}

	@Override
	@Attribute(required = false)
	public Style getStyle() {
		return style;
	}

	@Override
	@Attribute(required = false)
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
