package org.openawt;

public class ArrayHelper {
	public static <U> U[] copyOf(U[] original, int newLength){
		U[] copy = (U[]) new Object[newLength];
		System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
		return copy;
	}
	public static byte[] copyOf(byte[] original,int newLength){
        byte[] copy = new byte[newLength];
        System.arraycopy(original, 0, copy, 0,
                         Math.min(original.length, newLength));
        return copy;
	}
	public static int[] copyOf(int[] original,int newLength){
        int[] copy = new int[newLength];
        System.arraycopy(original, 0, copy, 0,
                         Math.min(original.length, newLength));
        return copy;
	}
	public static float[] copyOf(float[] original,int newLength){
        float[] copy = new float[newLength];
        System.arraycopy(original, 0, copy, 0,
                         Math.min(original.length, newLength));
        return copy;
	}
	public static double[] copyOf(double[] original,int newLength){
        double[] copy = new double[newLength];
        System.arraycopy(original, 0, copy, 0,
                         Math.min(original.length, newLength));
        return copy;
	}
}
