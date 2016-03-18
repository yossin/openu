

/**
 * @author yos
 *
 */
public class RGBImage {

	private RGBColor[][] pixels;
	/**
	 * @param rows
	 * @param cols
	 * create a black image by size rows x cols
	 */
	public RGBImage(int rows, int cols){
		pixels = new RGBColor[rows][cols];
		for (int i=0;i<rows; i++){
			for (int j=0;j<cols; j++){
				pixels[i][j]=new RGBColor();
			}
		}
	}
	/**
	 * @param pixels
	 * create RGB image from RGB colors
	 */
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
	/**
	 * @param other
	 * copy constructor
	 */
	public RGBImage(RGBImage other){
		this(other.pixels);
	}
	
	/**
	 * @return width
	 */
	public int getWidth(){
		return pixels[0].length;
	}
	/**
	 * @return height
	 */
	public int getHeight(){
		return pixels.length;
	}
	/**
	 * @param row
	 * @param col
	 * @return true if point is in rows x cols range
	 */
	private boolean isValidPoint(int row, int col){
		return (row>-1 && row<getHeight())
				&& 
				(col>-1 && col<getWidth());
	}
	/**
	 * @param row
	 * @param col
	 * @return pixel if it is in a valid range, otherwise return a black pixel
	 */
	public RGBColor getPixel (int row, int col){
		if (isValidPoint(row, col)){
			return pixels[row][col];
		} else{
			return new RGBColor();
		}
	}
	/**
	 * @param row
	 * @param col
	 * @param pixel
	 * set pixel if it is in a valid range, otherwise do nothing
	 */
	public void setPixel (int row, int col, RGBColor pixel){
		if (isValidPoint(row, col)){
			pixels[row][col]=pixel;
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
	
	/**
	 * first col becomes last col, etc..
	 */
	public void flipHorizontal(){
		int mWidth = getWidth()/2; // Ex: even: <0 1|2 4>, odd <0 | 1 | 2>  
		for (int i=0; i<getHeight(); i++){
			for (int j1=0, j2=getWidth()-1; j1<mWidth; j1++, j2--){
				RGBColor left = pixels[i][j1];
				RGBColor right = pixels[i][j2];
				pixels[i][j1]=right;
				pixels[i][j2]=left;
			}
		}
	}

	
	/**
	 * first row becomes last row, etc..
	 */
	public void flipVertical(){
		int mHeight = getHeight()/2; // Ex: even: <0 1|2 4>, odd <0 | 1 | 2>  
		for (int i1=0, i2=getHeight()-1; i1<mHeight; i1++, i2--){
			for (int j=0;j<getWidth(); j++){
				RGBColor upper = pixels[i1][j];
				RGBColor lower = pixels[i2][j];
				pixels[i1][j]=lower;
				pixels[i2][j]=upper;
			}
		}
	}

	/**
	 * invert each pixel's colors (invert: 255-x - for RGB colors)
	 */
	public void invertColors(){
		for (int i=0;i<getHeight(); i++){
			for (int j=0;j<getWidth(); j++){
				pixels[i][j].invert();
			}
		}
	}

	
	
	/**
	 * rotate clockwise: ex:2x3 becomes 3x2
	 * 001 002 003          111 001
	 * 111 112 113    -->   112 002
	 *                      113 003
	 */
	public void rotateClockwise(){
		RGBColor rotated[][] = new RGBColor[getWidth()][getHeight()];
		for (int i1=0, i2=getHeight()-1;i1<getHeight(); i1++, i2--){
			for (int j=0;j<getWidth(); j++){
				rotated[j][i2] = pixels[i1][j];
			}
		}
		pixels=rotated;
	}

	
	/**
	 * rotate counter clockwise: ex:2x3 becomes 3x2
	 * 001 002 003          003 113
	 * 111 112 113    -->   002 112
	 *                      001 111
	 */
	public void rotateCounterClockwise(){
		RGBColor rotated[][] = new RGBColor[getWidth()][getHeight()];
		for (int i=0; i<getHeight(); i++){
			for (int j1=0, j2=getWidth()-1;j1<getWidth(); j1++, j2--){
				rotated[j2][i] = pixels[i][j1];
			}
		}
		pixels=rotated;
	}
	
	
	/**
	 * @author yos
	 * container for returning shift resolve response
	 */
	private static class Shift{
		/**
		 * index to start the shift from
		 */
		final int shiftStart;
		/**
		 * upper index to do the shift (last index +1)
		 */
		int shiftEnd;
		/**
		 * index to start black image from
		 */
		int blackStart;
		/**
		 * upper index to store the black pixels (last index +1)
		 */
		int blackEnd;

		/**
		 * @param shiftStart
		 * @param shiftEnd
		 * @param blackStart
		 * @param blackEnd
		 */
		public Shift(int shiftStart, int shiftEnd, int blackStart, int blackEnd) {
			this.shiftStart = shiftStart;
			this.shiftEnd = shiftEnd;
			this.blackStart = blackStart;
			this.blackEnd = blackEnd;
		}
	}
	
	/**
	 * @param offset
	 * @param n
	 * @return shift resolve response
	 */
	private static Shift resolveShift(int offset, int n){
		// height/width 4, offset 1:  shiftStart 1, shiftEnd 4 (<end)
		// height/width 4, offset -1: shiftStart 0, shiftEnd 3 (<end)
		if (Math.abs(offset)<=n && offset!=0){
			int shiftStart, shiftEnd, blackStart, blackEnd;
			if (offset>0){
				shiftStart=offset; shiftEnd=n;
				blackStart=0; blackEnd=shiftStart;
			} else {
				shiftStart=0; shiftEnd=n+offset;
				blackStart=shiftEnd; blackEnd=n;
			}
			return new Shift(shiftStart, shiftEnd, blackStart, blackEnd);
		}else {
			return null;
		}
	}
	
	/**
	 * shift cols by offset
	 * shift 1 results
	 * 001 002 003          000 001 002
	 * 111 112 113    -->   000 111 112
	 * @param offset if offset>0 shift left, if offset<0 shift right
	 */
	public void shiftCol(int offset){
		Shift shift = resolveShift(offset, getWidth());
		if (shift != null){
			RGBColor shifted[][] = new RGBColor[getHeight()][getWidth()];
			for (int i=0; i<getHeight(); i++){
				for (int j=shift.shiftStart; j<shift.shiftEnd; j++){
					shifted[i][j] = pixels[i][j-offset];
				}
				for (int j=shift.blackStart; j<shift.blackEnd; j++){
					shifted[i][j] = new RGBColor();
				}
			}
			pixels=shifted;
		}
	}

	
	/**
	 * shift rows by offset
	 * shift 1 results
	 * 001 002 003          000 000 000
	 * 111 112 113    -->   001 002 003
	 * @param offset if offset>0 shift down, if offset<0 shift up
	 */
	public void shiftRow(int offset){
		Shift shift = resolveShift(offset, getHeight());
		if (shift != null){
			RGBColor shifted[][] = new RGBColor[getHeight()][getWidth()];
			for (int i=shift.shiftStart; i<shift.shiftEnd; i++){
				shifted[i] = pixels[i-offset];
			}
			for (int i=shift.blackStart; i<shift.blackEnd; i++){
				for (int j=0; j<getWidth(); j++){
					shifted[i][j] = new RGBColor();
				}
			}
			pixels=shifted;
		}
	}
	
	
	/**
	 * @return gray scale array
	 */
	public double[][] toGrayscaleArray(){
		double[][] grayscale = new double[getHeight()][getWidth()];
		for (int i=0; i<getHeight(); i++){
			for (int j=0; j<getWidth(); j++){
				grayscale[i][j]=pixels[i][j].convertToGrayscale();
			}
		}
		return grayscale;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<getHeight();i++){
			StringBuilder line = new StringBuilder();
			for (int j=0; j<getWidth();j++){
				line.append(pixels[i][j]).append(" ");
			}
			builder.append(line.toString().trim()).append("\n");
		}
		return builder.toString();
	}
	
	/**
	 * @return a copy of pixels member
	 */
	public RGBColor[][] toRGBColorArray(){
		RGBColor[][] cloned = new RGBColor[getHeight()][getWidth()];
		for (int i=0; i<getHeight(); i++){
			for (int j=0; j<getWidth(); j++){
				cloned[i][j] = new RGBColor(pixels[i][j]); 
			}
		}
		return cloned;
	}

}