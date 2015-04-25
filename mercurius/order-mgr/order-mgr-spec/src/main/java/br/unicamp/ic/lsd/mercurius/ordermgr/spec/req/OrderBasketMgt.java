package br.unicamp.ic.lsd.mercurius.ordermgr.spec.req;

import java.util.Set;

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.BasketItem;

public interface OrderBasketMgt {

	void cleanBasket();

	Set<BasketItem> getBasketItems(Basket basket);

}
