package br.unicamp.ic.lsd.mercurius.ordermgr.spec.req;

public interface OrderProductMgt {

	boolean hasQuantityAvailable(String sku, int quantity);

	boolean productExists(Integer productId);

}
