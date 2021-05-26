/*
 * 
 */
package jpp.digraph.graph;

// TODO: Auto-generated Javadoc
/**
 * The Class CostEdge.
 *
 * @param <N> the number type
 */
public class CostEdge<N extends INode> implements ICostEdge<N> {
	
	/** The description. */
	private String description="";
	
	/** The source. */
	private N source;
	
	/** The target. */
	private N target;
	
	/** The cost. */
	private double cost;
	
	
	/**
	 * Instantiates a new cost edge.
	 *
	 * @param source the source
	 * @param target the target
	 * @param cost the cost
	 */
	public CostEdge(N source, N target, double cost){
		this.source = source;
		this.target = target;
		this.cost	= cost;
		
	}
//    Erzeugt einen bewerteten Pfeil. Als Beschreibung des Pfeils wird ein leerer String verwendet.
	/**
 * Instantiates a new cost edge.
 *
 * @param description the description
 * @param source the source
 * @param target the target
 * @param cost the cost
 */
public CostEdge(String description, N source, N target, double cost){
		this.description = description;
		this.source = source;
		this.target = target;
		this.cost	= cost;
		
	}
//    Erzeugt einen bewerteten Pfeil.

	

	/* (non-Javadoc)
 * @see jpp.digraph.graph.IEdge#getDescription()
 */
@Override
	public String getDescription() {
		return this.description;
	}

	
	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IEdge#getSource()
	 */
	@Override
	public N getSource() {
		return this.source;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.IEdge#getTarget()
	 */
	@Override
	public N getTarget() {
		return this.target;
	}

	/* (non-Javadoc)
	 * @see jpp.digraph.graph.ICostEdge#getCost()
	 */
	@Override
	public double getCost() {
		return this.cost;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return description +"\t"+ source
				+ " to " + target + "\t" + cost;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CostEdge other = (CostEdge) obj;
		if (Double.doubleToLongBits(cost) != Double
				.doubleToLongBits(other.cost))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}
	
	
}
