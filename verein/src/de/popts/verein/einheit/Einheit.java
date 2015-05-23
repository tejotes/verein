package de.popts.verein.einheit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Einheit {

	String oid;
	
	EinheitArt art;
	
	String id;
	
	String name;
	
	Date created;
	
	String oberEinheitOid;

	@Override
	public String toString() {
		return "Einheit [oid=" + oid + ", art=" + art + ", id=" + id
				+ ", name=" + name + ", created=" + created
				+ ", oberEinheitOid=" + oberEinheitOid + "]";
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public EinheitArt getArt() {
		return art;
	}

	public void setArt(EinheitArt art) {
		this.art = art;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Einheit getObereinheit() {
		return null;
	}
	
	public List<Einheit> listUnterEinheit() {
		return new ArrayList<Einheit>();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((art == null) ? 0 : art.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((oberEinheitOid == null) ? 0 : oberEinheitOid.hashCode());
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
		Einheit other = (Einheit) obj;
		if (art != other.art)
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
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
		if (oberEinheitOid == null) {
			if (other.oberEinheitOid != null)
				return false;
		} else if (!oberEinheitOid.equals(other.oberEinheitOid))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		return true;
	}
	
	
}
