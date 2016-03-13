package mmn12;

/**
 * @author yos
 *
 */
public class RGBColor {
	private int _red=0;
	private int _green=0;
	private int _blue=0;

	/**
	 * @param value
	 * @return True if value is between 0-255
	 */
	private static boolean isValid(int value){
		return value>=0 && value<=255;
	}
	/**
	 * @param c1
	 * @param c2
	 * @return (c1+c2)/2
	 */
	private static int average(int c1, int c2){
		return (c1+c2)/2;
	}
	/**
	 * @param value
	 * @return 255-value
	 */
	private static int invert(int value){
		return 255-value;
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public RGBColor(int red, int green, int blue){
		setRed(red);
		setGreen(green);
		setBlue(blue);
	}
	
	/**
	 * default constructor
	 */
	public RGBColor(){
		this(0,0,0);
	}
	
	/**
	 * copy constructor
	 * @param other
	 */
	public RGBColor(RGBColor other){
		this(other._red,other._green,other._blue);
	}
	
	public int getRed() {
		return _red;
	}

	public void setRed(int red) {
		if (isValid(red)){
			this._red=red;
		}
	}

	public int getGreen() {
		return _green;
	}

	public void setGreen(int green) {
		if (isValid(green)){
			this._green=green;
		}
	}

	public int getBlue() {
		return _blue;
	}

	public void setBlue(int blue) {
		if (isValid(blue)){
			this._blue=blue;
		}
	}
	
	@Override
	public String toString() {
		return String.format("(%d,%d,%d)", _red, _green, _blue);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RGBColor){
			return obj.toString().equals(toString());
		} else {
			return super.equals(obj);
		}
	}
	
	/**
	 * @param other
	 * mix color with the other color by setting the average RGB of both
	 */
	public void mix(RGBColor other){
		_red=average(_red, other._red);
		_green=average(_green, other._green);
		_blue=average(_blue, other._blue);
	}
	
	/**
	 * @return gray scale: red x 30% + green x 59% + blue x 11% 
	 */
	public double convertToGrayscale(){
		return 0.3*_red + 0.59*_green + 0.11*_blue;
	}
	
	/**
	 * invert (using private method for each RGB)
	 */
	public void invert(){
		_red=invert(_red);
		_green=invert(_green);
		_blue=invert(_blue);
	}

}