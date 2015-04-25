package br.unicamp.ic.lsd.mercurius.datatype.factory;

import java.io.Serializable;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.BasketItem;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;

public interface BasketItemFactory extends Serializable {

	BasketItem createInstance(Basket basket, String sku, Product product, List<ProductAttribute> productAttributes);

}
