/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.samples.entities.Product;

/**
 *
 * @author Vanessa
 */
@Stateless
public class ProductBean {

    @PersistenceContext
    EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<Product> getProducts () {
        return (List<Product>) em.createNamedQuery("Product.findAll").getResultList();
    }
    
    public Product addProduct(Product prd) {
        em.persist(prd);
        return prd;
    }


    
}
