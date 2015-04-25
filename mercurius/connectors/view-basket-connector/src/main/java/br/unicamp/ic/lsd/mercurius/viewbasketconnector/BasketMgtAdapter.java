package br.unicamp.ic.lsd.mercurius.viewbasketconnector;

import br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov.BasketMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewBasketMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class BasketMgtAdapter implements ViewBasketMgt {

	private final IManager manager;
	private BasketMgt basketMgt;

	BasketMgtAdapter(IManager manager) {
		super();
		this.manager = manager;
		basketMgt = (BasketMgt) this.manager.getRequiredInterface("BasketMgt");
	}

	@Override
	public Basket getBasket(Customer customer) {
		return basketMgt.getBasket(customer);
	}

	@Override
	public Basket newBasket() {
		return basketMgt.newBasket();
	}

	@Override
	public Basket addProduct(Basket basket, ProductQuantity productQuantity) {
		return basketMgt.addProduct(basket, productQuantity);
	}

}
