package br.unicamp.ic.lsd.mercurius.vieworderconnector;

import br.unicamp.ic.lsd.mercurius.view.spec.entities.CreditCardInformation;

import com.google.gson.Gson;

public class CreditCardInformationImpl implements CreditCardInformation {

	private String bandeira;
	private String nome;
	private String numeroCartao;
	private String validadeMes;
	private String validadeAno;
	private String codigoSeguranca;

	CreditCardInformationImpl() {
		super();
	}

	@Override
	public String getBandeira() {
		return bandeira;
	}

	@Override
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getNumeroCartao() {
		return numeroCartao;
	}

	@Override
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	@Override
	public String getValidadeMes() {
		return validadeMes;
	}

	@Override
	public void setValidadeMes(String validadeMes) {
		this.validadeMes = validadeMes;
	}

	@Override
	public String getValidadeAno() {
		return validadeAno;
	}

	@Override
	public void setValidadeAno(String validadeAno) {
		this.validadeAno = validadeAno;
	}

	@Override
	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	@Override
	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	@Override
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
