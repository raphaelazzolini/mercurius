package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.unicamp.ic.lsd.mercurius.datatype.Address;
import br.unicamp.ic.lsd.mercurius.datatype.factory.AddressFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.AddressDAO;

@Stateless(name = "addressDAO")
@Local(AddressDAO.class)
public class AddressDAOImpl extends AbstractDAO<Address, Integer> implements AddressDAO {

	private static final long serialVersionUID = -153936162642374657L;

	@EJB
	private AddressFactory addressFactory;

	private static Class<? extends Address> entityClass;

	@Override
	public Address newInstance() {
		return addressFactory.createInstance();
	}

	@Override
	protected Class<? extends Address> getEntityClass() {
		if (entityClass == null) {
			Address entity = addressFactory.createInstance();
			entityClass = entity.getClass();
		}
		return entityClass;
	}

}
