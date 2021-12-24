package edu.kpi.iasa.workshop.service;

import edu.kpi.iasa.workshop.exception.RoleNotFoundException;
import edu.kpi.iasa.workshop.model.Role;
import edu.kpi.iasa.workshop.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByCode(String code) {
        return roleRepository.findRoleByCode(code).orElseThrow(RoleNotFoundException::new);
    }
}
