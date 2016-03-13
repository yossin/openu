package mmn12;

/**
 * @author yos
 *
 */
public class LightBulb {

	private RGBColor _color;
	private boolean _switchedOn;
	/**
	 * @param red
	 * @param green
	 * @param blue
	 * initialize a switched off lamp with the given RGB colors 
	 */
	public LightBulb(int red, int green, int blue){
		if (RGBColor.isValid(red) && RGBColor.isValid(green) && RGBColor.isValid(blue)){
			_color = new RGBColor(red, green, blue);
		} else {
			_color = new RGBColor();
		}
		_switchedOn=false;
	}
	/**
	 * @param color
	 * initialize a switched off lamp with the given RGBColor
	 */
	public LightBulb(RGBColor color){
		_color=color;
		_switchedOn=false;
	}
	/**
	 * @param other
	 * copy constructor
	 */
	public LightBulb(LightBulb other){
		_color=other._color;
		_switchedOn=other._switchedOn;
	}
	
	public RGBColor getColor() {
		return _color;
	}
	public void setColor(RGBColor color) {
		_color = color;
	}
	public boolean isSwitchedOn() {
		return _switchedOn;
	}
	/**
	 * change the state of the light: on->off, off->on
	 */
	public void switchLight(){
		_switchedOn=!_switchedOn;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s %s", _color, _switchedOn?"On":"Off");
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LightBulb){
			return toString().equals(obj.toString());
		} else {
			return super.equals(obj);
		}
	}
}
