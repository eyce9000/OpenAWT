package org.openawt.svg;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openawt.Color;
import org.openawt.geom.Path2D;
import org.openawt.geom.PathIterator;
import org.openawt.geom.Polyline2D;
import org.openawt.svg.serialization.ColorTransform;
import org.openawt.svg.serialization.PathIteratorTransform;
import org.openawt.svg.serialization.PolylineTransform;
import org.openawt.svg.serialization.RegisterMatcher;
import org.openawt.svg.serialization.StyleTransform;
import org.openawt.svg.serialization.TransformTransform;
import org.openawt.svg.transforms.Compound;
import org.openawt.svg.transforms.Rotate;
import org.openawt.svg.transforms.Scale;
import org.openawt.svg.transforms.Transform;
import org.openawt.svg.transforms.Translate;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Persister;

@Root(name="svg")
@Namespace(reference="http://www.w3.org/2000/svg")
public class SVGCanvas extends SVGShapeCollection{
	@Attribute
	private String width="100%";
	@Attribute
	private String height="100%";
	
	public SVGCanvas(){
		super();
	}
	public SVGCanvas(String width,String height){
		super();
		this.width = width;
		this.height = height;
	}

	/*
	 * SERIALIZATION
	 */
	public static SVGCanvas deserialize(InputStream in) throws Exception{
		Persister persister = new Persister(buildXMLTypeMatcher());
		return persister.read(SVGCanvas.class, in);
	}
	
	public static SVGCanvas deserialize(File in) throws Exception{
		Persister persister = new Persister(buildXMLTypeMatcher());
		return persister.read(SVGCanvas.class, in);
	}
	
	public void serialize(OutputStream out) throws Exception{
		Persister persister = new Persister(buildXMLTypeMatcher());
		persister.write(this, out);
	}
	
	public void serialize(File out) throws Exception{
		Persister persister = new Persister(buildXMLTypeMatcher());
		persister.write(this, out);
	}
	
	public static RegisterMatcher buildXMLTypeMatcher(){
		RegisterMatcher matcher = new RegisterMatcher();
		matcher.register(Color.class, new ColorTransform());
		matcher.register(Style.class, new StyleTransform());
		matcher.register(Polyline2D.class, new PolylineTransform());
		matcher.register(PathIterator.class, new PathIteratorTransform());
		matcher.register(Transform.class, new TransformTransform());
		matcher.register(Compound.class, new TransformTransform());
		matcher.register(Rotate.class, new TransformTransform());
		matcher.register(Translate.class, new TransformTransform());
		matcher.register(Scale.class, new TransformTransform());
		return matcher;
	}
	
}
