package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.ResourceNotFoundException.ResourceNotFoundException;
import com.boje.hotelbooking.dto.UserDTO;
import com.boje.hotelbooking.dtoRequest.UserDTORequest;
import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.models.User;
import com.boje.hotelbooking.repositories.UserRepository;
import com.boje.hotelbooking.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final EntityMapper entityMapper;

    public UserServiceImp(UserRepository userRepository,
                          EntityMapper entityMapper) {
        this.userRepository = userRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public UserDTORequest createUser(UserDTORequest userDTORequest) {
        User user = entityMapper.toUser(userDTORequest);
        User userSaved = userRepository.save(user);

        return entityMapper.toUserDTORequest(userSaved);
    }

    @Override
    public UserDTORequest updateUser(UserDTORequest userDTORequest) {
        User user = userRepository.findById(userDTORequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userDTORequest.getId()));

        user.setAge(userDTORequest.getAge());
        user.setBookings(userDTORequest.getBookings());
        user.setPassword(userDTORequest.getPassword());
        user.setFullName(userDTORequest.getFullName());
        user.setContactInfo(userDTORequest.getContactInfo());

        User updatedUser = userRepository.save(user);
        return entityMapper.toUserDTORequest(updatedUser);
    }

    @Override
    public boolean deleteUser(int user_id) {
        try{
            userRepository.deleteById(user_id);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByContactInfo_Email(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        return  entityMapper.toUserDTORequest(user);
    }

    @Override
    public UserDTO findByPhone(String phone) {
        User user = userRepository.findByContactInfo_PhoneNumber(phone)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with phone: " + phone));

        return   entityMapper.toUserDTORequest(user);
    }

    @Override
    public UserDTO findAllDataByUserId(int user_id) {
        User user = userRepository.findAllDataById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));

        return entityMapper.toUserDTORequest(user);
    }
}