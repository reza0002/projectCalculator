package com.example.projectcalculator.Service;

import com.example.projectcalculator.exception.InvalidInputException;
import com.example.projectcalculator.repository.ProjectRepository;
import com.example.projectcalculator.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProjcetServiceTest {
    private ProjectRepository repository;
    private ProjectService service;

    @BeforeEach
    void setUp(){
        repository = Mockito.mock(ProjectRepository.class);
        service = new ProjectService(repository);
    }

    @Test
    void userLogin_notNull(){
        assertThrows(InvalidInputException.class, () -> service.userLogin(null, "blabla"));
    }
}
