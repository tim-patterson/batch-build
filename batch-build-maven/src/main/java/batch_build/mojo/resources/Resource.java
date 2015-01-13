package batch_build.mojo.resources;

public abstract class Resource implements Comparable<Resource>{
	
	public abstract String getUniqueIdentifier();
	
	public String toString(){
		return getUniqueIdentifier();
	}

	public int compareTo(Resource o) {
		return toString().compareTo(o.toString());
	}
	
	@Override
	public int hashCode() {
		return getUniqueIdentifier().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HCatResource other = (HCatResource) obj;
		return getUniqueIdentifier().equals(other.getUniqueIdentifier());
	}
}
