package br.unicamp.ic.lsd.mercurius.basketmgr.spec.req;

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;

public interface SaveBasketMgt {

	/**
	 * Saves the customer's {@link Basket}
	 * 
	 * @param basket
	 *            the {@link Basket} to be saved
	 * @param customer
	 *            the {@link Customer} that owns the {@link Basket} to be saved
	 * @return the saved {@link Basket}
	 */
	Basket saveBasket(Basket basket, Customer customer);

	/**
	 * Loads the {@link Basket} from the informed {@link Customer}.
	 * 
	 * @param customer
	 *            the {@link Customer} that owns the {@link Basket}
	 * @return
	 */
	Basket loadBasket(Customer customer);

}
