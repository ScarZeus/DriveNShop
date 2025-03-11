package repo.purchaseRepo;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserPurchaseRepoInterface<T,ID> {

    List<T> findAllUserByPurchaseDate(Date date);
    T findUserByPurchaseId(ID id);
    T saveBill(T billEntity);
    void delete(ID billEntity);
    void deleteBeforeDate(Date date);
    void deleteAfterDate(Date date);
    void deleteCustom(Date from , Date to);
    T getBillById(ID billID);
    void update(T entity);
}
