package repo.userRepo;

import model.UserModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public class UserModelRepoImpl implements UserModelRepoInterface<UserModel,Long>{
    UserModel user;
    List<UserModel> users;
    List<UserModel> employees;

    @Autowired
    public SessionFactory factory;


    @Override
    public UserModel save(UserModel userEntity) {
        user=null;
        try {
            factory.inTransaction((session) ->{
                session.persist(userEntity);
                session.flush();
                user=userEntity;
            }
            );
        }
        catch (Exception e){
            throw new RuntimeException("persist Operation failed",e);
        }
        return user;
    }

    @Override
    public UserModel findByEmailId(String email) {
        user=null;
        factory.inSession(session -> {
            String query = "select u from UserModel u where u.email = :email";
            user=session.createQuery(query, UserModel.class)
                    .setParameter("email", email)
                    .getSingleResult();
        });
        return user;
    }


    @Override
    public void updateById(UserModel existingUser) {
        factory.inTransaction(session ->{
            String hql="UPDATE users u SET u.user_name= :name, u.password= :password, u.role = :role  WHERE u.email = :email";
            int affectedRows= session.createQuery(hql)
                    .setParameter("name",existingUser.getUser_name())
                    .setParameter("password",existingUser.getPassword())
                    .setParameter("role",existingUser.getRole())
                    .setParameter("email",existingUser.getEmail())
                    .executeUpdate();
        });
    }

    @Override
    public List<UserModel> getAllEmployees() {
        employees=null;
        factory.inSession(session ->{
            String query = "SELECT u FROM users u WHERE u.role = 'EMPLOYEE'";
            employees=session.createQuery(query, UserModel.class).getResultList();
        });
        return employees;
    }

    @Override
    public void deleteByEmailId(String email) {
        factory.inTransaction(session -> {
            String query="DELETE FROM users u WHERE u.email = :email";
            int affectedRows= session.createQuery(query)
                    .setParameter("email",email)
                    .executeUpdate();
            if(affectedRows == 0){
                throw new RuntimeException("No user found");
            }
        });

    }
}
