package com.ramazanayyildiz.api.service;

import com.ramazanayyildiz.api.entity.User;
import com.ramazanayyildiz.api.entity.enums.Role;

import java.util.List;

public interface IUserService extends IService<User> {
    List<User> getUsersByRole(Role role);

    List<User> getPotentialUsers(List<Integer> ids);
}
