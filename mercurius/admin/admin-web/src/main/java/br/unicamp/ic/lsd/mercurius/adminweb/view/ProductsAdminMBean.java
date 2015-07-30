package br.unicamp.ic.lsd.mercurius.adminweb.view;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;

import br.unicamp.ic.lsd.mercurius.admin.impl.AdminComponentFactory;
import br.unicamp.ic.lsd.mercurius.admin.spec.req.AdminProductMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Category;
import br.unicamp.ic.lsd.mercurius.datatype.Manufacturer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductImage;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.sed.cosmos.IManager;

@Named("productsAdminMBean")
@ViewScoped
public class ProductsAdminMBean implements Serializable {

	private static final long serialVersionUID = -9156481910419815456L;

	@Inject
	private AdminMainMBean adminMainMBean;

	private IManager adminManager;
	private AdminProductMgt productMgt;

	private boolean editing;
	private boolean cropImage;
	private boolean editingSku;
	private LazyDataModel<Product> products;
	private Product product;
	private ProductQuantity sku;
	private List<ProductQuantity> skusToDelete;
	private List<SelectItem> categories;
	private ProductImage image;
	private String tmpFileName;
	private CroppedImage croppedImage;
	private int rowIndex;
	private List<ProductImage> imagesDelete;
	private Integer selectedManufacturerer;
	private Collection<Integer> selectedCategories;

	@PostConstruct
	public void init() {
		adminManager = AdminComponentFactory.createInstance();
		productMgt = (AdminProductMgt) adminManager.getRequiredInterface("AdminProductMgt");

		loadProductsModel();
		limparImagem();
		selectedManufacturerer = null;
		selectedCategories = null;
	}

	public void newProduct() {
		product = productMgt.createNewProductInstance();
		categories = createCategoriesSelectItems(productMgt.getAllCategories());
		selectedManufacturerer = null;
		selectedCategories = new HashSet<>();
		editing = true;
	}

	public void edit() {
		categories = createCategoriesSelectItems(productMgt.getAllCategories());
		selectedManufacturerer = product.getManufacturer().getId();
		selectedCategories = new HashSet<>();
		for (Category category : product.getCategories()) {
			selectedCategories.add(category.getId());
		}

		editing = true;
	}

	public void cancelEdit() {
		product = null;
		imagesDelete = null;
		editing = false;
	}

	public void delete() {
		productMgt.removeProduct(product);
		loadProductsModel();
	}

	public void save() {
		if (CollectionUtils.isEmpty(product.getQuantities())) {
			addMessage("admin.products.no_categories", FacesMessage.SEVERITY_ERROR);
			return;
		}

		product.setManufacturer(productMgt.getManufacturerById(selectedManufacturerer));
		product.setCategories(productMgt.getCategoriesByIds(selectedCategories));

		Product savedProduct = productMgt.saveProduct(product);

		for (ProductQuantity sku : product.getQuantities()) {
			sku.setProduct(savedProduct);
			ProductQuantity savedSku = productMgt.saveProductQuantity(sku);
			for (ProductImage image : sku.getProductImages()) {
				image.setProductQuantity(savedSku);
				productMgt.saveProductImage(image);
			}
		}

		if (CollectionUtils.isNotEmpty(imagesDelete)) {
			for (ProductImage image : imagesDelete) {
				productMgt.deleteProductImage(image);
			}
		}

		if (CollectionUtils.isNotEmpty(skusToDelete)) {
			for (ProductQuantity sku : skusToDelete) {
				productMgt.deleteProductQuantity(sku);
			}
		}

		skusToDelete = null;
		imagesDelete = null;
		editing = false;
	}

	public void editSku() {
		editingSku = true;
	}

	public void deleteSku() {
		if (skusToDelete == null) {
			skusToDelete = new ArrayList<>();
		}

		product.getQuantities().remove(rowIndex);
		skusToDelete.add(sku);
	}

	public void newSku() {
		sku = productMgt.createNewProductQuantity();
		editingSku = true;
	}

	public void cancelEditSku() {
		editingSku = false;
	}

	public void saveSku() {
		if (CollectionUtils.isEmpty(sku.getProductImages())) {
			addMessage("admin.products.no_images", FacesMessage.SEVERITY_ERROR);
			return;
		}

		if (!product.getQuantities().contains(sku)) {
			product.getQuantities().add(sku);
		}

		editingSku = false;
	}

	public void deleteImage() {
		if (imagesDelete == null) {
			imagesDelete = new ArrayList<>();
		}
		imagesDelete.add(image);
		sku.getProductImages().remove(rowIndex);
	}

	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile uploadedFile = event.getFile();
		String uploadedFileName = uploadedFile.getFileName();
		StringBuilder tmpFileNameBuilder = new StringBuilder();

		tmpFileNameBuilder.append(System.currentTimeMillis());
		tmpFileNameBuilder.append(".");
		tmpFileNameBuilder.append(FilenameUtils.getExtension(uploadedFileName));
		tmpFileName = tmpFileNameBuilder.toString();
		String tmpFilePath = getTmpFilePath();

