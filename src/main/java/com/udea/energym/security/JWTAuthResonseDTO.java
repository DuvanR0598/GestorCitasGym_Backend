package com.udea.energym.security;

public class JWTAuthResonseDTO {

	private String token;
	private String tipoDeToken = "Bearer";

	public JWTAuthResonseDTO(String token) {
		super();
		this.token = token;
	}

	public JWTAuthResonseDTO(String token, String tipoDeToken) {
		super();
		this.token = token;
		this.tipoDeToken = tipoDeToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String tokenDeAcceso) {
		this.token = tokenDeAcceso;
	}

	public String getTipoDeToken() {
		return tipoDeToken;
	}

	public void setTipoDeToken(String tipoDeToken) {
		this.tipoDeToken = tipoDeToken;
	}
}
