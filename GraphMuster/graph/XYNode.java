package jpp.digraph.graph;

public class XYNode implements IXYNode {
	final String id;
	int x;
	int y;
	String description="";

	public XYNode(String id, int x, int y) {
		if (id == null|| id.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.id = id;
		this.x = x;
		this.y = y;

	}

	public XYNode(String id, int x, int y, String description) {
		if (id == null|| id.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.id = id;
		this.x = x;
		this.y = y;
		this.description = description;
	}

	public String getId() {
		return id;

	}

	public String getDescription() {
		return description;

	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + x;
		result = prime * result + y;
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
		XYNode other = (XYNode) obj;
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
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
