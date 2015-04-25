package br.unicamp.ic.lsd.mercurius.persistence.dao;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.Promotion;

public interface PromotionDAO extends DAO<Promotion, Integer> {

	List<Promotion> getPromotions(Customer customer);

}
