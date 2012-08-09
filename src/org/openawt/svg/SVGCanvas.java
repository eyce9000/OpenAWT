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
		matcher.register(Polyline2D.class, new PolylineTransform());
		matcher.register(PathIterator.class, new PathIteratorTransform());
		Persister persister = new Persister(matcher);
		return persister;
	}
}
