package org.openawt.svg;

import org.openawt.geom.RectangularShape;

import org.openawt.geom.Rectangle2D;
import org.openawt.svg.transforms.Transform;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="rect")
public class SVGRectangle implements SVGShape<Rectangle2D>{
	private Style style;
	private Rectangle2D rect;
	
	@Attribute(required=false)
	private Double rx = null, ry = null;
	private Transform transform;
	
	public SVGRectangle(
			@Attribute(name="x") double x, 
			@Attribute(name="y") double y,  
			@Attribute(name="width") double width,  
			@Attribute(name="height") double height){
		rect = new Rectangle2D.Double(x,y,width,height);
	}
	
	public SVGRectangle(RectangularShape shape){
		this(shape.getX(),shape.getY(),shape.getWidth(),shape.getHeight());
	}
	public SVGRectangle(Rectangle2D rect){
		this.rect = rect;
	}
	
	public SVGRectangle(double x, double y, double width, double height, double roundingX, double roundingY){
		this(x,y,width,height);
		this.rx = roundingX;
		this.ry = roundingY;
	}
	
	
	@Attribute(name="x")
	public double getX(){
		return (double)rect.getX();
	}
	
	@Attribute(name="y")
	public double getY(){
		return (double)rect.getY();
	}

	@Attribute(name="width")
	public double getWidth(){
		return (double)rect.getWidth();
	}

	@Attribute(name="height")
	public double getHeight(){
		return (double)rect.getHeight();
	}
	
	@Override
	@Attribute
	public Style getStyle() {
		return style;
	}

	@Override
	@Attribute
	public void setStyle(Style style) {
		this.style = style;
	}

	@Override
	public Rectangle2D getShape() {
		return rect;
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
