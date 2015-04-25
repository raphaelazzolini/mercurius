package br.unicamp.ic.lsd.mercurius.view.spec.req;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Category;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.ProductNotFoundException;

public interface ViewProductMgt {

	Product getProduct(Integer productId) throws ProductNotFoundException;

	List<Category> getCategoryParents(Category category);

}
