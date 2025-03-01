package repo.productRepo;

import model.ProductModel;

import java.util.List;


public interface ProductModelRepoInterface<T , ID> {
    T save(T productEntity);

    T findById(ID id);

    void updateProduct(T entity);

    void deleteById(T product);

    List<T> findAllByName(String name);

    public List<ProductModel> getAllByCategory(String category);

    void updateAll(List<ProductModel> updatedproduct);

}