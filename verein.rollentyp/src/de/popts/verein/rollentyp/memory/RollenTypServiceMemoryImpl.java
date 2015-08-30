package de.popts.verein.rollentyp.memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.felix.dm.annotation.api.Component;

import de.popts.verein.einheit.api.Einheit;
import de.popts.verein.einheit.api.EinheitArt;
import de.popts.verein.rollentyp.api.RollenArt;
import de.popts.verein.rollentyp.api.RollenTyp;
import de.popts.verein.rollentyp.api.RollenTypService;
import de.popts.verein.rollentyp.api.RollenTypException;

@Component
public class RollenTypServiceMemoryImpl implements RollenTypService {

	private Map<String, RollenTyp> rollenTypMap = new ConcurrentHashMap<>();
	private Map<Einheit, List<RollenTyp>> rollenTypList2EinheitMap = new ConcurrentHashMap<>();
	private Map<EinheitArt, List<RollenTyp>> rollenTypList2EinheitArtMap = new ConcurrentHashMap<>();
	
	@Override
	public RollenTyp rollenTypErzeugen(RollenTyp rollenTyp) throws RollenTypException {
		// check rollentyp
		if (rollenTyp == null) {
			throw new RollenTypException("rollenTyp == null");
		}
		
		// validate rollentyp
		rollenTyp.validate();
		
		// generate oid
		rollenTyp.setOid(UUID.randomUUID().toString());
		
		// get key
		String key = rollenTyp.getId();
		
		// check duplicate
		if (rollenTypMap.containsKey(key)) {
			String message = String.format("duplicate id '%s'", key);
			throw new RollenTypException(message);
		}
		
		// put in map
		rollenTypMap.put(key, rollenTyp);
		
		// return result
		return rollenTyp;
	}

	@Override
	public void rollenTypLoeschen(RollenTyp rollenTyp) throws RollenTypException {
		// check rollentyp
		if (rollenTyp == null) {
			String message = String.format("rollenTyp == null");
			throw new RollenTypException(message);
		}
		
		// get key
		String key = rollenTyp.getId();
		
		// check existence
		if (!rollenTypMap.containsKey(key)) {
			String message = String.format("unknown id '%s'", rollenTyp);
			throw new RollenTypException(message);
		}
		
		// remove from map
		rollenTypMap.remove(key);
	}

	@Override
	public void addRollenTypToEinheitArt(RollenTyp rollenTyp, EinheitArt einheitArt) throws RollenTypException {
		// check rollenTyp
		if (rollenTyp == null) {
			throw new RollenTypException("rollenTyp == null");
		}
		
		// check einheitArt
		if (einheitArt == null) {
			throw new RollenTypException("einheitArt == null");
		}
		
		// get all entries for einheitArt
		List<RollenTyp> rollenTypList = rollenTypList2EinheitArtMap.get(einheitArt);
		if (rollenTypList == null) {
			rollenTypList = new ArrayList<>();
			rollenTypList2EinheitArtMap.put(einheitArt, rollenTypList);
		}
		
		// insert if no duplicate
		if (rollenTypList.contains(einheitArt)) {
			String message = String.format("duplicate rollenTyp '%s' for einheitArt '%s'", rollenTyp, einheitArt);
			throw new RollenTypException(message);
		} else {
			rollenTypList.add(rollenTyp);
		}
	}

	@Override
	public void removeRollenTypFromEinheitArt(RollenTyp rollenTyp, EinheitArt einheitArt) throws RollenTypException {
		// check rollenTyp
		if (rollenTyp == null) {
			throw new RollenTypException("rollenTyp == null");
		}
		
		// check einheitArt
		if (einheitArt == null) {
			throw new RollenTypException("einheitArt == null");
		}
		
		// get all entries for einheitArt
		List<RollenTyp> rollenTypList = rollenTypList2EinheitArtMap.get(einheitArt);
		
		// remove if defined
		if (rollenTypList != null && rollenTypList.contains(rollenTyp)) {
			rollenTypList.remove(rollenTyp);
		} else {
			String message = String.format("unknown rollenTyp '%s' for einheit '%s'", rollenTyp, einheitArt);
			throw new RollenTypException(message);
		}
	}

	@Override
	public void addRollenTypToEinheit(RollenTyp rollenTyp, Einheit einheit) throws RollenTypException {
		// check rollenTyp
		if (rollenTyp == null) {
			throw new RollenTypException("rollenTyp == null");
		}
		
		// check einheit
		if (einheit == null) {
			throw new RollenTypException("einheit == null");
		}
		
		// get all entries for einheit
		List<RollenTyp> rollenTypList = rollenTypList2EinheitMap.get(einheit);
		if (rollenTypList == null) {
			rollenTypList = new ArrayList<>();
			rollenTypList2EinheitMap.put(einheit, rollenTypList);
		}
		
		// insert if no duplicate
		if (rollenTypList.contains(rollenTyp)) {
			String message = String.format("duplicate rollenTyp '%s' for einheit '%s'", rollenTyp, einheit);
			throw new RollenTypException(message);
		} else {
			rollenTypList.add(rollenTyp);
		}
	}

