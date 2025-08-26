package com.books.demo.repository;


import com.books.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserJPARepository extends JpaRepository<User, Long> {
    User findByUserName(String username);

    @Modifying
    @Query("delete from User u where u.email = :email")
    void deleteByEmail(@Param("email") String email);


    @Modifying
    @Query(value = "delete from users u where u.email = :email", nativeQuery = true)
    void deleteByEmailNative(@Param("email") String email);


    Page<User> findByUserNameContainingAndEmailContainingIgnoreCase(String username, String email, Pageable pageable);

    @Query("Select u from User u where Lower(u.userName) like Lower(concat('%', :keyword, '%'))" +
            "or Lower(u.email) like Lower(concat('%', :keyword, '%'))")
    Page<User> findByKeywords(@Param("keyword") String keyword, Pageable pageable);
}
