package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Personne;

public class PersonneBusiness {

	private List<Personne> personnes = new ArrayList<>();

	public PersonneBusiness() {
		Personne p = new Personne();
		p.setId(0);
		p.setPrenom("Etienne");
		p.setNom("BEGON");
		p.setNaissance(new Date());
		personnes.add(p);
		p = new Personne();
		p.setId(0);
		p.setPrenom("Ernesto");
		p.setNom("EXPOSITO");
		p.setNaissance(new Date());
		personnes.add(p);
	}

	public List<Personne> getAll() {
		return personnes;
	}

	public Personne get(long id) {
		return personnes.stream().filter(p -> p.getId() == id).findAny().orElse(null);
	}

	public void add(Personne personne) {
		personnes.add(personne);
	}

	public Personne update(Personne personne) {
		Personne oldPersonne = personnes.stream().filter(p -> p.getId() == personne.getId()).findAny().orElse(null);
		if (oldPersonne != null) {
			personnes.remove((int) personne.getId());
			personnes.add(personne);
			return personne;
		}
		return null;
	}

	public void delete(Personne personne) {
		personnes.remove((int) personne.getId());
	}

	public Personne search(String nom, String prenom) {
		if (nom != null && prenom != null) {
			return personnes.stream().filter(p -> nom.contains(p.getNom()) && prenom.contains(p.getPrenom())).findAny()
					.orElse(null);
		}
		return null;
	}

}
