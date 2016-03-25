

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
	
	
	/**find a node with power >= to the requested power. if none return null.
	 * <br> examples:
	 * <br>when searching power=3 for this polynom: x^5+x^4+x^2 we return x^4
	 * <br>when searching power=6 for the same polynom return null
	 * <br><b>efficiency: o(n)</b> where n is the number of elements in this polynom.
	 * @param start node to start search from
	 * @param power
	 * @return poly node with power >= to the requested power
	 */
	PolyNode findNearest(final PolyNode start, final int power){
		PolyNode prev=null, curr=start;
		while (curr!=null && curr.getPower()>= power){
			prev = curr;
			curr=curr.getNext();
		}
		return prev;
	}
	
	/**add poly node starting from a given node as long as a head update is not required.
	 * <br>case1.1 / case1.2: p.power > start.power. head update is required, p will not be added. return p  
	 * <br>case2: no matching node with power == p.power: link p into it's nearest. return nearest
	 * <br>case3: there is a node such as node.power==p.power: update node's coefficient. 
	 * delete it in case it's coefficient becomes 0 and it is not the last node.
	 * return the nearest node found or it's next (in case we delete it)
	 * <br><b>efficiency: o(n)</b> where n is the number of elements in this polynom.
	 * @param start node to start search from
	 * @param p
	 * @return when an update cannot be made return p, otherwise return nearest node
	 */
	PolyNode addPolyNode(PolyNode start, PolyNode p){
		if (p!=null){
			if (start == null){ // case 1.1: no head - p > polynom.head  (no head)
				return p;				
			} else { // find closest
				PolyNode nearest = findNearest(start, p.getPower());
				if (nearest == null){ // case 1.2: p > polynom.head
					return p;				
				} else {
					PolyNode next = nearest.getNext();
					if (nearest.getPower()>p.getPower()){ // case2: p.power > closest.power link: 
						p.setNext(next);				  // closest -> p -> (closest.next)
						nearest.setNext(p);
					} else if (nearest.getPower() == p.getPower()){ // case3: closest.power==p.power
						nearest.setCoefficient(p.getCoefficient()+nearest.getCoefficient());
						if (nearest.getCoefficient()==0){
							// replace this node with next node (delete) if possible
							if (next!=null){
								nearest.setCoefficient(next.getCoefficient());
								nearest.setPower(next.getPower());
								nearest.setNext(next.getNext());
							}
						}
					}
				}
				return nearest;
			}
		}
		return null;
	}
	/**
	 * add node in the right position (by power)
	 * <br><b>efficiency: o(n)</b> where n is the number of elements in this polynom.
	 * @param p
	 * @return this polynom
	 */
	public Polynom addNode(PolyNode p){
		PolyNode node = addPolyNode(head, p);
		if (node == p){ // we need to replace head
			node.setNext(head);
			head=node;				
		}
		return this;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Polynom){
			Polynom polynom =(Polynom)obj;
			return toString().equals(polynom.toString());
		} else {
			return super.equals(obj);
		}
	}
	
	/**
	 * multiply polynom by scalar 
	 * <br><b>efficiency: o(n)</b> where n is the number of elements in this polynom.
	 * @param scalar
	 * @return this polynom
	 */
	public Polynom multByScalar(int scalar) {
		if (scalar==0){
			head=null;
		} else {
			for (PolyNode node=head; node!=null; node=node.getNext()){
				node.setCoefficient(node.getCoefficient()*scalar);
			}
		}
		return this;
	}
	
	/**
	 * add polynom
	 * <br><b>efficiency: o(n+m)</b> where n is the number of elements in polynom, m is the number of element in this.
	 * @param polynom
	 * @return this polynom
	 */
	public Polynom addPol(Polynom polynom){
		if (head==null){
			head=polynom.head;
		} else {
			PolyNode node = head, next=null;
			for (PolyNode p=polynom.head; p!=null; p=next){
				next=p.getNext();
				node = addPolyNode(node, p);
				if (node==p){ // we need to update head
					node.setNext(head);
					head=node;
				}
			}
		}
		return this;
	}
	
	/**
	 * @return head - highest power element
	 */
	public PolyNode getHead() {
		return head;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * <br><b>efficiency: o(n)</b> where n is the number of elements in this polynom.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (PolyNode node=head; node!=null; node=node.getNext()){
			if (!node.toString().isEmpty()){
				if (!node.toString().startsWith("-")){
					builder.append("+");
				}
				builder.append(node);
			}
		}
		return builder.substring(builder.length()>0?(builder.charAt(0)=='-'?0:1):0);
	}
	
}
