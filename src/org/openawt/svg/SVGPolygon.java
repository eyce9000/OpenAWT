package org.openawt.svg;

import java.util.List;

import org.openawt.geom.Point2D;
import org.openawt.geom.Polygon2D;
import org.openawt.geom.Polyline2D;
import org.openawt.svg.transforms.Transform;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="polygon")
public class SVGPolygon implements SVGShape<Polygon2D>{
	private Polygon2D polygon;
	private Style style;
	private Transform transform;
	
	public SVGPolygon(@Attribute(name="points")Polyline2D polyline){
		polygon = new Polygon2D(polyline.xpoints,polyline.ypoints,polyline.npoints);
	}
	public SVGPolygon(Polygon2D polygon){
		this.polygon = polygon;
	}
	public SVGPolygon(float[] xpoints, float[] ypoints, int npoints){
		polygon = new Polygon2D(xpoints,ypoints,npoints);
	}
	public SVGPolygon(List<Point2D> points){
		float[] xpts = new float[points.size()], ypts = new float[points.size()];
		for(int i=0; i<points.size(); i++){
			xpts[i] = (float)points.get(i).getX();
			ypts[i] = (float)points.get(i).getY();
		}
		polygon = new Polygon2D(xpts,ypts,points.size());
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

	@Attribute(name="points")
	public Polyline2D getPolyline(){
		return polygon.getPolyline2D();
	}
	
	@Override
	public Polygon2D getShape() {
		return polygon;
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
