package com.example.onetooneinspring.controller;


import com.example.onetooneinspring.service.PersonAddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonAddressControllerTest {

    @InjectMocks
    private PersonAddressControllerTest personAddressControllerTest;
    @Mock
    private PersonAddressService personAddressService;

    @BeforeEach
    public void setUp(){


    }

//    @Test
//    public void testGetPerson() throws Exception{
//         when(personAddressService.getPerson(1L)).thenReturn()
//    }

}
