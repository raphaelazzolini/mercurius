package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.BasketItem;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.factory.BasketItemFactory;

@Singleton(name = "basketItemFactory")
@Local(BasketItemFactory.class)
public class BasketItemFactoryImpl implements BasketItemFactory {

	private static final long serialVersionUID = -1084745393173408464L;

	private BasketItemFactoryImpl() {
		super();
	}

	@Override
	public BasketItem createInstance(Basket basket, String sku, Product product, List<ProductAttribute> attributes) {
		BasketItemImpl basketItem = new BasketItemImpl();
		BasketItemId id = new BasketItemId();
		id.setSku(sku);
		basketItem.setId(id);
		basketItem.setBasket((BasketImpl) basket);
		basketItem.setProduct((ProductImpl) product);
		basketItem.setProductAttributes(attributes);
		return basketItem;
	}

}