	@Override
	public void removeRollenTypFromEinheit(RollenTyp rollenTyp, Einheit einheit) throws RollenTypException {
		// check rollenTyp
		if (rollenTyp == null) {
			throw new RollenTypException("rollenTyp == null");
		}
		
		// check einheit
		if (einheit == null) {
			throw new RollenTypException("einheit == null");
		}
		
		// get all entries for einheit
		List<RollenTyp> rollenTypList = rollenTypList2EinheitMap.get(einheit);
		
		// remove if defined
		if (rollenTypList != null && rollenTypList.contains(rollenTyp)) {
			rollenTypList.remove(rollenTyp);
		} else {
			String message = String.format("unknown rollenTyp '%s' for einheit '%s'", rollenTyp, einheit);
			throw new RollenTypException(message);
		}
	}

	@Override
	public List<RollenTyp> listRollenTyp4EinheitArtAndRollenArt(EinheitArt einheitArt, RollenArt rollenArt) throws RollenTypException {
		// check einheitArt
		if (einheitArt == null) {
			throw new RollenTypException("einheitArt == null");
		}
		if (rollenArt == null) {
			throw new RollenTypException("rollenArt == null");
		}

		// get all entries for einheitArt
		List<RollenTyp> rollenTypList = rollenTypList2EinheitArtMap.get(einheitArt); 
		if (rollenTypList == null) {
			rollenTypList = Collections.emptyList();
		} else {
			rollenTypList = filterRollenTypList(rollenTypList, rollenArt);
		}

		// return result
		return rollenTypList;
	}

	@Override
	public List<RollenTyp> listRollenTyp4EinheitAndRollenArt(Einheit einheit, RollenArt rollenArt) throws RollenTypException {
		// check einheit
		if (einheit == null) {
			throw new RollenTypException("einheit == null");
		}
		if (rollenArt == null) {
			throw new RollenTypException("rollenArt == null");
		}

		// get all entries for einheitArt
		List<RollenTyp> rollenTypList = rollenTypList2EinheitMap.get(einheit); 
		if (rollenTypList == null) {
			rollenTypList = Collections.emptyList();
		} else {
			rollenTypList = filterRollenTypList(rollenTypList, rollenArt);
		}

		// return result
		return rollenTypList;
	}

	private List<RollenTyp> filterRollenTypList(Collection<RollenTyp> rollenTypCollection, RollenArt rollenArt) throws RollenTypException {
		// check params
		if (rollenTypCollection == null) {
			throw new RollenTypException("rollenTypList == null");
		}
		if (rollenArt == null) {
			throw new RollenTypException("art == null");
		}
		
		// calc result
		List<RollenTyp> resultList = new ArrayList<>(rollenTypCollection.size());
		for (RollenTyp rollenTyp : rollenTypCollection) {
			if (rollenArt == RollenArt.ANY || rollenArt == rollenTyp.getArt()) {
				resultList.add(rollenTyp);
			}
		}
		
		// return result
		return resultList;
	}

	@Override
	public List<RollenTyp> listAllRollenTyp4EinheitAndRollenArt(Einheit einheit, RollenArt rollenArt) throws RollenTypException {
		// check params
		if (einheit == null) {
			throw new RollenTypException("einheit == null");
		}
		if (rollenArt == null) {
			throw new RollenTypException("rollenArt == null");
		}
		
		// list for einheit
		List<RollenTyp> einheitList = listRollenTyp4EinheitAndRollenArt(einheit, rollenArt);
		
		// list for einheitArt
		List<RollenTyp> einheitArtList = listRollenTyp4EinheitArtAndRollenArt(einheit.getArt(), rollenArt);

		// merge lists
		HashSet<RollenTyp> resultSet = new HashSet<>(einheitList.size() + einheitArtList.size());
		resultSet.addAll(einheitList);
		resultSet.addAll(einheitArtList);
		
		// return result
		return Arrays.asList(resultSet.toArray(new RollenTyp[resultSet.size()]));
	
	}

	@Override
	public boolean isValidRollenTyp4Einheit(Einheit einheit, RollenTyp rollenTyp) throws RollenTypException {
		// check params
		if (einheit == null) {
			throw new RollenTypException("einheit == null");
		}
		if (rollenTyp == null) {
			throw new RollenTypException("rollenTyp == null");
		}
		
		// check entries for EinheitType
		List<RollenTyp> rollenTypList = listRollenTyp4EinheitArtAndRollenArt(einheit.getArt(), rollenTyp.getArt());
		if (rollenTypList.contains(rollenTyp)) {
			return true;
		}
		
		// check entries for Einheit
		rollenTypList = listRollenTyp4EinheitAndRollenArt(einheit, rollenTyp.getArt());
		if (rollenTypList.contains(rollenTyp)) {
			return true;
		}

		// not found
		return false;
	}

	@Override
	public List<RollenTyp> listRollenTyp4RollenArt(RollenArt rollenArt) throws RollenTypException {
		
		System.out.println("size="+rollenTypMap.size());
		
		// check rollenArt
		if (rollenArt == null) {
			throw new RollenTypException("rollenArt == null");
		}
		
		// declare result
		List<RollenTyp> rollenTypList = filterRollenTypList(rollenTypMap.values(), rollenArt);

		// return result
		return rollenTypList;
	}
	
}
