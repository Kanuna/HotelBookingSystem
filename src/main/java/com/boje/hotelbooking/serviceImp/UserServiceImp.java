package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.ResourceNotFoundException.ResourceNotFoundException;
import com.boje.hotelbooking.dto.LoginRequestDTO;
import com.boje.hotelbooking.dto.UserDTO;
import com.boje.hotelbooking.dto.UserResponseDTO;
import com.boje.hotelbooking.dtoRequest.UserDTORequest;
import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.models.Booking;
import com.boje.hotelbooking.models.ContactInfo;
import com.boje.hotelbooking.models.User;
import com.boje.hotelbooking.repositories.BookingRepository;
import com.boje.hotelbooking.repositories.UserRepository;
import com.boje.hotelbooking.services.UserService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final EntityMapper entityMapper;

    public UserServiceImp(UserRepository userRepository,
                          BookingRepository bookingRepository,
                          EntityMapper entityMapper) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public UserDTORequest createUser(UserDTO userDTO) {
        User user = entityMapper.toUser(userDTO);

        Argon2 argon = Argon2Factory.create();
        String hashedPassword = argon.hash(3, 65536, 1, user.getPassword().toCharArray());

        user.setPassword(hashedPassword);

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setEmail(userDTO.getContactInfo().getEmail());
        contactInfo.setPhoneNumber(userDTO.getContactInfo().getPhone());

        contactInfo.setUser(user);
        user.setContactInfo(contactInfo);

        User userSaved = userRepository.save(user);

        return entityMapper.toUserDTORequest(userSaved);
    }

    @Override
    public UserDTO updateUser(UserDTORequest userDTORequest) {
        User user = userRepository.findById(userDTORequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userDTORequest.getId()));

        user.setAge(userDTORequest.getAge());
        user.setPassword(userDTORequest.getPassword());
        user.setFullName(userDTORequest.getFullName());

        if(userDTORequest.getBooking_ids() != null) {
            List<Booking> bookings = bookingRepository.findAllById(userDTORequest.getBooking_ids());
            bookings.forEach(b -> b.setUser(user));
            user.setBookings(bookings);
        }

        user.setContactInfo(entityMapper.toContactInfo(userDTORequest.getContactInfo()));

        User updatedUser = userRepository.save(user);
        return entityMapper.toUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(int user_id) {
        if (!userRepository.existsById(user_id)) {
            throw new ResourceNotFoundException("Contact info not found with id: " + user_id);
        }

        userRepository.deleteById(user_id);
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
    public UserResponseDTO findAllDataByUserId(int user_id) {
        User user = userRepository.findAllDataById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));

        return entityMapper.toUserResponseDTO(user);
    }

    @Override
    public Boolean login(LoginRequestDTO request) {
        User user = userRepository.findByContactInfo_Email(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given email " +  request.getEmail() ));

        Argon2 argon = Argon2Factory.create();

        return argon.verify(user.getPassword(), request.getPassword().toCharArray());
    }
}