package br.unicamp.ic.lsd.mercurius.orderbasketconnector;

import java.util.Set;

import br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov.BasketMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.BasketItem;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.OrderBasketMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class BasketMgtAdapter implements OrderBasketMgt {

	private static final String BASKET_MGT = "BasketMgt";

	private final IManager manager;
	private final BasketMgt basketMgt;

	BasketMgtAdapter(IManager manager) {
		super();
		this.manager = manager;
		basketMgt = (BasketMgt) this.manager.getRequiredInterface(BASKET_MGT);
	}

	@Override
	public void cleanBasket() {
		basketMgt.cleanBasket();
	}

	@Override
	public Set<BasketItem> getBasketItems(Basket basket) {
		basketMgt.loadBasketItems(basket);
		return basket.getBasketItems();
	}

}
