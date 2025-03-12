package com.timothy.libraree.model.mapper;

import com.timothy.libraree.entity.User;
import com.timothy.libraree.model.UserRequest;
import com.timothy.libraree.model.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapToUser(UserRequest req);

    UserResponse mapToUserResponse(User user);
}
