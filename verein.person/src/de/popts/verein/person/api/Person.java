package de.popts.verein.person.api;

import java.util.Date;

public class Person {

	private String oid;
	
	private String name;
	
	private String vorname;
	
	private Date geburtsDatum;
	
	private String strasse;
	
	private String hausnr;
	
	private String plz;
	
	private String ort;
	
	private String land;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((geburtsDatum == null) ? 0 : geburtsDatum.hashCode());
		result = prime * result + ((hausnr == null) ? 0 : hausnr.hashCode());
		result = prime * result + ((land == null) ? 0 : land.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + ((ort == null) ? 0 : ort.hashCode());
		result = prime * result + ((plz == null) ? 0 : plz.hashCode());
		result = prime * result + ((strasse == null) ? 0 : strasse.hashCode());
		result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
		return result;
	}

	public Person() {
		// nothing to do
	}
	
	public Person(String oid) {
		this.oid = oid;
	}

	public Person(Person other) {
		this.oid = other.oid;
		this.name = other.name;
		this.vorname = other.vorname;
		this.geburtsDatum = other.geburtsDatum;
		this.strasse = other.strasse;
		this.hausnr = other.hausnr;
		this.plz = other.plz;
		this.ort = other.ort;
		this.land = other.land;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (geburtsDatum == null) {
			if (other.geburtsDatum != null)
				return false;
		} else if (!geburtsDatum.equals(other.geburtsDatum))
			return false;
		if (hausnr == null) {
			if (other.hausnr != null)
				return false;
		} else if (!hausnr.equals(other.hausnr))
			return false;
		if (land == null) {
			if (other.land != null)
				return false;
		} else if (!land.equals(other.land))
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
		if (ort == null) {
			if (other.ort != null)
				return false;
		} else if (!ort.equals(other.ort))
			return false;
		if (plz == null) {
			if (other.plz != null)
				return false;
		} else if (!plz.equals(other.plz))
			return false;
		if (strasse == null) {
			if (other.strasse != null)
				return false;
		} else if (!strasse.equals(other.strasse))
			return false;
		if (vorname == null) {
			if (other.vorname != null)
				return false;
		} else if (!vorname.equals(other.vorname))
			return false;
		return true;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Date getGeburtsDatum() {
		return geburtsDatum;
	}

	public void setGeburtsDatum(Date geburtsDatum) {
		this.geburtsDatum = geburtsDatum;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnr() {
		return hausnr;
	}

	public void setHausnr(String hausnr) {
		this.hausnr = hausnr;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	@Override
	public String toString() {
		return "Person [oid=" + oid + ", name=" + name + ", vorname=" + vorname
				+ ", geburtsDatum=" + geburtsDatum + ", strasse=" + strasse
				+ ", hausnr=" + hausnr + ", plz=" + plz + ", ort=" + ort
				+ ", land=" + land + "]";
	}
	
	
}
