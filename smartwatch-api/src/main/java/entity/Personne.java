package entity;

import java.util.Date;

import lombok.Data;

@Data
public class Personne {

	private long id;
	private String prenom;
	private String nom;
	private Date naissance;

}
