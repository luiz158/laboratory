package br.gov.serpro.catalogo.entity;


public enum FaseEnum {

	ANALISE, PROSPECCAO, INTERNALIZACAO, SUSTENTACAO, DECLINIO;
	
	public static FaseEnum getFase(int fase){
		if(fase>0){
			return values()[fase-1];
		}
		
		return null;
	}
	
	
}
