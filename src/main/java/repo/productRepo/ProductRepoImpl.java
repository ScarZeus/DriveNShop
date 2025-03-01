package repo.productRepo;


import model.ProductModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepoImpl implements ProductModelRepoInterface<ProductModel,Long>{
    ProductModel products;
    List<ProductModel> productsList;

    @Autowired
    public SessionFactory factory;

    @Override
    public List<ProductModel> getAllByCategory(String category) {
        productsList= null;
        factory.inSession(session -> {
            String query="SELECT t FROM ProductModel t WHERE t.category = :category";
            productsList = session.createSelectionQuery(query, ProductModel.class)
                    .setParameter("category", category)
                    .getResultList();
        });
        return productsList;
    }

    @Override
    public void updateAll(List<ProductModel> updatedProduct) {
        factory.inTransaction(session -> {
            int batchSize = 20;
            for (int i = 0; i < updatedProduct.size(); i++) {
                ProductModel product = updatedProduct.get(i);
                session.createQuery("UPDATE ProductModel p SET p.stock = :stock WHERE p.id = :id")
                        .setParameter("stock", product.getStock())
                        .setParameter("id", product.getId())
                        .executeUpdate();
                if(i % batchSize == 0){
                    session.flush();
                    session.clear();
                }
            }
        });
    }



    @Override
    public ProductModel save(ProductModel productEntity) {
        factory.inTransaction(session -> {
            System.out.println("Before Storing");
            session.persist(productEntity);
            System.out.println("Successfull");
        });
        return productEntity;
    }

    @Override
    public ProductModel findById(Long id) {
        products=null;
        factory.inSession(session -> {
            String query = "SELECT t FROM ProductModel t WHERE t.id = :id ";
            products= session.createSelectionQuery(query, ProductModel.class)
                    .setParameter("id",id)
                    .getSingleResult();
        });
        return products;
    }



    @Override
    public void updateProduct(ProductModel newProductUpdate) {
        factory.inTransaction(session -> {
            session.remove(newProductUpdate);
            session.persist(newProductUpdate);
        });
    }


    @Override
    public void deleteById(ProductModel product) {
        factory.inTransaction(session -> {
            session.remove(products);
        });
    }

    @Override
    public List<ProductModel> findAllByName(String name) {
        productsList=null;
        String query="SELECT * FROM products p " +
                "ORDER BY " +
                "CASE " +
                "    WHEN p.product_name = :productName THEN 0 " +
                "    WHEN p.product_name LIKE :pattern THEN 1 " +
                "    ELSE 2 " +
                "END, " +
                "p.product_name ASC";
        factory.inSession(session -> {
            productsList= session.createNativeQuery(query,ProductModel.class)
                    .setParameter("productName",name)
                    .setParameter("pattern","%"+name+"%")
                    .getResultList();
        });
        return productsList;
    }


}
