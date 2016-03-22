

/**
 * @author yos
 *
 */
public class Polynom {
	private PolyNode head;
	
	/**
	 * initialize an empty polynom
	 */
	public Polynom(){
		head=null;
	}
	/**
	 * initialize polynom with a head element
	 * @param head
	 */
	public Polynom(PolyNode head){
		this.head=head;
	}
	
	/**
	 * add node in the right position (by power)
	 * @param p
	 * @return polynom
	 */
	public Polynom addNode(PolyNode p){
		PolyNode node=head, prev=null;
		while (node != null){
			if (node.getPower() == p.getPower()){
				double coefficient = node.getCoefficient() + p.getCoefficient();
				node.setCoefficient(coefficient);
				return this;
			} else if (node.getPower()<p.getPower()){
				break;
			}
			prev=node;
			node=node.getNext();
		}
		p.setNext(node);
		if (prev == null){
			head=p;
		} else {
			prev.setNext(p);
		}
		return this;
	}
	
}
