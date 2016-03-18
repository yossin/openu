

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author yos
 *
 */
public class RGBImageTests {
	private RGBImage image;
	
	private static RGBColor createColor(int pixel){
		return new RGBColor(pixel, pixel, pixel);
	}
	
	private static RGBImage createImage(int[][] pixels){
		int height = pixels.length;
		int width = pixels[0].length;
		RGBColor colors[][] = new RGBColor[height][width];
		for (int i=0; i<height; i++){
			for (int j=0; j<width; j++){
				colors[i][j]=createColor(pixels[i][j]);
			}
		}
		return new RGBImage(colors);
	}
	
	@Before
	public void before(){
		image =createImage(new int[][]{
			{100,101,102},
			{200,201,202}
		});
	}
	
	@Test
	public void testFlipHorizontal() {
		image.flipHorizontal();
		RGBImage expected = createImage(new int[][]{
			{102,101,100},
			{202,201,200}
		});
		assertEquals(expected, image);		
	}
	@Test
	public void testFlipVertical() {
		image.flipVertical();
		RGBImage expected = createImage(new int[][]{
			{200,201,202},
			{100,101,102},
		});
		assertEquals(expected, image);		
	}
	@Test
	public void testGetHeight() {
		assertEquals(2, image.getHeight());		
	}
	@Test
	public void testGetWidth() {
		assertEquals(3, image.getWidth());
	}
	@Test
	public void testGetPixel() {
		assertEquals(new RGBColor(201,201,201), image.getPixel(1, 1));
	}
	@Test
	public void testInvertColors() {
		image.invertColors();
		RGBImage expected = createImage(new int[][]{
			{255-100,255-101,255-102},
			{255-200,255-201,255-202}
		});
		assertEquals(expected, image);		
	}
	@Test
	public void testRotateClockwise() {
		image.rotateClockwise();
		RGBImage expected = createImage(new int[][]{
			{200,100},
			{201,101},
			{202,102},
		});
		assertEquals(expected, image);		
	}
	@Test
	public void testRotateCounterClockwise() {
		image.rotateCounterClockwise();
		RGBImage expected = createImage(new int[][]{
			{102,202},
			{101,201},
			{100,200},
		});
		assertEquals(expected, image);		
	}
	@Test
	public void testSetPixel() {
		image.setPixel(1, 1, new RGBColor(1,1,1));
		assertEquals(new RGBColor(1,1,1), image.getPixel(1, 1));
	}
	@Test
	public void testShiftRight() {
		image.shiftCol(1);
		RGBImage expected = createImage(new int[][]{
			{0,100,101},
			{0,200,201}
		});
		assertEquals(expected, image);		
	}
	@Test
	public void testShiftLeft() {
		image.shiftCol(-1);
		RGBImage expected = createImage(new int[][]{
			{101,102,0},
			{201,202,0}
		});
		assertEquals(expected, image);		
	}
	@Test
	public void testShiftDown() {
		image.shiftRow(1);
		RGBImage expected = createImage(new int[][]{
			{0,0,0},
			{100, 101,102}
		});
		assertEquals(expected, image);		
	}
	@Test
	public void testShiftUp() {
		image.shiftRow(-1);
		RGBImage expected = createImage(new int[][]{
			{200,201,202},
			{0,0,0}
		});
		assertEquals(expected, image);		
	}
	@Test
	public void testToGrayscaleArray() {
		double[][] grayscale = new double[][]{
			{createColor(100).convertToGrayscale(),
			 createColor(101).convertToGrayscale(),
			 createColor(102).convertToGrayscale()},
			{createColor(200).convertToGrayscale(),
			 createColor(201).convertToGrayscale(),
			 createColor(202).convertToGrayscale()},
		};
		double[][]imageGrayscale = image.toGrayscaleArray();
		for (int i=0;i<grayscale.length;i++){
			assertArrayEquals(grayscale[i], imageGrayscale[i], 0);
		}
		
	}
	@Test
	public void testToRGBColorArray() {
		RGBColor[][] rgb = image.toRGBColorArray();
		assertEquals(image, new RGBImage(rgb));
	}

}