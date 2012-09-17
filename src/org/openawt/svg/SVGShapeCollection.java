package org.openawt.svg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;

public abstract class SVGShapeCollection implements Iterable<SVGShape>{

	@ElementListUnion({
		@ElementList(entry="g",inline=true,type=SVGGroup.class,required=false),
		@ElementList(entry="circle",inline=true,type=SVGCircle.class,required=false),
		@ElementList(entry="rect",inline=true,type=SVGRectangle.class,required=false),
		@ElementList(entry="ellipse",inline=true,type=SVGEllipse.class,required=false),
		@ElementList(entry="line",inline=true,type=SVGLine.class,required=false),
		@ElementList(entry="polyline",inline=true,type=SVGPolyline.class,required=false),
		@ElementList(entry="polygon",inline=true,type=SVGPolygon.class,required=false),
		@ElementList(entry="path",inline=true,type=SVGPath.class,required=false)
	})
	
	protected List<SVGShape> shapes;
	
	
	public SVGShapeCollection(){
		shapes = new ArrayList<SVGShape>();
	}
	
	public SVGShapeCollection addShape(SVGShape shape){
		shapes.add(shape);
		return this;
	}
	
	public boolean removeShape( SVGShape shape){
		return shapes.remove(shape);
	}

	@Override
	public Iterator<SVGShape> iterator() {
		return shapes.iterator();
	}
}
