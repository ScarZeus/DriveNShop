package repo.imageRepo;

import java.util.List;

public interface ImageModelRepoInterface<T,ID> {

    List<T> getImages(Long productId);

    T getImageById(ID id);

    List<T> saveImages(List<T> id);


    void deleteImages(ID id);
}
