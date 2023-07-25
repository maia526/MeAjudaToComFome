package br.gov.finep.ToComFome.modelo;

import java.util.List;

public class Info {
	private int statuscode;
	private Copyright copyright;
	private List<String> messages;
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	public Copyright getCopyright() {
		return copyright;
	}
	public void setCopyright(Copyright copyright) {
		this.copyright = copyright;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	
}
