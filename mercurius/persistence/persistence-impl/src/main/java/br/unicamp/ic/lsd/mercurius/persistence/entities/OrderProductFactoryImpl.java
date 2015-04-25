package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.OrderProduct;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.factory.OrderProductFactory;

@Singleton(name = "orderProductFactory")
@Local(OrderProductFactory.class)
public class OrderProductFactoryImpl implements OrderProductFactory {

	private static final long serialVersionUID = 5671394998642361838L;

	private OrderProductFactoryImpl() {
		super();
	}

	@Override
	public OrderProduct createInstance(String sku, Product product, List<ProductAttribute> productAttributes) {
		OrderProductImpl orderProduct = new OrderProductImpl();
		OrderProductId id = new OrderProductId();
		id.setSku(sku);
		orderProduct.setId(id);
		orderProduct.setProduct(product);
		orderProduct.setProductAttributes(productAttributes);
		return orderProduct;
	}

}
