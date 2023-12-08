    package com.example.example3.service;

    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import com.example.example3.entity.User;

    public interface UserService {
        User createUser(User user);

        User getUserById(Long userId);

        Page<User> getAllUsers(Pageable pageable);

        User loginUser(String email, String password);

        User updateUser(User user);

        void deleteUser(Long userId);

        User getUserByEmail(String email);
    }