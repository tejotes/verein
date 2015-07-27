package de.popts.verein.rollentyp.jpa;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import de.popts.verein.einheit.api.EinheitArt;

@Entity
@Table(name = "rollentyp")
public class JpaRollenTypZuordnung {

	@Id
	private String oid;

	@Enumerated(EnumType.STRING)
	private EinheitArt einheitArt;
	
	private String einheitOid;
	
	private String rollenTypOid;

	// generated
	
	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public EinheitArt getEinheitArt() {
		return einheitArt;
	}

	public void setEinheitArt(EinheitArt einheitArt) {
		this.einheitArt = einheitArt;
	}

	public String getEinheitOid() {
		return einheitOid;
	}

	public void setEinheitOid(String einheitOid) {
		this.einheitOid = einheitOid;
	}

	public String getRollenTypOid() {
		return rollenTypOid;
	}

	public void setRollenTypOid(String rollenTypOid) {
		this.rollenTypOid = rollenTypOid;
	}

	@Override
	public String toString() {
		return "JpaRollenTypZuordnung [oid=" + oid + ", einheitArt=" + einheitArt + ", einheitOid=" + einheitOid
				+ ", rollenTypOid=" + rollenTypOid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((einheitArt == null) ? 0 : einheitArt.hashCode());
		result = prime * result + ((einheitOid == null) ? 0 : einheitOid.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + ((rollenTypOid == null) ? 0 : rollenTypOid.hashCode());
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
		JpaRollenTypZuordnung other = (JpaRollenTypZuordnung) obj;
		if (einheitArt != other.einheitArt)
			return false;
		if (einheitOid == null) {
			if (other.einheitOid != null)
				return false;
		} else if (!einheitOid.equals(other.einheitOid))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (rollenTypOid == null) {
			if (other.rollenTypOid != null)
				return false;
		} else if (!rollenTypOid.equals(other.rollenTypOid))
			return false;
		return true;
	}

}
