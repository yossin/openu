package mmn13;

import mmn12.RGBColor;

/**
 * @author yos
 *
 */
public class RGBImage {

	private RGBColor[][] pixels;
	public RGBImage(int rows, int cols){
		pixels = new RGBColor[rows][cols];
		for (int i=0;i<rows; i++){
			for (int j=0;j<cols; j++){
				pixels[i][j]=new RGBColor();
			}
		}
	}
	public RGBImage(RGBColor[][] pixels){
		int rows=pixels.length;
		int cols=pixels[0].length;
		this.pixels = new RGBColor[rows][cols];
		for (int i=0;i<rows; i++){
			for (int j=0;j<cols; j++){
				this.pixels[i][j]=new RGBColor(pixels[i][j]);
			}
		}
	}
	public RGBImage(RGBImage other){
		this(other.pixels);
	}
	
	public int getWidth(){
		return pixels[0].length;
	}
	public int getHeight(){
		return pixels.length;
	}
	private boolean isValidPoint(int row, int col){
		return (row>-1 && row<getHeight())
				&& 
				(col>-1 && col<getWidth());
	}
	public RGBColor getPixel (int row, int col){
		if (isValidPoint(row, col)){
			return pixels[row][col];
		} else{
			return new RGBColor();
		}
	}
	public void setPixel (int row, int col, RGBColor pixel){
		if (isValidPoint(row, col)){
			pixels[row][col]=pixel;
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RGBImage){
			RGBImage image = (RGBImage)obj;
			if (getHeight()!=image.getHeight()){
				return false;
			}
			if (getWidth()!=image.getWidth()){
				return false;
			}
			for (int i=0;i<getHeight(); i++){
				for (int j=0;j<getWidth(); j++){
					if (!pixels[i][j].equals(image.pixels[i][j])){
						return false;
					}
				}
			}
			return true;
		} else{
			return super.equals(obj);
		}
	}
	
}