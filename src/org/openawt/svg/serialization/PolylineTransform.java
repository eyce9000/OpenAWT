package org.openawt.svg.serialization;

import org.openawt.geom.Polyline2D;
import org.simpleframework.xml.transform.Transform;

public class PolylineTransform implements Transform<Polyline2D>{

	@Override
	public Polyline2D read(String in) throws Exception {
		float[] xPoints;
		float[] yPoints;
		
		String[] points = in.trim().split("\\s");
		
		xPoints = new float[points.length];
		yPoints = new float[points.length];
		
		for(int i=0; i<points.length; i++){
			String point = points[i];
			
			String[] parts = point.split(",");
			if(parts.length == 2){
				float x = Float.parseFloat(parts[0]);
				float y = Float.parseFloat(parts[1]);
				xPoints[i] = x;
				yPoints[i] = y;
			}
		}
		
		return new Polyline2D(xPoints,yPoints,yPoints.length);
	}

	@Override
	public String write(Polyline2D poly) throws Exception {
		float[] xPoints = poly.xpoints;
		float[] yPoints = poly.ypoints;
		int nPoints = poly.npoints;
		
		String value = "";
		
		for(int i=0; i<nPoints; i++){
			value += xPoints[i]+","+yPoints[i];
			if(i<nPoints-1){
				value += " ";
			}
		}
		return value;
	}

}
