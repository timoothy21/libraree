package com.timothy.libraree.service;

import com.timothy.libraree.model.UserRequest;
import com.timothy.libraree.model.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest req);
}
