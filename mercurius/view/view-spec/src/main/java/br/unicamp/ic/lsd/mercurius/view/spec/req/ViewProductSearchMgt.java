package br.unicamp.ic.lsd.mercurius.view.spec.req;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Product;

public interface ViewProductSearchMgt {

	List<Product> searchByText(String text);

}
