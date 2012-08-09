package org.openawt.svg;

import org.openawt.Shape;
import org.openawt.geom.GeneralPath;
import org.openawt.geom.Path2D;
import org.openawt.geom.PathIterator;
import org.openawt.svg.serialization.PathIteratorTransform;
import org.simpleframework.xml.Attribute;

public class SVGPath implements SVGShape<Path2D>{
	private Path2D path;
	private Style style;
	public SVGPath(){
		path = new GeneralPath();
	}
	public SVGPath(Path2D path){
		this.path = path;
	}
	public SVGPath(@Attribute(name="d")String serializedFormat) throws Exception{
		this(new PathIteratorTransform().read(serializedFormat));
	}
	public SVGPath(PathIterator pi){
		path = new GeneralPath();
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
	}

	@Attribute(name="d")
	public String getSerializedPath() throws Exception{
		return new PathIteratorTransform().write(getShape());
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
	public Path2D getShape() {
		return path;
	}

}
