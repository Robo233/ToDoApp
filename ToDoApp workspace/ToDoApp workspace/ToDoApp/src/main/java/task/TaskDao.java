package task;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import dao.GenericDao;
import user.User;

public class TaskDao extends GenericDao<Task> {

	private EntityManagerFactory factory;

	public TaskDao(EntityManagerFactory factory) {
		super(Task.class);
		this.factory = factory;
	}

	@Override
	public EntityManager getEntityManager() {
		try {
			return factory.createEntityManager();
		} catch (Exception ex) {
			System.out.println("The entity manager cannot be created!");
			return null;
		}
	}
	
	public List<User> find(String name) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> q = cb.createQuery(User.class);

		Root<User> c = q.from(User.class);
		ParameterExpression<String> paramName = cb.parameter(String.class);
		q.select(c).where(cb.equal(c.get("username"), paramName));
		TypedQuery<User> query = em.createQuery(q);
		query.setParameter(paramName, name);

		List<User> results = query.getResultList();
		return results;
	}
	
	public void delete(Task taskToDelete) {
	    EntityManager entityManager = factory.createEntityManager();
	    entityManager.getTransaction().begin();
	    taskToDelete = entityManager.contains(taskToDelete) ? taskToDelete : entityManager.merge(taskToDelete);
	    entityManager.remove(taskToDelete);
	    entityManager.getTransaction().commit();
	    entityManager.close();
	}
	
	public void updateTask(Task taskToUpdate) {
	    EntityManager entityManager = factory.createEntityManager();
	    entityManager.getTransaction().begin();
	    entityManager.merge(taskToUpdate);
	    entityManager.getTransaction().commit();
	    entityManager.close();
	}


}
