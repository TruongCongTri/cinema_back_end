
package com.example.cinema_back_end.security.service;


import com.example.cinema_back_end.entities.Role;
import com.example.cinema_back_end.entities.User;
import com.example.cinema_back_end.security.UserPrinciple;
import com.example.cinema_back_end.security.repo.IRoleRepository;
import com.example.cinema_back_end.security.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author tritcse00526x
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

/*START-Override from IUserService*/
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void updateInfo(Integer userId, User user) {
        userRepository.updateInfo(userId, user.getFullName(), user.getPhone());;
    }

    @Override
    public void changePassword(Integer id,String newPass) {
        userRepository.changePassword(id, passwordEncoder.encode(newPass));
    }

    @Override
    public void saveUserByAdmin(User user, boolean isUpdatePass) throws Exception{
        User existingUser = findByUsername(user.getUsername()).orElse(null);
        if(existingUser != null && user.getId() != existingUser.getId()){
            throw new Exception("Đã tồn tại người dùng, vui lòng chọn tên đăng nhập khác");
        }
        Set<Role> roles= user.getRoles()
                .stream()
                .map(role -> {
                    Role existingRole = roleRepository.findByName(role.getName());
                    if(existingRole != null) {
                        return existingRole;
                    } else {
                        return role;
                    }
                })
                .collect(Collectors.toSet());
        user.setRoles(roles);
        if(isUpdatePass){
            save(user);
        } else {
            userRepository.save(user);
        }
    }

    @Override
    public User getByResetPasswordToken(String token) {
        // TODO Auto-generated method stub
        return null;
    }
/*END-Override from IUserService*/

/*START-Override from IGeneralService*/
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        try {
            User existingUser = findByUsername(user.getUsername()).orElse(null);
            if(existingUser != null && user.getId() != existingUser.getId()){
                throw new Exception("Đã tồn tại người dùng, vui lòng chọn tên đăng nhập khác");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (Exception e) {
            return user;
        }
    }

    @Override
    public void remove(Integer id) {
        userRepository.deleteUserTableJoin(id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Verify whether the username exists in the database
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }
/*END-Override from IUserService*/

}

