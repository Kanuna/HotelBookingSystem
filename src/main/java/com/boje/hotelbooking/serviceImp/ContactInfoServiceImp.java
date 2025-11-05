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
    public ContactInfoDTORequest createContactInfo(ContactInfoDTO contactInfoDTO) {
        ContactInfo contactInfo = entityMapper.toContactInfo(contactInfoDTO);
        ContactInfo createdContactInfo = contactInfoRepository.save(contactInfo);

        return entityMapper.toContactInfoDTORequest(createdContactInfo);
    }

    @Override
    public ContactInfoDTO updateContactInfo(ContactInfoDTORequest contactInfoDTORequest) {
        ContactInfo contactInfo = contactInfoRepository.findById(contactInfoDTORequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("ContactInfo not found with id: " + contactInfoDTORequest.getId()));

        contactInfo.setEmail(contactInfoDTORequest.getEmail());
        contactInfo.setUser(entityMapper.toUser(contactInfoDTORequest.getUser()));
        contactInfo.setPhoneNumber(contactInfoDTORequest.getPhone());

        ContactInfo updatedContactInfo = contactInfoRepository.save(contactInfo);

        return entityMapper.toContactInfoDTO(updatedContactInfo);
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