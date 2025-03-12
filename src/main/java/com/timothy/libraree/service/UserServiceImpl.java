package com.timothy.libraree.service;

import com.timothy.libraree.entity.User;
import com.timothy.libraree.exception.APIException;
import com.timothy.libraree.model.UserRequest;
import com.timothy.libraree.model.UserResponse;
import com.timothy.libraree.model.mapper.UserMapper;
import com.timothy.libraree.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest req) {
        boolean userExists = userRepository.existsById(req.getNIK());

        if (userExists) {
            throw new APIException(HttpStatus.CONFLICT, "User with NIK " + req.getNIK() + " already exists!");
        }

        User user = UserMapper.INSTANCE.mapToUser(req);
        userRepository.save(user);

        return UserMapper.INSTANCE.mapToUserResponse(user);
    }
}
