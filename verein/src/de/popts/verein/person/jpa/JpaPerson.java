package de.popts.verein.person.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

import de.popts.verein.person.Person;

@Entity
public class JpaPerson {
	
	@Id
	private String oid;
	
	private String name;
	
	private String vorname;
	
	private String strasse;
	
	private String hausnr;
	
	private String plz;
	
	private String ort;
	
	private String land;

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
		return "JpaPerson [oid=" + oid + ", name=" + name + ", vorname="
				+ vorname + ", strasse=" + strasse + ", hausnr=" + hausnr
				+ ", plz=" + plz + ", ort=" + ort + ", land=" + land + "]";
	}
	
	public static JpaPerson fromPerson(Person person) {
		
		JpaPerson jpaPerson = new JpaPerson();
		jpaPerson.setName(person.getName());
		jpaPerson.setVorname(person.getVorname());
		jpaPerson.setStrasse(person.getStrasse());
		jpaPerson.setHausnr(person.getHausnr());
		jpaPerson.setPlz(person.getPlz());
		jpaPerson.setOrt(person.getOrt());
		jpaPerson.setLand(person.getLand());
		
		return jpaPerson;
	}

	public Person toPerson() {
		Person person = new Person();
		person.setName(name);
		person.setVorname(vorname);
		person.setStrasse(strasse);
		person.setHausnr(hausnr);
		person.setPlz(plz);
		person.setOrt(ort);
		person.setLand(land);
		
		return person;
	}
	
}
