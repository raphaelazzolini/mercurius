package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import br.unicamp.ic.lsd.mercurius.datatype.Manufacturer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;

import com.google.common.base.Objects;

@Entity(name = "Manufacturer")
@Table(name = "manufacturer")
public class ManufacturerImpl implements Manufacturer {

	private static final long serialVersionUID = -1919700266701089943L;

	protected ManufacturerImpl() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "manufacturer_id")
	private Integer id;

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Analyzer(definition = "productalyzer")
	@Column(name = "manufacturer_name", nullable = false)
	private String name;

	@Column(name = "manufacturer_url")
	private String url;

	@OneToMany(mappedBy = "manufacturer", targetEntity = ProductImpl.class)
	private List<Product> products;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public List<Product> getProducts() {
		List<Product> productsList = new ArrayList<Product>();
		productsList.addAll(products);
		return productsList;
	}

	@Override
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ManufacturerImpl) {
			ManufacturerImpl manufacturer = (ManufacturerImpl) obj;
			return Objects.equal(manufacturer.getId(), id);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ManufacturerImpl [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}

}
