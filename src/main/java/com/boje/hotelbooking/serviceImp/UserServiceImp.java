package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.dto.UserDTO;
import com.boje.hotelbooking.dtoRequest.UserDTORequest;
import com.boje.hotelbooking.mapper.ModelMapper;
import com.boje.hotelbooking.models.User;
import com.boje.hotelbooking.repositories.UserRepository;
import com.boje.hotelbooking.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImp(UserRepository userRepository,
                          ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTORequest createUser(UserDTORequest userDTORequest) {
        User user = modelMapper.toUser(userDTORequest);
        User userSaved = userRepository.save(user);

        return modelMapper.toUserDTORequest(userSaved);
    }

    @Override
    public UserDTORequest updateUser(UserDTORequest userDTORequest) {

    }

    @Override
    public void deleteUser(int user_id) {

    }

    @Override
    public UserDTO findByEmail(String email) {
        return null;
    }

    @Override
    public UserDTO findByPhone(String phone) {
        return null;
    }

    @Override
    public UserDTO findAllDataByUserId(int user_id) {
        return null;
    }

    @Override
    public List<UserDTO> findUserByStreet(String street) {
        return List.of();
    }
}