package app.service.impl;

import app.entity.User;
import app.role.Role;
import app.service.ServiceException;
import app.service.ServiceProvider;
import app.utils.converter.UserConverter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.lang.reflect.Field;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private ServiceProvider serviceProviderMock;

    @Mock
    private UserServiceImpl userServiceMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getUser() {
    }

    @Test
    public void create() {
    }
}