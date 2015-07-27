package de.popts.verein.rollentyp.jpa;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import de.popts.verein.rollentyp.api.RollenArt;
import de.popts.verein.rollentyp.api.RollenTyp;

@Entity
@Table(name = "rollentyp")
public class JpaRollenTyp {
	@Id
	private String oid;

	@Enumerated(EnumType.STRING)
	private RollenArt art;
	
	private String id;
	
	private String name;

	public static JpaRollenTyp fromRollenTyp(RollenTyp rollenTyp) {
		JpaRollenTyp jpaRollenTyp = new JpaRollenTyp();
		
		jpaRollenTyp.setOid(rollenTyp.getOid());
		jpaRollenTyp.setArt(rollenTyp.getArt());
		jpaRollenTyp.setId(rollenTyp.getId());
		jpaRollenTyp.setName(rollenTyp.getName());
		
		return jpaRollenTyp;
	}
	
	public RollenTyp toRollenTyp() {
		
		RollenTyp rollenTyp = new RollenTyp();
		
		rollenTyp.setOid(oid);
		rollenTyp.setArt(art);
		rollenTyp.setId(id);
		rollenTyp.setName(name);
		
		return rollenTyp;
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
		JpaRollenTyp other = (JpaRollenTyp) obj;
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

	@Override
	public String toString() {
		return "JpaRollenTyp [oid=" + oid + ", art=" + art + ", id=" + id + ", name=" + name + "]";
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public RollenArt getArt() {
		return art;
	}

	public void setArt(RollenArt art) {
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

}
