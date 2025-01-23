package repo.userRepo;


import java.util.List;

public interface UserModelRepoInterface <T , ID>{
    T save(T userEntity);
    T findByEmailId(String id);
    void  updateById(T userEntity);
    List<T> getAllEmployees();
    void deleteByEmailId(String email);
}
