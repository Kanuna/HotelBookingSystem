package com.boje.hotelbooking.serviceImp;

import com.boje.hotelbooking.ResourceNotFoundException.ResourceNotFoundException;
import com.boje.hotelbooking.dto.ContactInfoDTO;
import com.boje.hotelbooking.dtoRequest.ContactInfoDTORequest;
import com.boje.hotelbooking.mapper.EntityMapper;
import com.boje.hotelbooking.models.ContactInfo;
import com.boje.hotelbooking.repositories.ContactInfoRepository;
import com.boje.hotelbooking.services.ContactInfoService;
import org.springframework.stereotype.Service;

@Service
public class ContactInfoServiceImp implements ContactInfoService {
    private final ContactInfoRepository contactInfoRepository;
    private final EntityMapper entityMapper;

    public  ContactInfoServiceImp(ContactInfoRepository contactInfoRepository, EntityMapper entityMapper) {
        this.contactInfoRepository = contactInfoRepository;
        this.entityMapper = entityMapper;
    }


    @Override
    public ContactInfoDTORequest createContactInfo(ContactInfoDTORequest contactInfoDTORequest) {
        ContactInfo contactInfo = entityMapper.toContactInfo(contactInfoDTORequest);
        ContactInfo createdContactInfo = contactInfoRepository.save(contactInfo);

        return entityMapper.toContactInfoDTORequest(createdContactInfo);
    }

    @Override
    public ContactInfoDTORequest updateContactInfo(int contactInfo_id, ContactInfoDTO contactInfoDTO) {
        ContactInfo contactInfo = contactInfoRepository.findById(contactInfo_id)
                .orElseThrow(() -> new ResourceNotFoundException("ContactInfo not found with id: " +  contactInfoDTO));

        contactInfo.setEmail(contactInfoDTO.getEmail());
        contactInfo.setUser(contactInfoDTO.getUser());
        contactInfo.setPhoneNumber(contactInfoDTO.getPhone());

        ContactInfo updatedContactInfo = contactInfoRepository.save(contactInfo);

        return entityMapper.toContactInfoDTORequest(updatedContactInfo);
    }

    @Override
    public void deleteContactInfo(int contactInfo_id) {
        if (!contactInfoRepository.existsById(contactInfo_id)) {
            throw new ResourceNotFoundException("Contact info not found with id: " + contactInfo_id);
        }

        contactInfoRepository.deleteById(contactInfo_id);
    }

    @Override
    public ContactInfoDTO getByEmail(String email) {
        ContactInfo contactInfo = contactInfoRepository.findContactInfoByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("ContactInfo not found with email: " +  email));

        return entityMapper.toContactInfoDTORequest(contactInfo);
    }

    @Override
    public ContactInfoDTO getByPhone(String phone) {
        ContactInfo contactInfo = contactInfoRepository.findContactInfoByPhoneNumber(phone)
                .orElseThrow(() -> new ResourceNotFoundException("ContactInfo not found with phone: " +  phone));

        return entityMapper.toContactInfoDTORequest(contactInfo);
    }
}