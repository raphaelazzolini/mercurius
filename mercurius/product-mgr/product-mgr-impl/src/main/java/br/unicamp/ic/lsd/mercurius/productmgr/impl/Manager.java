package br.unicamp.ic.lsd.mercurius.productmgr.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.unicamp.ic.lsd.mercurius.categorymgr.impl.CategoryMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ConfigurationDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ManufactoryDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductImageDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductQuantityDAO;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductCategoryMgt;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductManager;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductMgt;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.req.ProductPromotionMgt;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.req.ProductRecommendedProductsMgt;
import br.unicamp.ic.lsd.mercurius.productpromotionconnector.ProductPromotionComponentFactory;
import br.unicamp.ic.lsd.mercurius.recommendedproductsconnector.ProductRecommendedProductsComponentFactory;
import br.unicamp.ic.sed.cosmos.AManagerComposite;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManagerComposite implements ProductManager {

	private static final String CATEGORY_MGT = "CategoryMgt";
	private static final String CATEGORY_MANAGER = "CategoryManager";
	private static final String PROMOTION_MGT = "PromotionMgt";
	private static final String I_MANAGER = "IManager";
	private static final String PRODUCT_MGT = "ProductMgt";

	private ProductDAO productDAO;
	private ProductQuantityDAO productQuantityDAO;
	private ProductImageDAO productImageDAO;
	private ManufactoryDAO manufactoryDAO;
	private ConfigurationDAO configurationDAO;

	Manager() {
		super();
		try {
			Context context = new InitialContext();
			productDAO = (ProductDAO) context.lookup("java:app/persistence/productDAO");
			productQuantityDAO = (ProductQuantityDAO) context.lookup("java:app/persistence/productQuantityDAO");
			productImageDAO = (ProductImageDAO) context.lookup("java:app/persistence/productImageDAO");
			manufactoryDAO = (ManufactoryDAO) context.lookup("java:app/persistence/manufactoryDAO");
			configurationDAO = (ConfigurationDAO) context.lookup("java:app/persistence/configurationDAO");

			setProvidedInterface(PRODUCT_MGT, new ProductFacade(this));
			setProvidedInterface(I_MANAGER, this);
			setProvidedInterfaceType(PRODUCT_MGT, ProductMgt.class);
			setProvidedInterfaceType(I_MANAGER, IManager.class);
			
			IManager promotionManager = ProductPromotionComponentFactory.createInstance();
			setRequiredInterfaceType("ProductPromotionMgt", ProductPromotionMgt.class);
			setRequiredInterface("ProductPromotionMgt", promotionManager.getProvidedInterface("ProductPromotionMgt"));
			
			IManager recommendedProductsManager = ProductRecommendedProductsComponentFactory.createInstance();
			setRequiredInterfaceType("ProductRecommendedProductsMgt", ProductRecommendedProductsMgt.class);
			setRequiredInterface("ProductRecommendedProductsMgt",recommendedProductsManager.getProvidedInterface("ProductRecommendedProductsMgt"));
			
			setInternalComponent(CATEGORY_MANAGER, CategoryMgrComponentFactory.createInstance());
			setProvidedInterface(CATEGORY_MGT, new CategoryFacade(this));
			setProvidedInterfaceType(CATEGORY_MGT, ProductCategoryMgt.class);
		} catch (NamingException e) {
		}
	}

	@Override
	public ProductDAO getProductDAO() {
		return productDAO;
	}

	@Override
	public ProductQuantityDAO getProductQuantityDAO() {
		return productQuantityDAO;
	}

	@Override
	public ProductImageDAO getProductImageDAO() {
		return productImageDAO;
	}

	@Override
	public ManufactoryDAO getManufactoryDAO() {
		return manufactoryDAO;
	}

	@Override
	public ConfigurationDAO getConfigurationDAO() {
		return configurationDAO;
	}

}
