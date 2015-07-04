package de.popts.verein.rollentyp;

public class RollenTyp {

	String oid;
	
	RollenArt art;

	String id;
	
	String name;
	
	public RollenTyp(String oid) {
		super();
		this.oid = oid;
	}
	
	public RollenTyp(RollenTyp rollenTyp) {
		super();
		this.oid = rollenTyp.getOid();
		this.id = rollenTyp.getId();
		this.name = rollenTyp.getName();
		this.art = rollenTyp.getArt();
	}
	
	
	public void validate() throws RollenTypException {
		// check art
		if (art == null || art == RollenArt.NONE || art == RollenArt.ANY) {
			String message = String.format("invalid art '%s'", art);
			throw new RollenTypException(message);
		}
		
		// check id
		if (id == null || id.trim().isEmpty()) {
			String message = String.format("invalid id '%s'", id);
			throw new RollenTypException(message);
		}
		id = id.trim();
		
		// check name
		if (name == null || name.trim().isEmpty()) {
			String message = String.format("invalname name '%s'", name);
			throw new RollenTypException(message);
		}
		name = name.trim();
	}
	
	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RollenArt getArt() {
		return art;
	}

	public void setArt(RollenArt art) {
		this.art = art;
	}

	@Override
	public String toString() {
		return "RollenTyp [oid=" + oid + ", id=" + id + ", name=" + name + ", art=" + art + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((art == null) ? 0 : art.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
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
		RollenTyp other = (RollenTyp) obj;
		if (art != other.art)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		return true;
	}
}
