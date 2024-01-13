package com.example.hrSystem.repository;

import com.example.hrSystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "Select u FROM User u LEFT JOIN FETCH u.roles WHERE u.username= :username ")
    Optional<User> findByUsernameWithRoles(@Param("username") String username);

    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id= :id")
    Optional<User> findByIdWithRoles(@Param("id") Integer id);

    @Query(value = "Select u FROM User u WHERE u.username= :username ")
    Optional<User> findByUsername(@Param("username") String username);

    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.roles ur " +
            " WHERE (:username IS NULL OR u.username LIKE %:username%) " +
            " AND (:arabicName IS NULL OR u.arabicName LIKE %:arabicName%) " +
            " AND (:roleCode IS NULL OR ur.code LIKE %:roleCode%) " +
            " ORDER BY u.id DESC",
            countQuery = "SELECT COUNT(u) FROM User u LEFT JOIN u.roles ur" +
                    " WHERE (:username IS NULL OR u.username LIKE %:username%) " +
                    " AND (:arabicName IS NULL OR u.arabicName LIKE %:arabicName%) " +
                    " AND (:roleCode IS NULL OR ur.code LIKE %:roleCode%) ")
    Page<User> findAllWithRoles(@Param("username") String username,
                                @Param("arabicName") String arabicName,
                                @Param("roleCode") String roleCode, Pageable pageable);
}
