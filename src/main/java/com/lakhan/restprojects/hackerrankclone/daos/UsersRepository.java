package com.lakhan.restprojects.hackerrankclone.daos;

import com.lakhan.restprojects.hackerrankclone.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    public Optional<User> findByUsername(String username);
}
