package br.unicamp.ic.lsd.mercurius.view.spec.req;

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

public interface ViewBasketMgt {

	Basket getBasket(Customer customer);

	Basket newBasket();

	Basket addProduct(Basket basket, ProductQuantity productQuantity);

}
