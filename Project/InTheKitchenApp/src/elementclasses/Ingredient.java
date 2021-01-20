package elementclasses;

public class Ingredient {
	private String naam;
	private String foto_naam;
	private String beschrijving;
	private String ID;
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String getFoto_naam() {
		return foto_naam;
	}
	public void setFoto_naam(String foto_naam) {
		this.foto_naam = foto_naam;
	}
	public String getBeschrijving() {
		return beschrijving;
	}
	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
}