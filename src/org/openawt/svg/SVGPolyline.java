package org.openawt.svg;

import java.util.List;

import org.openawt.geom.Line2D;
import org.openawt.geom.Point2D;
import org.openawt.geom.Polygon2D;
import org.openawt.geom.Polyline2D;
import org.openawt.svg.transforms.Transform;
import org.simpleframework.xml.Attribute;

public class SVGPolyline implements SVGShape<Polyline2D>{
	
	private Style style;
	private Polyline2D poly;
	private Transform transform;
	
	public SVGPolyline(float[] xpoints, float[] ypoints, int npoints){
		poly = new Polyline2D(xpoints,ypoints,npoints);
	}
	
	public SVGPolyline(@Attribute(name="points")Polyline2D poly){
		this.poly = poly;
	}
	
	public SVGPolyline(List<Point2D> points){
		float[] xpts = new float[points.size()], ypts = new float[points.size()];
		for(int i=0; i<points.size(); i++){
			xpts[i] = (float)points.get(i).getX();
			ypts[i] = (float)points.get(i).getY();
		}
		poly = new Polyline2D(xpts,ypts,points.size());
	}
	
	@Override
	@Attribute(required = false)
	public Style getStyle() {
		return this.style;
	}

	@Override
	@Attribute(required = false)
	public void setStyle(Style style) {
		this.style = style;
	}

	@Override
	@Attribute(name="points")
	public Polyline2D getShape() {
		return poly;
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
