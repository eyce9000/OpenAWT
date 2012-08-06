package org.openawt.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openawt.Color;
import org.openawt.Style;
import org.openawt.geom.Circle2D;
import org.openawt.geom.Rectangle2D;
import org.openawt.serialization.SVGCanvas;
import org.openawt.serialization.StyleTransform;

public class SVGTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		SVGCanvas svg = new SVGCanvas();
		Rectangle2D.Float rect1 = new Rectangle2D.Float(10,10,50,50);
		Circle2D.Float circle1 = new Circle2D.Float(30,	 30, 14);
		
		rect1.setStyle(new Style().setStroke(Color.GREEN).setFill(Color.BLUE).setStrokeWidth(4f));
		circle1.setStyle(new Style().setStroke(Color.RED).setStrokeWidth(2f));
		
		svg.addRectangle(rect1).addCircle(circle1);
		try {
			svg.serialize(out);
			svg.serialize(new File("test.svg"));
			System.out.println(out.toString());
			ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
			SVGCanvas svg2 = SVGCanvas.deserailize(in);
			List<Rectangle2D.Float> rects = svg2.getRectangles();
			assertTrue(svg2!=null);
			assertTrue(rects.size() > 0);
			assertTrue(rects.get(0).getStyle() != null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
