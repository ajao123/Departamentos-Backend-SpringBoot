package com.allissonjardel.departamentoBackend.model.enums;

public enum Sexo {
	
	MASCULINO(1),
	FEMININO(2);

	private Integer code;
	
	private Sexo(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}

	public static Sexo valueOf(Integer code) {
		for(Sexo value : Sexo.values()) {
			if(code == value.getCode()) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Sexo code");
	}
	
}
