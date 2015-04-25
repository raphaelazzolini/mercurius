package br.unicamp.ic.lsd.mercurius.ordermgr.spec.req;

import java.math.BigDecimal;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Address;
import br.unicamp.ic.lsd.mercurius.datatype.ShippingService;

public interface ShippingMgt {

	List<ShippingService> getOrderShippingServices(BigDecimal width, BigDecimal height, BigDecimal weight,
			Address deliveryAddress);

	boolean checkOrderArrived(String trackingNumber);

}
