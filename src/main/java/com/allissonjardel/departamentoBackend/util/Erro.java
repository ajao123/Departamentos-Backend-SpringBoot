package com.allissonjardel.departamentoBackend.util;

public class Erro{
	private String messageUser;
	private String messageDeveloper;
	
	public Erro(String messageUser, String messageDeveloper) {
		this.messageUser = messageUser;
		this.messageDeveloper = messageDeveloper;
	}

	public String getMessageUser() {
		return messageUser;
	}

	public String getMessageDeveloper() {
		return messageDeveloper;
	}

	@Override
	public String toString() {
		return "Erro [messageUser=" + messageUser + ", messageDeveloper=" + messageDeveloper + "]";
	}		
	
}
