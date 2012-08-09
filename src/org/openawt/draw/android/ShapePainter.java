package org.openawt.draw.android;

import org.openawt.BasicStroke;
import org.openawt.Shape;
import org.openawt.geom.*;
import org.openawt.svg.SVGShape;
import org.openawt.svg.Style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Path;
import android.graphics.Path.FillType;

public class ShapePainter {
	public static void draw(Canvas c, SVGShape s, Paint paint){
		Style style = s.getStyle();
		if(style!=null){
			org.openawt.Color fillColor = style.getStroke();
			if(fillColor!=null){
				paint.setColor(fillColor.getARGB());
				fill(c,s.getShape(),paint);
			}

			org.openawt.Color strokeColor = style.getStroke();
			Float strokeWidth = style.getStrokeWidth();
			if(strokeColor!=null)
				paint.setColor(strokeColor.getARGB());
			if(strokeWidth != null)
				paint.setStrokeWidth(strokeWidth);

			if(strokeColor!=null || strokeWidth!=null){
				draw(c, s.getShape(), paint);
			}
		}
		else
			draw(c,s.getShape(),paint);
	}
	public static void draw(Canvas c, Shape s, BasicStroke strokeStyle, Paint paint){
		if(strokeStyle!=null)
			applyStrokeStyle(strokeStyle,paint);
		draw(c,s,paint);
	}
	public static void draw(Canvas c, Shape s, Paint paint){
		if(s!=null){
			PathIterator pi = s.getPathIterator(null);
			Path path = getPath(pi);
			paint.setStyle(Paint.Style.STROKE);
			c.drawPath(path, paint);
		}
	}
	public static void fill(Canvas c, Shape s, BasicStroke strokeStyle,Paint paint){
		if(strokeStyle!=null)
			applyStrokeStyle(strokeStyle,paint);
		fill(c,s,paint);
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
	public static void applyStrokeStyle(BasicStroke strokeStyle, Paint paint){
		switch(strokeStyle.getEndCap()){
		case BasicStroke.CAP_BUTT:
			paint.setStrokeCap(Cap.BUTT);
			break;
		case BasicStroke.CAP_ROUND:
			paint.setStrokeCap(Cap.ROUND);
			break;
		case BasicStroke.CAP_SQUARE:
			paint.setStrokeCap(Cap.SQUARE);
		}

		switch(strokeStyle.getLineJoin()){
		case BasicStroke.JOIN_BEVEL:
			paint.setStrokeJoin(Join.BEVEL);
			break;
		case BasicStroke.JOIN_MITER:
			paint.setStrokeJoin(Join.MITER);
			paint.setStrokeMiter(strokeStyle.getMiterLimit());
			break;
		case BasicStroke.JOIN_ROUND:
			paint.setStrokeJoin(Join.ROUND);
			break;
		}

		paint.setStrokeWidth(strokeStyle.getLineWidth());
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
