

/**
 * @author yos
 *
 */
/**
 * @author yos
 *
 */
public class PolyNode {

	private int power; //חזקה
	private double coefficient; //מעריך
	private PolyNode next;
	
	/**
	 * create a new poly node
	 * if the power is negative set the power & the coefficient into 0
	 * @param power
	 * @param coefficient
	 */
	public PolyNode(int power, double coefficient){
		this(power, coefficient, null);
	}
	/**
	 * create a new poly node
	 * if the power is negative set the power & the coefficient into 0
	 * @param power
	 * @param coefficient
	 * @param next
	 */
	public PolyNode(int power, double coefficient, PolyNode next){
		setPower(power);
		setCoefficient(coefficient);
		setNext(next);
	}
	
	/**
	 * copy constructor
	 * @param other
	 */
	public PolyNode(PolyNode other){
		this(other.power, other.coefficient, other.next);
	}
	
	/**
	 * @return power
	 */
	public int getPower() {
		return power;
	}
	/**
	 * @return coefficient
	 */
	public double getCoefficient() {
		return coefficient;
	}
	/**
	 * @return next poly node
	 */
	public PolyNode getNext() {
		return next;
	}
	/**
	 * set power
	 * @param power - if the power is negative set the power & the coefficient into 0
	 */
	public void setPower(int power) {
		if (power<0){
			power=0;
			setCoefficient(0);
		} else {
			this.power = power;
		}
	}
	/**
	 * set coefficient
	 * @param coefficient
	 */
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	/**
	 * set nect poly
	 * @param next
	 */
	public void setNext(PolyNode next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (coefficient!=0){
			if (coefficient!=1){
				if (coefficient==-1){
					builder.append("-");
				} else {
					long lcoefficient=(long)coefficient;
					if (lcoefficient == coefficient){
						builder.append(lcoefficient);
					} else {
						builder.append(coefficient);
					}
				}
			}
			if (power==0 && (coefficient==1 || coefficient==-1)){
					builder.append(1);
			}else  if (power !=0){
				builder.append("x");
				if (power>1){
					builder.append("^").append(power);
				}
			}
		}
		return builder.toString();
	}
}
