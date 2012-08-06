package org.openawt.geom;

import org.simpleframework.xml.Attribute;

public abstract class Circle2D extends Ellipse2D {
	public static class Float extends Circle2D{
		public float x;
		public float y;
		private float width;
		private float height;
		
		@Override
		public Rectangle2D getBounds2D() {
			return new Rectangle2D.Float(x,y,width,width);
		}

		@Override
		public double getX() {
			return (double) x;
		}
		
		@Override
		public double getY() {
			return (double) y;
		}

		@Override
		public double getWidth() {
			return width;
		}

		@Override
		public double getHeight() {
			return width;
		}

		@Override
		public boolean isEmpty() {
            return (width <= 0.0 || height <= 0.0);
		}

		@Override
		public void setFrame(double x, double y, double w, double h) {
            this.x = (float)x;
            this.y = (float)y;
            this.width = (float)w;
            this.height = (float)h;
			
		}
		
		public Float(float x, float y, float width){
			setFrame(x,y,width,width);
		}

		@Override
		public double getRadiusX() {
			return width/2;
		}

		@Override
		public double getRadiusY() {
			return height/2;
		}
		
		public double getRadius(){
			return getRadiusX();
		}
	}
	public static class Double extends Circle2D{
		public double x;
		public double y;
		private double width;
		private double height;
		
		@Override
		public Rectangle2D getBounds2D() {
			return new Rectangle2D.Double(x,y,width,width);
		}

		@Override
		public double getX() {
			return (double) x;
		}
		
		@Override
		public double getY() {
			return (double) y;
		}

		@Override
		public double getWidth() {
			return width;
		}

		@Override
		public double getHeight() {
			return width;
		}

		@Override
		public boolean isEmpty() {
            return (width <= 0.0 || height <= 0.0);
		}

		@Override
		public void setFrame(double x, double y, double w, double h) {
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
			
		}
		
		public Double(double x, double y, double width){
			setFrame(x,y,width,width);
		}

		@Override
		public double getRadiusX() {
			return width/2;
		}

		@Override
		public double getRadiusY() {
			return height/2;
		}
		
		public double getRadius(){
			return getRadiusX();
		}
	}
	@Attribute(name="r")
	public double getRadius(){
		return getRadiusX();
	}
}
