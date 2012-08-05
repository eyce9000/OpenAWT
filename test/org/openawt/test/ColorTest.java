package org.openawt.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openawt.Color;

public class ColorTest {
	
	Color white,blue,red,maroon,aWhite,aBlue,aRed;
	
	@Before
	public void setUp() throws Exception {
		white = Color.WHITE;
		blue = Color.BLUE;
		red = Color.RED;
		aWhite = new Color(255,255,255,128);
		aBlue = new Color(0,0,255,128);
		aRed = new Color(255,0,0,128);
		maroon = new Color(108,20,32);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToRGBHexString() {
		assertTrue("Wrong RGB Hex String:"+white.toRGBHexString(),white.toRGBHexString().equals("#ffffff"));
		assertTrue("Wrong RGB Hex String:"+red.toRGBHexString(),red.toRGBHexString().equals("#ff0000"));
		assertTrue("Wrong RGB Hex String:"+blue.toRGBHexString(),blue.toRGBHexString().equals("#0000ff"));
	}

	@Test
	public void testToARGBHexString() {
		assertTrue("Wrong ARGB Hex String:"+white.toARGBHexString(),white.toARGBHexString().equals("#ffffffff"));
		assertTrue("Wrong ARGB Hex String:"+aWhite.toARGBHexString(),aWhite.toARGBHexString().equals("#80ffffff"));
	}

	@Test
	public void testToRGBString() {
		assertTrue("Wrong RGB String:"+white.toRGBString(),white.toRGBString().equals("rgb(255,255,255)"));
		assertTrue("Wrong RGB String:"+red.toRGBString(),red.toRGBString().equals("rgb(255,0,0)"));
		assertTrue("Wrong RGB String:"+blue.toRGBString(),blue.toRGBString().equals("rgb(0,0,255)"));
	}

	@Test
	public void testToRGBAString() {
		assertTrue("Wrong RGBA String:"+aWhite.toRGBAString(),aWhite.toRGBAString().equals("rgba(255,255,255,128)"));
		assertTrue("Wrong RGBA String:"+aRed.toRGBAString(),aRed.toRGBAString().equals("rgba(255,0,0,128)"));
		assertTrue("Wrong RGBA String:"+aBlue.toRGBAString(),aBlue.toRGBAString().equals("rgba(0,0,255,128)"));
	}

	@Test
	public void testParse() {
		assertTrue(maroon.equals(Color.parse("#6C1420")));
		assertTrue(maroon.equals(Color.parse("rgb(108,20,32)")));
		assertTrue(maroon.equals(Color.parse("rgba(108,20,32,255)")));
		
		assertTrue(blue.equals(Color.parse("#00f")));
		assertTrue(aBlue.equals(Color.parse("rgba(0,0,255,128)")));
	}

	@Test
	public void testFromRGBA() {
		assertTrue(aBlue.equals(Color.fromARGB(0x800000ff)));
	}

	@Test
	public void testFromRGB() {
		assertTrue(blue.equals(Color.fromRGB(0x0000ff)));
		assertTrue(maroon.equals(Color.fromRGB(0x6C1420)));
	}

}
