import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DAOGenerico<T> {

    private static final EntityManagerFactory emFactory;
    private EntityManager entityManager;

    static {
        emFactory = Persistence.createEntityManagerFactory("bonusVendasPU");
    }

    public DAOGenerico() {
        this.entityManager = emFactory.createEntityManager();
    }

    public void salvar(T entidade) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entidade);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }

    public void atualizar(T entidade) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entidade);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }

    public T buscarPorId(Class<T> classe, Long id) {
        return entityManager.find(classe, id);
    }

    public List<T> listarTodos(Class<T> classe) {
        CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        return entityManager.createQuery(query).getResultList();
    }

    public void deletar(T entidade) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(entidade) ? entidade : entityManager.merge(entidade));
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }

    public void fechar() {
        entityManager.close();
    }
}
