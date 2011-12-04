package org.openawt.draw.android;

import org.openawt.Shape;
import org.openawt.geom.*;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;

public class ShapePainter {
	public static void draw(Canvas c, Shape s, Paint paint){
		if(s!=null){
			PathIterator pi = s.getPathIterator(null);
			Path path = getPath(pi);
			paint.setStyle(Style.STROKE);
			c.drawPath(path, paint);
		}
	}
	public static void fill(Canvas c, Shape s, Paint paint){
		PathIterator pi = s.getPathIterator(null);
		Path path = getPath(pi);
		switch(pi.getWindingRule()){
		case PathIterator.WIND_EVEN_ODD:
			path.setFillType(FillType.EVEN_ODD);
			break;
		case PathIterator.WIND_NON_ZERO:
			path.setFillType(FillType.WINDING);
			break;
		}
		c.drawPath(path, paint);
	}

	public static Path getPath(PathIterator pi){
		Path path = new Path();
		for(;!pi.isDone();pi.next()){
			float[] coords = new float[6];
			switch (pi.currentSegment(coords)){
			case PathIterator.SEG_CLOSE:
				path.close();
				break;
			case PathIterator.SEG_CUBICTO:
				path.cubicTo(coords[0], coords[1], coords[2], coords[3], coords[4], coords[5]);
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
