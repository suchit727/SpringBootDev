package com.example.springwithresttemplate.controller;

import com.example.springwithresttemplate.model.Address;
import com.example.springwithresttemplate.model.Person;
import com.example.springwithresttemplate.model.PersonRe;
import com.example.springwithresttemplate.service.PersonAddressRestImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@Log4j2
public class PersonAddressRestControllerTest {
    @InjectMocks
    private PersonAddressRestController microserviceController;
    @Mock
    private PersonAddressRestImpl restService;
    @Mock
    private ObjectMapper mapper;
    private MockMvc mockMvc;
    private Person person;
    private Address address;
    private PersonRe personRe;
    private JacksonTester<Person> personResponseJacksonTester;
    private JacksonTester<Address> addressJacksonTester;
    private JacksonTester<PersonRe> responseJacksonTester;
    private static final String get_persons = "/person/1";
    private static final String put_person = "/update-person/1";
    private static final String delete_person = "/delete-person/1";
    private static final String post_person = "/person";

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(microserviceController).build();
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        JacksonTester.initFields(this, mapper);

        person = new Person();
        person.setFirstName("suchit");
        person.setLastName("khadtar");

        address = new Address();
        address.setAddressOne("Borivali");
        address.setAddressTwo("near national park");
        address.setCity("city");
        address.setZipCode("zipcode");
        person.setAddress(address);
        personRe = new PersonRe();
        personRe.setId(1L);


    }

    @Test
    public void testGetPerson() throws Exception {
        Person upPerson = new Person();
        upPerson.setFirstName("suraj");
        upPerson.setLastName("lokhande");

        Address  upAddress = new Address();
        upAddress.setAddressOne("Borivali-E");
        upAddress.setAddressTwo("near national park");
        upAddress.setCity("Mumbai");
        upAddress.setZipCode("400066");
        upPerson.setAddress(address);


        Long id = 1L;

        when(restService.getPerson(id)).thenReturn(person);
        MockHttpServletResponse mockHttpServletResponse=mockMvc.perform(MockMvcRequestBuilders.get(get_persons)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(personResponseJacksonTester.write(person).getJson()))
                .andExpect(status().isOk()).andReturn().getResponse();

        Assertions.assertEquals(personResponseJacksonTester.write(person).getJson(),mockHttpServletResponse.getContentAsString());
        verify(restService, times(1)).getPerson(id);
    }

    @Test
    public void testUpdatePerson() throws Exception {
       Person upPerson = new Person();
        upPerson.setFirstName("suraj");
        upPerson.setLastName("lokhande");

      Address  upAddress = new Address();
        upAddress.setAddressOne("Borivali-E");
        upAddress.setAddressTwo("near national park");
        upAddress.setCity("Mumbai");
        upAddress.setZipCode("400066");
        upPerson.setAddress(address);


        when(restService.updatePerson(anyLong(), any(Person.class))).thenReturn(upPerson);

        MockHttpServletResponse mockHttpServletResponse = mockMvc
                .perform(MockMvcRequestBuilders.put(put_person)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(personResponseJacksonTester.write(person).getJson()))
                .andExpect(status().isOk()).andReturn().getResponse();

        Assertions.assertEquals(personResponseJacksonTester.write(upPerson).getJson(),mockHttpServletResponse.getContentAsString());
        verify(restService, times(1)).updatePerson(anyLong(), any(Person.class));

    }

    @Test
    public void testCreatePerson() throws Exception {
        when(restService.createPerson(any(Person.class))).thenReturn(personRe);
       MockHttpServletResponse mockHttpServletResponse= mockMvc.perform(MockMvcRequestBuilders.post(post_person)
                 .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(personResponseJacksonTester.write(person).getJson()))
                .andExpect(status().isOk()).andReturn().getResponse();

       Assertions.assertEquals(responseJacksonTester.write(personRe).getJson(),mockHttpServletResponse.getContentAsString());

        verify(restService, times(1)).createPerson(any(Person.class));

    }

    @Test
    public void testDeletePerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(delete_person, 1)).andExpect(status().isOk()).andReturn().getResponse();
        verify(restService, times(1)).deletePerson(anyLong());
    }
}
