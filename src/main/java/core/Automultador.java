package core;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Automultador {
	private int idArduino, fuente;
	private float velocidad;
	private long fecha;
	public Automultador(int idArduino, float velocidad, long fecha, int fuente) {
		super();
		this.idArduino = idArduino;
		this.velocidad = velocidad;
		this.fecha = fecha;
		this.fuente = fuente;
	}
	
	public int getIdArduino() {
		return idArduino;
	}
	public void setIdArduino(int idArduino) {
		this.idArduino = idArduino;
	}
	public int getFuente() {
		return fuente;
	}
	public LocalDateTime getFechaReal() {
		return Instant.ofEpochMilli(fecha).atZone(ZoneId.of("UTC")).toLocalDateTime();
	}
	public void setFuente(int fuente) {
		this.fuente = fuente;
	}
	public float getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(float velocidad) {
		this.velocidad = velocidad;
	}
	public long getFecha() {
		return fecha;
	}
	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (fecha ^ (fecha >>> 32));
		result = prime * result + fuente;
		result = prime * result + idArduino;
		result = prime * result + Float.floatToIntBits(velocidad);
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
		Automultador other = (Automultador) obj;
		if (fecha != other.fecha)
			return false;
		if (fuente != other.fuente)
			return false;
		if (idArduino != other.idArduino)
			return false;
		if (Float.floatToIntBits(velocidad) != Float.floatToIntBits(other.velocidad))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Automultador [idArduino=" + idArduino + ", fuente=" + fuente + ", velocidad=" + velocidad + ", fecha="
				+ fecha + "]";
	}
}
