package com.example.cinema_back_end.security.service;

import com.example.cinema_back_end.entities.Role;
import com.example.cinema_back_end.security.repo.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author tritcse00526x
 */
@Service
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepository roleRepository;

/*START-Override from IRoleService*/
    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
/*END-Override from IRoleService*/

/*START-Override from IGeneralService*/
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void remove(Integer id) {
        roleRepository.deleteById(id);
    }
/*END-Override from IGeneralService*/
}
