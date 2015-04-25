package br.unicamp.ic.lsd.mercurius.persistence.dao;

import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

public interface ProductQuantityDAO extends DAO<ProductQuantity, String> {

	ProductQuantity loadAttributes(ProductQuantity productQuantity);

}
