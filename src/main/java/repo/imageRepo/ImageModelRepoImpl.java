package repo.imageRepo;

import model.ImageModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ImageModelRepoImpl implements ImageModelRepoInterface<ImageModel,Long>{

    public ImageModel image=null;
    public List<ImageModel> images = null;

    @Autowired
    public SessionFactory factory;


    @Override
    public List<ImageModel> getImages(Long productId) {
        images=null;
        factory.inSession(session -> {
            String query="SELECT t FROM ImageModel t WHERE t.productID=:id";
            images=session.createQuery(query,ImageModel.class)
                    .setParameter("id",productId)
                    .getResultList();
        });
        return images;
    }

    @Override
    public ImageModel getImageById(Long imageId) {
        image=null;
        factory.inSession(session -> {
            String query = "SELECT t FROM ImageModel t WHERE t.id = :id";
           image= session.createQuery(query,ImageModel.class)
                   .setParameter("id",imageId)
                   .getSingleResult();
        });
        return image;
    }

    @Override
    public List<ImageModel> saveImages(List<ImageModel> imageCollection) {
        images=null;
        factory.inTransaction(session -> {
            for(ImageModel index: imageCollection) {
                session.persist(index);
                session.flush();
                images.add(index);
            }
        });
    return images;
    }


    @Override
    public void deleteImages(Long id) {
        factory.inTransaction(session -> {
            session.createQuery("DELETE FROM ImageModel t WHERE t.id = :id")
                    .setParameter("id",id)
                    .executeUpdate();
        });
    }
}