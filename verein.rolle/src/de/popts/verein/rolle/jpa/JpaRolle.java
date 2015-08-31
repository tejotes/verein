package de.popts.verein.rolle.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.popts.verein.rolle.api.Rolle;

@Entity
@Table(name="Rolle")
public class JpaRolle {

	@Id
	private String oid;

	@Temporal(TemporalType.TIMESTAMP)	
	private Date erzeugt;
	
	@Temporal(TemporalType.DATE)	
	private Date startDatum;
	
	private String startGrund;
	
	@Temporal(TemporalType.DATE)	
	private Date endeDatum;
	
	private String endeGrund;
	
	private String rollenTypOid;
	
	private String einheitOid;
	
	private String personOid;

	public static JpaRolle fromRolle(Rolle rolle) {
		JpaRolle jpaRolle = new JpaRolle();
		
		jpaRolle.setOid(rolle.getOid());
		jpaRolle.setErzeugt(rolle.getErzeugt());
		jpaRolle.setStartDatum(rolle.getStartDatum());
		jpaRolle.setStartGrund(rolle.getStartGrund());
		jpaRolle.setEndeDatum(rolle.getEndeDatum());
		jpaRolle.setEndeGrund(rolle.getEndeGrund());
		jpaRolle.setRollenTypOid(rolle.getRollenTypOid());
		jpaRolle.setEinheitOid(rolle.getEinheitOid());
		jpaRolle.setPersonOid(rolle.getPersonOid());
		
		return jpaRolle;
	}
	
	public Rolle toRolle() {
		Rolle rolle = new Rolle(getOid(), getStartDatum(), getStartGrund(), getEinheitOid(), getPersonOid(), getRollenTypOid());
		
		return rolle;
	}
	
	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getErzeugt() {
		return erzeugt;
	}

	public void setErzeugt(Date erzeugt) {
		this.erzeugt = erzeugt;
	}

	public Date getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(Date startDatum) {
		this.startDatum = startDatum;
	}

	public String getStartGrund() {
		return startGrund;
	}

	public void setStartGrund(String startGrund) {
		this.startGrund = startGrund;
	}

	public Date getEndeDatum() {
		return endeDatum;
	}

	public void setEndeDatum(Date endeDatum) {
		this.endeDatum = endeDatum;
	}

	public String getEndeGrund() {
		return endeGrund;
	}

	public void setEndeGrund(String endeGrund) {
		this.endeGrund = endeGrund;
	}

	public String getRollenTypOid() {
		return rollenTypOid;
	}

	public void setRollenTypOid(String rollenTypOid) {
		this.rollenTypOid = rollenTypOid;
	}

	public String getEinheitOid() {
		return einheitOid;
	}

	public void setEinheitOid(String einheitOid) {
		this.einheitOid = einheitOid;
	}

	public String getPersonOid() {
		return personOid;
	}

	public void setPersonOid(String personOid) {
		this.personOid = personOid;
	}
	
	
	
}
