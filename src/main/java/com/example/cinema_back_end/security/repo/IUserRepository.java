package com.example.cinema_back_end.security.repo;

import com.example.cinema_back_end.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author tritcse00526x
 */
@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Query("UPDATE User u " +
            "SET u.fullName = :fullname, u.phone = :phone " +
            "WHERE u.id = (:id)")
    void updateInfo(@Param("id") Integer id,
                    @Param("fullname") String fullname,
                    @Param("phone") String phone);
    @Modifying
    @Query("UPDATE User u " +
            "SET u.password = :password " +
            "WHERE u.id = (:id)")
    void changePassword(@Param("id") Integer id,@Param("password") String password);
    @Modifying
    @Query(nativeQuery = true,
            value= "DELETE FROM users_roles t " +
                    "WHERE t.user_id = (:id)")
    void deleteUserTableJoin(@Param("id") Integer id);

}

