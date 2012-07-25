package org.openawt.draw.awt;

import java.awt.Graphics2D;

import org.openawt.Color;
import org.openawt.Shape;
import org.openawt.geom.PathIterator;


public class ShapePainter {
	public static void draw(Graphics2D g2d, Shape s){
		if(s!=null){
			PathIterator pi = s.getPathIterator(null);
			java.awt.geom.GeneralPath path = toAwtPath(pi);
			g2d.draw(path);
		}
	}
	public static void fill(Graphics2D g2d, Shape s){
		if(s!=null){
			PathIterator pi = s.getPathIterator(null);
			java.awt.geom.GeneralPath path = toAwtPath(pi);
			g2d.fill(path);
		}
	}
	public static void setColor(Graphics2D g2d, Color color){
		g2d.setColor(new java.awt.Color(color.hex,color.hasAlpha));
	}
	public static java.awt.geom.GeneralPath toAwtPath(PathIterator pi){
		java.awt.geom.GeneralPath path = new java.awt.geom.GeneralPath();
		path.setWindingRule(pi.getWindingRule());
		for(;!pi.isDone();pi.next()){
			float[] coords = new float[6];
			switch (pi.currentSegment(coords)){
			case PathIterator.SEG_CLOSE:
				path.closePath();
				break;
			case PathIterator.SEG_CUBICTO:
				path.curveTo(coords[0], coords[1], coords[2], coords[3], coords[4], coords[5]);
				break;
			case PathIterator.SEG_LINETO:
				path.lineTo(coords[0], coords[1]);
				break;
			case PathIterator.SEG_MOVETO:
				path.moveTo(coords[0], coords[1]);
				break;
			case PathIterator.SEG_QUADTO:
				path.quadTo(coords[0], coords[1], coords[2], coords[3]);
				break;
			}
		}
		return path;
	}
}
