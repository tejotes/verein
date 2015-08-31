package de.popts.verein.rolle.api;

import java.util.Date;

public class Rolle {

	private String oid;
	
	private Date erzeugt;
	
	private Date startDatum;
	
	private Date endeDatum;
	
	private String startGrund;
	
	private String endeGrund;
	
	private String einheitOid;
	
	private String personOid;
	
	private String rollenTypOid;

	@Override
	public String toString() {
		return "Rolle [oid=" + oid + ", erzeugt=" + erzeugt + ", startDatum=" + startDatum + ", endeDatum=" + endeDatum
				+ ", startGrund=" + startGrund + ", endeGrund=" + endeGrund + ", einheitOid=" + einheitOid
				+ ", personOid=" + personOid + ", rollenTypOid=" + rollenTypOid + "]";
	}

	public Rolle() {
		super();
	}
	
	public Rolle(String oid) {
		this();
		this.oid = oid;
	}
	
	public Rolle(String oid, Date startDatum, String grund, String einheitOid, String personOid, String rollenTypOid) {
		// call other constructor
		this(oid);
		
		// init my attribs
		this.erzeugt = new Date();
		this.startDatum = startDatum;
		this.startGrund = grund;
		this.einheitOid = einheitOid;
		this.personOid = personOid;
		this.rollenTypOid = rollenTypOid;
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

	public Date getEndeDatum() {
		return endeDatum;
	}

	public void setEndeDatum(Date endeDatum) {
		this.endeDatum = endeDatum;
	}

	public String getStartGrund() {
		return startGrund;
	}

	public void setStartGrund(String startGrund) {
		this.startGrund = startGrund;
	}

	public String getEndeGrund() {
		return endeGrund;
	}

	public void setEndeGrund(String endeGrund) {
		this.endeGrund = endeGrund;
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

	public String getRollenTypOid() {
		return rollenTypOid;
	}

	public void setRollenTypOid(String rollenTypOid) {
		this.rollenTypOid = rollenTypOid;
	}
	
}
