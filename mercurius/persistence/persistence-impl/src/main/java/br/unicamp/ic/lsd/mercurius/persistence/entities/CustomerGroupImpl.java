package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.unicamp.ic.lsd.mercurius.datatype.CustomerGroup;

@Entity(name = "CustomerGroup")
@Table(name = "customer_group")
public class CustomerGroupImpl implements CustomerGroup {

	private static final long serialVersionUID = -1773989937244944553L;

	protected CustomerGroupImpl() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_group_id")
	private Integer id;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
