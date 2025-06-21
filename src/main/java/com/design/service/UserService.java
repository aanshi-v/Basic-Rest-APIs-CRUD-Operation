package com.design.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.design.entity.User;
import com.design.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	
	public List<User> getAllUsers() {
        return userRepository.findAll();
    }

	
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    
    public User createUser(User user) {
        return userRepository.save(user);
    }

    
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    return userRepository.save(user);
                }).orElse(null);
    }
    
    
    public User patchUser(Long id, Map<String, Object> updates) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null; // or throw a custom exception
        }

        if (updates.containsKey("name")) {
            user.setName((String) updates.get("name"));
        }

        if (updates.containsKey("email")) {
            user.setEmail((String) updates.get("email"));
        }

        return userRepository.save(user);
    }

    

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
