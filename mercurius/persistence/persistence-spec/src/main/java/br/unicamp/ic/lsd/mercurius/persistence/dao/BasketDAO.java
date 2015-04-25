package br.unicamp.ic.lsd.mercurius.persistence.dao;

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;

public interface BasketDAO extends DAO<Basket, Integer> {

	Basket getBasketFromCustomer(Customer customer);

	Basket getBasketFromSession(String sessionId);

	void loadBasketItems(Basket basket);

}
