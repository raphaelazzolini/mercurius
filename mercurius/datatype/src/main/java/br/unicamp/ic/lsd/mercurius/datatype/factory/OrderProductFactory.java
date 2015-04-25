package br.unicamp.ic.lsd.mercurius.datatype.factory;

import java.io.Serializable;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.OrderProduct;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;

public interface OrderProductFactory extends Serializable {

	OrderProduct createInstance(String sku, Product product, List<ProductAttribute> productAttributes);

}
