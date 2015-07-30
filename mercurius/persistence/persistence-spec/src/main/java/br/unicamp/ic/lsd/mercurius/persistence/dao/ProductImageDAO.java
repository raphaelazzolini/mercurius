package br.unicamp.ic.lsd.mercurius.persistence.dao;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.ProductImage;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

public interface ProductImageDAO extends DAO<ProductImage, Integer> {

	List<ProductImage> getImagesFromProductQuantity(ProductQuantity productQuantity);

}
