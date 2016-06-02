/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import beans.ProductBean;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.glassfish.samples.entities.*;

/**
 *
 * @author Vanessa
 */
@Named (value = "productControler")
@RequestScoped
public class ProductControler {
    
    @EJB
    ProductBean prod;
    
    List<Product> productList = new ArrayList<>();
    public List<Product> getProductList() {
        productList = prod.getProducts();
        return productList;
    }
    
    //public ProductControler(){
        Product novoProduto = new Product();
        Manufacturer mn = new Manufacturer();
        ProductCode pc = new ProductCode(); 
    //}

    public Product getNovoProduto() {
        return novoProduto;
    }

    public void setNovoProduto(Product novoProduto) {
        this.novoProduto = novoProduto;
    }

    public Manufacturer getMn() {
        return mn;
    }

    public void setMn(Manufacturer mn) {
        this.mn = mn;
    }

    public ProductCode getPc() {
        return pc;
    }

    public void setPc(ProductCode pc) {
        this.pc = pc;
    }

    
    public ProductBean getProd() {
        return prod;
    }

    public void setProd(ProductBean prod) {
        this.prod = prod;
    }
    
    public String addNewProduct() {
        novoProduto.setProductCode(pc);
        novoProduto.setManufacturerId(mn);

        prod.addProduct(novoProduto); // insere o produto na base de dados

        productList = prod.getProducts();
        return "listProducts.xhtml"; //devolve o nome da página que lista todos os produtos
    }



    
}


