package br.unicamp.ic.lsd.mercurius.persistence.dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@Transactional
public abstract class DomainTestContext {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackages(true, "br.unicamp.ic.lsd.mercurius.persistence",
						"br.unicamp.ic.lsd.mercurius.datatype")
				.addAsManifestResource("META-INF/test-persistence.xml", ArchivePaths.create("persistence.xml"));
	}

	@PersistenceContext(unitName = "submarinoPU")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public UserTransaction getUtx() {
		return utx;
	}

	public void setUtx(UserTransaction utx) {
		this.utx = utx;
	}

	protected void truncateTables(String... tables) {
		try {
			utx.begin();
			for (String table : tables) {
				StringBuilder deleteQueryBuilder = new StringBuilder("delete from ");
				deleteQueryBuilder.append(table);
				getEm().createQuery(deleteQueryBuilder.toString()).executeUpdate();
			}
			utx.commit();
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException
				| RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			try {
				utx.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {

			}
		}
	}

}
