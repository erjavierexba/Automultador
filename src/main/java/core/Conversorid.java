package core;

public class Conversorid {
 
	private int id,responsable;
	private String lugar;
	private float velLimite;
	
	
	
	
	public Conversorid(int id, String lugar, float velLimite, int responsable) {
		super();
		this.id = id;
		this.lugar = lugar;
		this.velLimite = velLimite;
		this.responsable = responsable;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getResponsable() {
		return responsable;
	}
	public void setResponsable(int responsable) {
		this.responsable = responsable;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public float getVelLimite() {
		return velLimite;
	}
	public void setVelLimite(float velLimite) {
		this.velLimite = velLimite;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((lugar == null) ? 0 : lugar.hashCode());
		result = prime * result + responsable;
		result = prime * result + Float.floatToIntBits(velLimite);
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
		Conversorid other = (Conversorid) obj;
		if (id != other.id)
			return false;
		if (lugar == null) {
			if (other.lugar != null)
				return false;
		} else if (!lugar.equals(other.lugar))
			return false;
		if (responsable != other.responsable)
			return false;
		if (Float.floatToIntBits(velLimite) != Float.floatToIntBits(other.velLimite))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Conversorid [id=" + id + ", responsable=" + responsable + ", lugar=" + lugar + ", velLimite="
				+ velLimite + "]";
	}
	
	
}
