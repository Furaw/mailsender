package com.yukon.ita.user;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User insert(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    User update(User user, Long id);

    void deleteById(Long id);
}
