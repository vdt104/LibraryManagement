package com.vdt.library_mangement.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vdt.library_mangement.dto.ReaderDto;
import com.vdt.library_mangement.entity.Reader;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // // Configure nested mappings
        // modelMapper.addMappings(new PropertyMap<Reader, ReaderDto>() {
        //     @Override
        //     protected void configure() {
        //         map().setFullName(source.getUser().getFullName());
        //         map().setDob(source.getUser().getDob());
        //         map().setGender(source.getUser().getGender() != null ? source.getUser().getGender().toString() : null);
        //         map().setPhoneNumber(source.getUser().getPhoneNumber());
        //         map().setAddress(source.getUser().getAddress());
        //         map().setIdentificationNumber(source.getUser().getIdentificationNumber());
        //         map().setEmail(source.getUser().getEmail());
        //         map().setStudentId(source.getStudentId());
        //     }
        // });

        return modelMapper;
    }
}