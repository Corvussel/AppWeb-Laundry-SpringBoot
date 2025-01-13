package com.laundry.lavanderia.service.interfaces;

import java.util.List;

import com.laundry.lavanderia.Model.Security.Role;

public interface IRoleService {

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    void saveRole(Role role);

    void deleteRole(Long id);

}
