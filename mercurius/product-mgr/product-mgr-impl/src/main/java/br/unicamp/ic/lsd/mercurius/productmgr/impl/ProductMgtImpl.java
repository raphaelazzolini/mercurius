package br.unicamp.ic.lsd.mercurius.productmgr.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ProductImage;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductManager;

class ProductMgtImpl {

	private final ProductManager manager;

	ProductMgtImpl(ProductManager manager) {
		super();
		this.manager = manager;
	}

	public Product getProductById(Integer productId) {
		Product product = manager.getProductDAO().findById(productId);
		return manager.getProductDAO().loadProductImages(product);
	}

	public Product loadCategories(Product product) {
		return manager.getProductDAO().loadCategories(product);
	}

	public Product saveProduct(Product product) {
		return manager.getProductDAO().merge(product);
	}

	public ProductQuantity getProductQuantity(Product product, List<ProductAttribute> attributes) {
		ProductQuantity productQuantity = manager.getProductDAO().getProductQuantity(product, attributes);
		productQuantity = manager.getProductQuantityDAO().loadAttributes(productQuantity);
		if (CollectionUtils.isNotEmpty(productQuantity.getProductImages())) {
			productQuantity.getProduct().setMainImage(productQuantity.getProductImages().get(0).getImagePath());
		} else {
			productQuantity.getProduct().setMainImage(StringUtils.EMPTY);
		}
		return productQuantity;
	}

	public ProductQuantity getProductQuantity(String sku) {
		ProductQuantity productQuantity = manager.getProductQuantityDAO().findById(sku);
		productQuantity = manager.getProductQuantityDAO().loadAttributes(productQuantity);
		if (CollectionUtils.isNotEmpty(productQuantity.getProductImages())) {
			productQuantity.getProduct().setMainImage(productQuantity.getProductImages().get(0).getImagePath());
		} else {
			productQuantity.getProduct().setMainImage(StringUtils.EMPTY);
		}
		return productQuantity;
	}

	public void saveProductImage(File targetDirectory, String imageName, byte[] imageBytes) throws IOException {
		File bigImagesFolder = new File(targetDirectory, "big");
		File mediumImagesFolder = new File(targetDirectory, "medium");
		File smallImagesFolder = new File(targetDirectory, "small");

		if (!bigImagesFolder.exists()) {
			bigImagesFolder.mkdirs();
		}
		if (!mediumImagesFolder.exists()) {
			mediumImagesFolder.mkdirs();
		}
		if (!smallImagesFolder.exists()) {
			smallImagesFolder.mkdirs();
		}

		processImage(imageName, imageBytes, bigImagesFolder, 1000, 1000, 1f);
		processImage(imageName, imageBytes, mediumImagesFolder, 500, 500, 1f);
		processImage(imageName, imageBytes, smallImagesFolder, 250, 250, 1f);
	}

	public void deleteImages(ProductImage image, File imagesDirectory) {
		File bigImagesFolder = new File(imagesDirectory, "big");
		File mediumImagesFolder = new File(imagesDirectory, "medium");
		File smallImagesFolder = new File(imagesDirectory, "small");

		File bigImage = new File(bigImagesFolder, image.getImagePath());
		File mediumImages = new File(mediumImagesFolder, image.getImagePath());
		File smallImages = new File(smallImagesFolder, image.getImagePath());

		bigImage.delete();
		mediumImages.delete();
		smallImages.delete();
	}

	private void processImage(String fileName, byte[] imageBytes, File targetFolder, int width, int height,
			float quality) throws IOException {
		ImageWriter imageWriter = getImageWriter(fileName);
		if (imageWriter != null) {
			File outFileImage = new File(targetFolder, fileName);
			InputStream imageInputStream = new ByteArrayInputStream(imageBytes);

			if (!outFileImage.exists()) {
				outFileImage.createNewFile();
			}

			ImageOutputStream out = ImageIO.createImageOutputStream(outFileImage);
			BufferedImage bufferedImage = ImageIO.read(imageInputStream);

			if (width > 0 && height > 0) {
				int type = bufferedImage.getType();
				bufferedImage = resizeImage(bufferedImage, type, width, height);
			}

			IIOImage image = new IIOImage(bufferedImage, null, null);
			ImageWriteParam imageParam = imageWriter.getDefaultWriteParam();

			imageParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			imageParam.setCompressionQuality(quality);

			imageWriter.setOutput(out);
			imageWriter.write(null, image, imageParam);

			out.close();
		}
	}

	private ImageWriter getImageWriter(String fileName) {
		String extension = FilenameUtils.getExtension(fileName);
		ImageWriter writer = null;
		Iterator<ImageWriter> iwi = ImageIO.getImageWritersByFormatName(extension);
		if (!iwi.hasNext()) {
			return null;
		}
		writer = iwi.next();
		return writer;
	}

	private BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height) {
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();

		return resizedImage;
	}

}
