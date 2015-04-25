package br.unicamp.ic.lsd.mercurius.view.spec.entities;

public interface CreditCardInformation {

	public String getBandeira();

	public void setBandeira(String bandeira);

	public String getNome();

	public void setNome(String nome);

	public String getNumeroCartao();

	public void setNumeroCartao(String numeroCartao);

	public String getValidadeMes();

	public void setValidadeMes(String validadeMes);

	public String getValidadeAno();

	public void setValidadeAno(String validadeAno);

	public String getCodigoSeguranca();

	public void setCodigoSeguranca(String codigoSeguranca);

	public String toJson();

}
