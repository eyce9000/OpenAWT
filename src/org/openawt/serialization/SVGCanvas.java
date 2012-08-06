package org.openawt.serialization;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.openawt.Color;
import org.openawt.Shape;
import org.openawt.Style;
import org.openawt.geom.Circle2D;
import org.openawt.geom.Ellipse2D;
import org.openawt.geom.Rectangle2D;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

@Root(name="svg")
@Namespace(reference="http://www.w3.org/2000/svg")
public class SVGCanvas {
	@Attribute
	private String width="100%";
	@Attribute
	private String height="100%";
	
	
	private HashMap<Class<? extends Shape>,List<? extends Shape>> shapesByClass;
	
	public SVGCanvas(){
		shapesByClass = new HashMap<Class<? extends Shape>,List<? extends Shape>>();
	}
	
	private SVGCanvas addShape(Class<? extends Shape> type, Shape shape){
		List<Shape> shapes;
		if(!shapesByClass.containsKey(type)){
			shapes = new ArrayList<Shape>();
			shapesByClass.put(type, shapes);
		}
		else
			shapes = (List<Shape>)shapesByClass.get(type);
		
		shapes.add(shape);
		return this;
	}
	
	private boolean removeShape(Class<? extends Shape> type, Shape shape){
		List<Shape> shapes;
		if(!shapesByClass.containsKey(type))
			shapes = new ArrayList<Shape>();
		else
			shapes = (List<Shape>)shapesByClass.get(type);
		
		return shapes.remove(shape);
	}
	
	private void setShapes(Class<? extends Shape> type, List<? extends Shape> shapes){
		shapesByClass.put(type, shapes);
	}
	
	private <T> List<T> getShapesByClass(Class<? extends T> type){
		return (List<T>)shapesByClass.get(type);
	}
	
	/*
	 * SERIALIZATION
	 */

	public static SVGCanvas deserailize(InputStream in) throws Exception{
		Persister persister = buildXMLSerializer();
		return persister.read(SVGCanvas.class, in);
	}
	public static SVGCanvas deserailize(File in) throws Exception{
		Persister persister = buildXMLSerializer();
		return persister.read(SVGCanvas.class, in);
	}
	
	public void serialize(OutputStream out) throws Exception{
		Persister persister = buildXMLSerializer();
		persister.write(this, out);
	}
	public void serialize(File out) throws Exception{
		Persister persister = buildXMLSerializer();
		persister.write(this, out);
	}
	
	private static Persister buildXMLSerializer(){
		RegisterMatcher matcher = new RegisterMatcher();
		matcher.register(Color.class, new ColorTransform());
		matcher.register(Style.class, new StyleTransform());
		Persister persister = new Persister(matcher);
		return persister;
	}
	
	
	/*
	 * RECTANGLES
	 */
	
	@ElementList(required=true, inline=true, entry="rect")
	public void setRectangles(List<Rectangle2D.Float> rectangles){
		setShapes(Rectangle2D.Float.class,rectangles);
	}
	
	@ElementList(required=true, inline=true, entry="rect")
	public List<Rectangle2D.Float> getRectangles(){
		return getShapesByClass(Rectangle2D.Float.class);
	}
	
	public SVGCanvas addRectangle(Rectangle2D.Float rect){
		return addShape(Rectangle2D.Float.class,rect);
	}
	
	public boolean removeRectangle(Rectangle2D.Float rect){
		return removeShape(Rectangle2D.Float.class,rect);
	}
	
	
	/*
	 * ELLIPSES
	 */
	

	@ElementList(required=false, inline=true, entry="ellipse")
	public void setEllipses(List<Ellipse2D.Float> ellipses){
		setShapes(Ellipse2D.Float.class,ellipses);
	}
	
	@ElementList(required=false, inline=true, entry="ellipse")
	public List<Ellipse2D.Float> getEllipses(){
		return getShapesByClass(Ellipse2D.Float.class);
	}
	
	public SVGCanvas addEllipse(Ellipse2D.Float ellipse){
		return addShape(Ellipse2D.Float.class,ellipse);
	}
	
	public boolean removeEllipse(Ellipse2D.Float ellipse){
		return removeShape(Ellipse2D.Float.class,ellipse);
	}
	

	/*
	 * CIRCLES
	 */
	

	@ElementList(required=false, inline=true, entry="circle")
	public void setCircles(List<Circle2D.Float> circles){
		setShapes(Circle2D.Float.class,circles);
	}
	
	@ElementList(required=false, inline=true, entry="circle")
	public List<Circle2D.Float> getCircles(){
		return getShapesByClass(Circle2D.Float.class);
	}
	
	public SVGCanvas addCircle(Circle2D.Float circle){
		return addShape(Circle2D.Float.class,circle);
	}
	
	public boolean removeCircle(Circle2D.Float circle){
		return removeShape(Circle2D.Float.class,circle);
	}
}
