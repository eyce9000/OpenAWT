package org.openawt.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openawt.Color;
import org.openawt.svg.SVGCanvas;
import org.openawt.svg.SVGCircle;
import org.openawt.svg.SVGEllipse;
import org.openawt.svg.SVGGroup;
import org.openawt.svg.SVGLine;
import org.openawt.svg.SVGPath;
import org.openawt.svg.SVGPolygon;
import org.openawt.svg.SVGPolyline;
import org.openawt.svg.SVGRectangle;
import org.openawt.svg.Style;

public class SVGTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		SVGCanvas svg = new SVGCanvas();
		SVGRectangle rect1 = new SVGRectangle(10,10,50,50,5,5);
		SVGCircle circle1 = new SVGCircle(rect1.getShape().getCenterX(),rect1.getShape().getCenterY(), 14);
		SVGEllipse ellipse1 = new SVGEllipse(100, 100, 50,75);
		SVGLine line1 = new SVGLine(0,0,200,300);
		SVGPolyline polyline1 = new SVGPolyline(new float[]{10.0f,20.0f,100.0f},new float[]{0.0f,50.0f,25.0f},3);
		SVGPolygon polygon1 = new SVGPolygon(new float[]{20.0f,50.0f,150.0f},new float[]{0.0f,50.0f,25.0f},3);
		SVGPath path1 = new SVGPath(circle1.getShape().getPathIterator(null));

		rect1.setStyle(new Style().setStroke(Color.GREEN).setFill(Color.BLUE).setStrokeWidth(4f));
		circle1.setStyle(new Style().setStroke(Color.RED).setStrokeWidth(2f));
		ellipse1.setStyle(new Style().setFill(Color.GRAY));
		line1.setStyle(new Style().setStroke(Color.BLUE).setStrokeWidth(10f));
		polyline1.setStyle(new Style().setStroke(Color.GREEN).setFill(new Color(0,0,0,0)));
		path1.setStyle(new Style().setStrokeWidth(3f).setStroke(Color.BLACK).setFill(new Color(0,0,0,0)));
		
		SVGGroup group = new SVGGroup();
		
		group.addShape(path1);
		svg.addShape(group);
		svg.serialize(out);
		svg.serialize(new File("test.svg"));
		System.out.println(out.toString());
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		SVGCanvas svg2 = SVGCanvas.deserailize(in);
		assertTrue(svg2!=null);

	}

}
