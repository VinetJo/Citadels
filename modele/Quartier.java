package modele;

public class Quartier {
	
	private String nom = "";
	private String type = "";
	private int coutConstruction = 0;
	private String caracteristiques = "";
	public static final String [] TYPE_QUARTIERS = {"RELIGIEUX","MILITAIRE","NOBLE","COMMERCANT","MERVEILLE"};

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		for(int i = 0; i<this.TYPE_QUARTIERS.length;i++) {
			if(type.equals(TYPE_QUARTIERS[i])) {
				this.type = type;
			}
			if(i==(this.TYPE_QUARTIERS.length-1)&& this.type!=type) {
				this.type="";
				}
			}
		}
	
	public int getCout() {
		return coutConstruction;
	}
	
	public void setCout(int coutConstruction) {	
		if(coutConstruction>=1 && coutConstruction<=6) {
			this.coutConstruction = coutConstruction;
		}else {
			this.coutConstruction = 0;
		}
	}
	
	public String getCaracteristiques() {
		return caracteristiques;
	}
	
	public void setCaracteristiques(String caracteristiques) {
		this.caracteristiques = caracteristiques;
	}
	
	public Quartier(String nom, String type, int cout, String caracteristiques) {		
		this.nom = nom;
		this.type = type;
		this.coutConstruction = cout;
		this.caracteristiques = caracteristiques;
		
	}
	public Quartier(String nom, String type, int cout) {		
		this.nom = nom;
		this.type = type;
		this.coutConstruction = cout;
		
	}
	public Quartier() {
		
	}

	@Override
	public String toString() {
		return "" + nom + " - type: " + type + " - pièces: " + coutConstruction;
	}
	
	

}