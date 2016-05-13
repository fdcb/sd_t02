package askme;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "UserSessionEJB")
public class UserSessionBean {
    @PersistenceContext
    EntityManager entityManager;

    
}
