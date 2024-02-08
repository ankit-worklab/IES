package org.ies.repository;

import org.ies.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    //<T> List<T> findAll(Class<T> classType);

    <T> T findByUserId(Integer id,Class<T> classType);

    @Modifying
    @Query("update UserEntity as u set u.status= :status where u.userId= :userId")
    Integer updateAccountStatus(@Param("userId") Integer id, @Param("status") String status);

    UserEntity findByUserIdAndPasswordLike(int userId, String password);

    UserEntity findByEmailId(String email);

}
