package org.openawt.svg;

import org.openawt.Shape;
import org.openawt.svg.transforms.Transform;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class SVGText implements SVGShape{
	@Attribute
	private float x,y;
	@Text
	private String body;
	private Style style;
	private Transform transform;
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
	public Shape getShape() {
		return null;
	}
	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
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
