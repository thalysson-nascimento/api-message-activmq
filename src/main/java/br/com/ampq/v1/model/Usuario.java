package br.com.ampq.v1.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Usuario implements Serializable{

	private Integer id;
	private String remetente;
	private String destinatario;
	private String message;
	private LocalDate data_message = LocalDate.now();
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRemetente() {
		return remetente;
	}
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDate getData_message() {
		return data_message;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", remetente=" + remetente + ", destinatario=" + destinatario + ", message="
				+ message + ", data_message=" + data_message + "]";
	}
	
}
