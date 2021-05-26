package jpp.digraph.graph;

public class CostEdge<N extends INode> implements ICostEdge<N> {
	final String id;
	N source;
	N target;
	double cost;
	String description = "";

	public CostEdge(String id, N source, N target, double cost) {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.id = id;
		this.source = source;
		this.target = target;
		this.cost = cost;
	}

	public CostEdge(String id, N source, N target, double cost, String description) {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.id = id;
		this.source = source;
		this.target = target;
		this.cost = cost;
		this.description = description;
	}

	public String getDescription() {
		return description;

	}

	public N getSource() {
		return source;

	}

	public N getTarget() {
		return target;

	}

	public double getCost() {
		return cost;

	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CostEdge other = (CostEdge) obj;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
