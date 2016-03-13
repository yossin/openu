package mmn12;

/**
 * @author yos
 *
 */
public class Disco {

	private LightBulb _bulb1;
	private LightBulb _bulb2;
	private LightBulb _bulb3;
	private LightBulb _bulb4;
	
	public Disco(LightBulb bulb1, LightBulb bulb2, LightBulb bulb3, LightBulb bulb4){
		_bulb1=bulb1;
		_bulb2=bulb2;
		_bulb3=bulb3;
		_bulb4=bulb4;
	}
	
	public LightBulb getFirstBulb() {
		return _bulb1;
	}
	public LightBulb getSecondBulb() {
		return _bulb2;
	}
	public LightBulb getThirdBulb() {
		return _bulb3;
	}
	public LightBulb getFourthBulb() {
		return _bulb4;
	}
	
	public void switchBulb(int num){
		switch(num){
		case 1:
			_bulb1.switchLight();
			break;
		case 2:
			_bulb2.switchLight();
			break;
		case 3:
			_bulb3.switchLight();
			break;
		case 4:
			_bulb4.switchLight();
			break;
		default:
			throw new IllegalArgumentException("there are only 4 bulbs to switch, please pass a valid number");			
		}
	}
	
	private static void turn(LightBulb bulb, boolean state){
		if (bulb.isSwitchedOn() != state){
			bulb.switchLight();
		}
	}
	public void turnAllOn(){
		turn(_bulb1, true);
		turn(_bulb2, true);
		turn(_bulb3, true);
		turn(_bulb4, true);
	}
	public void turnAllOff(){
		turn(_bulb1, false);
		turn(_bulb2, false);
		turn(_bulb3, false);
		turn(_bulb4, false);
	}
	public boolean areAllOn(){
		return (_bulb1.isSwitchedOn() 
				&& _bulb2.isSwitchedOn()
				&& _bulb3.isSwitchedOn()
				&& _bulb4.isSwitchedOn());
	}

	public boolean areAllOff(){
		return (!_bulb1.isSwitchedOn() 
				&& !_bulb2.isSwitchedOn()
				&& !_bulb3.isSwitchedOn()
				&& !_bulb4.isSwitchedOn());
	}

	public boolean allSameColor(){
		return (_bulb1.getColor().equals(_bulb2.getColor())
				&& _bulb3.getColor().equals(_bulb4.getColor())
				&& _bulb1.getColor().equals(_bulb4.getColor()));
	}
	
}
