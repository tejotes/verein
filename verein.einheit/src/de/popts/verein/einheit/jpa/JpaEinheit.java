package de.popts.verein.einheit.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.popts.verein.einheit.api.Einheit;
import de.popts.verein.einheit.api.EinheitArt;

@Entity
@Table(name = "einheit")
public class JpaEinheit {

	@Id
	private String oid;

	@Enumerated(EnumType.STRING)
	private EinheitArt art;
	
	private String id;
	
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)	
	private Date created;
	
	String oberEinheitOid;

	public static JpaEinheit fromEinheit(Einheit einheit) {
		JpaEinheit jpaEinheit = new JpaEinheit();

		jpaEinheit.setOid(einheit.getOid());
		jpaEinheit.setArt(einheit.getArt());
		jpaEinheit.setId(einheit.getId());
		jpaEinheit.setName(einheit.getName());
		jpaEinheit.setCreated(einheit.getCreated());
		jpaEinheit.setOberEinheitOid(einheit.getOberEinheitOid());
		
		// return result
		return jpaEinheit;
	}
	
	public Einheit toEinheit() {
		
		Einheit einheit = new Einheit();
		
		einheit.setOid(oid);
		einheit.setArt(art);
		einheit.setId(id);
		einheit.setName(name);
		einheit.setCreated(created);
		einheit.setOberEinheitOid(oberEinheitOid);
		
		// return result
		return einheit;
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

	public String getOberEinheitOid() {
		return oberEinheitOid;
	}

	public void setOberEinheitOid(String oberEinheitOid) {
		this.oberEinheitOid = oberEinheitOid;
	}

	@Override
	public String toString() {
		return "JpaEinheit [oid=" + oid + ", art=" + art + ", id=" + id
				+ ", name=" + name + ", created=" + created
				+ ", oberEinheitOid=" + oberEinheitOid + "]";
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
		JpaEinheit other = (JpaEinheit) obj;
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
