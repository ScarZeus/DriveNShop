package repo.purchaseRepo;

import model.PurchaseModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public class UserPurchaseRepoImpl implements UserPurchaseRepoInterface<PurchaseModel,Long>{
    List<PurchaseModel> purchasesList;
    PurchaseModel purchasedProducts;

    @Autowired
    public SessionFactory factory;

    @Override
    public List<PurchaseModel> findAllUserByPurchaseDate(Date date) {
        purchasesList=null;
        factory.inSession(session -> {
            String query="SELECT t from PurchaseModel t WHERE t.purchase_date=: date";
            purchasesList=session.createSelectionQuery(query,PurchaseModel.class)
                    .setParameter("date",date)
                    .getResultList();
        });
        return purchasesList;
    }

    @Override
    public PurchaseModel findUserByPurchaseId(Long id) {
        purchasedProducts=null;
        factory.inSession(session -> {
             String query="SELECT t from PurchaseModel t WHERE t.id = :id";
            purchasedProducts=session.createSelectionQuery(query, PurchaseModel.class)
                    .setParameter("id",id)
                    .getSingleResult();
        });
        return purchasedProducts;
    }


    @Override
    public PurchaseModel saveBill(PurchaseModel billEntity) {
        try{
            factory.inTransaction(session -> {
                        session.persist(billEntity);

                    }
            );
        }
        catch (Exception e){
            throw new RuntimeException("bill Entity Persist failed:"+e.toString());
        }
        return billEntity;
    }

    @Override
    public void delete(Long billEntity) {

        factory.inTransaction(session -> {
            String query="DELETE t from PurchaseModel t WHERE t.id = :id";
            session.createQuery(query, PurchaseModel.class)
                    .setParameter("billId",billEntity)
                    .executeUpdate();
        });

    }

    @Override
    public void deleteBeforeDate(Date date) {
        factory.inTransaction(session -> {
            String query="DELETE t from PurchaseModel t WHERE t.purchase_date < :date";
            session.createQuery(query, PurchaseModel.class)
                    .setParameter("date",date)
                    .executeUpdate();
        });
    }

    @Override
    public void deleteAfterDate(Date date) {
        factory.inTransaction(session -> {
            String query="DELETE t from PurchaseModel t WHERE t.purchase_date > :date";
            session.createQuery(query, PurchaseModel.class)
                    .setParameter("date",date)
                    .executeUpdate();
        });

    }

    @Override
    public void deleteCustom(Date from, Date to) {
     factory.inTransaction(session -> {
         String query="FROM PurchaseModel t WHERE t.purchase_id >= :from AND t.purchase_date <= :to";
         List<PurchaseModel> purchaseDelete = session.createSelectionQuery(query,PurchaseModel.class)
                 .setParameter("from",from)
                 .setParameter("to",to)
                 .getResultList();
         for(PurchaseModel purchased:purchaseDelete){
             session.remove(purchased);
         }
     });
    }

    @Override
    public PurchaseModel getBillById(Long id) {
        purchasedProducts=null;
        factory.inSession(session -> {
            String query = "SELECT t FROM PurchaseModel";
             purchasedProducts = session.createSelectionQuery(query, PurchaseModel.class).getSingleResult();
        });
        return purchasedProducts;
    }

    @Override
    public void update(PurchaseModel entity) {
        factory.inTransaction(session -> {
            session.remove(entity);
            session.remove(entity);
        });
    }

}