		try {
			uploadedFile.write(tmpFilePath);
			cropImage = true;
			editing = false;
			editingSku = false;
		} catch (Exception e) {
			// logger.error("Erro ao salvar a imagem", e);
			// addMessage("admin.produtos.erro_upload",
			// FacesMessage.SEVERITY_ERROR);
		}
	}

	public void cancelarCortarImagem() {
		tmpFileName = null;
		cropImage = false;
		editing = true;
		editingSku = true;
	}

	public String getCategoriasString(Product produto) {
		StringBuilder categoriasBuilder = new StringBuilder();
		List<Category> categorias = produto.getCategories();

		for (int i = 0; i < categorias.size(); i++) {
			if (i > 0) {
				categoriasBuilder.append(", ");
			}
			categoriasBuilder.append(categorias.get(i).getName());
		}

		return categoriasBuilder.toString();
	}

	public void saveImage() {
		try {
			ProductImage image = productMgt.saveProductImage(tmpFileName, croppedImage.getBytes());
			sku.getProductImages().add(image);
			editing = true;
			editingSku = true;
			cropImage = false;
		} catch (IOException e) {
			// logger.error("Erro ao salvar imagem", e);
			addMessage("admin.products.error_upload", FacesMessage.SEVERITY_ERROR);
		} finally {
			File tmpFile = new File(getTmpFilePath());
			tmpFile.delete();
		}

		limparImagem();
	}

	public List<SelectItem> getManufacturerers() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Manufacturer manufacturer : productMgt.getManufacturers()) {
			manufacturer.setProducts(null);
			SelectItem item = new SelectItem(manufacturer.getId(), manufacturer.getName());
			items.add(item);
		}
		return items;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public LazyDataModel<Product> getProducts() {
		return products;
	}

	public void setProducts(LazyDataModel<Product> products) {
		this.products = products;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<SelectItem> getCategories() {
		return categories;
	}

	public void setCategories(List<SelectItem> categories) {
		this.categories = categories;
	}

	public ProductImage getImage() {
		return image;
	}

	public void setImagem(ProductImage image) {
		this.image = image;
	}

	public boolean isCropImage() {
		return cropImage;
	}

	public void setCropImage(boolean cropImage) {
		this.cropImage = cropImage;
	}

	public String getTmpFileName() {
		return tmpFileName;
	}

	public void setTmpFileName(String tmpFileName) {
		this.tmpFileName = tmpFileName;
	}

	public CroppedImage getCroppedImage() {
		return croppedImage;
	}

	public void setCroppedImage(CroppedImage croppedImage) {
		this.croppedImage = croppedImage;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public boolean isEditingSku() {
		return editingSku;
	}

	public void setEditingSku(boolean editingSku) {
		this.editingSku = editingSku;
	}

	public ProductQuantity getSku() {
		return sku;
	}

	public void setSku(ProductQuantity sku) {
		this.sku = sku;
	}

	public void setImage(ProductImage image) {
		this.image = image;
	}

	public Integer getSelectedManufacturerer() {
		return selectedManufacturerer;
	}

	public void setSelectedManufacturerer(Integer selectedManufacturerer) {
		this.selectedManufacturerer = selectedManufacturerer;
	}

	public Collection<Integer> getSelectedCategories() {
		return selectedCategories;
	}

	public void setSelectedCategories(Collection<Integer> selectedCategories) {
		this.selectedCategories = selectedCategories;
	}

	private void loadProductsModel() {
		products = new LazyDataModel<Product>() {
			private static final long serialVersionUID = 2895456264073910183L;

			@Override
			public List<Product> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				return getProducts(first, pageSize);
			}

			@Override
			public List<Product> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
				return getProducts(first, pageSize);
			}

			private List<Product> getProducts(int first, int pageSize) {
				List<Product> products = new ArrayList<>();

				for (Product product : productMgt.getProductsList(first, pageSize)) {
					product = productMgt.loadCategories(product);
					products.add(product);
				}

				return products;
			}

		};

		int produtosCount = productMgt.getTotalProducts();
		products.setRowCount(produtosCount);
	}

	private void limparImagem() {
		croppedImage = null;
		tmpFileName = null;
		cropImage = false;
	}

	private String getTmpFilePath() {
		StringBuilder tmpFilePathBuilder = new StringBuilder();
		tmpFilePathBuilder.append(adminMainMBean.getImagesDirectory());
		tmpFilePathBuilder.append("tmp/");
		tmpFilePathBuilder.append(tmpFileName);
		return tmpFilePathBuilder.toString();
	}

	private List<SelectItem> createCategoriesSelectItems(List<Category> categories) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Category category : categories) {
			SelectItem item = new SelectItem(category.getId(), category.getName());
			items.add(item);
		}
		return items;
	}

	private void addMessage(String key, FacesMessage.Severity severity) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
		context.addMessage(null, new FacesMessage(severity, bundle.getString(key), StringUtils.EMPTY));
	}

}
