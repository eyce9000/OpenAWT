package org.openawt.svg;

import org.openawt.Shape;
import org.openawt.geom.Ellipse2D;
import org.openawt.geom.Line2D;
import org.openawt.geom.Path2D;
import org.openawt.geom.Polygon2D;
import org.openawt.geom.Polyline2D;
import org.openawt.geom.RectangularShape;

public class SVGBuilder {
	public static SVGShape buildSVGShape(Shape shape){
		SVGShape svgShape;
		if(shape instanceof Ellipse2D){
			Ellipse2D ellipse = (Ellipse2D)shape;
			if(Math.abs(ellipse.getHeight()-ellipse.getWidth())<.00001){
				return new SVGCircle(ellipse.getCenterX(),ellipse.getCenterY(),ellipse.getWidth()/2);
			}
			return new SVGEllipse(ellipse);
		}
		else if(shape instanceof RectangularShape){
			return new SVGRectangle((RectangularShape) shape);
		}
		else if(shape instanceof Polygon2D){
			return new SVGPolygon((Polygon2D) shape);
		}
		else if(shape instanceof Polyline2D){
			return new SVGPolyline((Polyline2D) shape);
		}
		else if(shape instanceof Line2D){
			return new SVGLine((Line2D) shape);
		}
		else if(shape instanceof Path2D){
			return new SVGPath((Path2D)shape);
		}
		else{
			return new SVGPath(shape.getPathIterator(null));
		}
	}
}
