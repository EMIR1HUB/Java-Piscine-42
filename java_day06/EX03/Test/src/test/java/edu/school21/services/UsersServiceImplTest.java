package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.CallsRealMethods;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;

import org.mockito.Mock;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class UsersServiceImplTest extends Assertions {

    private EmbeddedDatabase dataSource;

    private UsersServiceImpl usersService;

    //Mockito
    UsersServiceImpl mocRepository = mock(UsersServiceImpl.class);

    @BeforeEach
    public void init() throws SQLException {
        this.dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        try {
            usersService = new UsersServiceImpl(dataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByLogin() {
        User user = new User(0L, "login123", "password123", true);
        mocRepository.findByLogin("login123");
        verify(mocRepository).findByLogin("login123");

        when(mocRepository.findByLogin("login123")).thenReturn(user);
        assertEquals(user, mocRepository.findByLogin("login123"));
    }


    @Test
    public void testUpdate() {
        User user = new User(1L, "adminNew", "admin123New", true);

//        doNothing().when(mocRepository).update(isA(User.class));
//        mocRepository.update(user);
//        verify(mocRepository, times(1)).update(user);

        ArgumentCaptor<User> valueCaptor = ArgumentCaptor.forClass(User.class);
        doNothing().when(mocRepository).update(valueCaptor.capture());
        mocRepository.update(user);

        assertEquals(user, valueCaptor.getValue());
    }

    @Test
    public void testAuthenticate() {
        mocRepository.authenticate("login123", "password123");
        verify(mocRepository).authenticate("login123", "password123");
    }

    @Test
    public void testPassword_False(){
        mocRepository.authenticate("login123", "passwordWrong");
        verify(mocRepository).authenticate("login123", "passwordWrong");

        when(mocRepository.authenticate("login123", "passwordWrong")).thenReturn(false);
        assertFalse(mocRepository.authenticate("login123", "passwordWrong"));
    }

    @Test
    public void checkAuthenticateByLoginException() {
        assertThrows(EntityNotFoundException.class, () -> usersService.authenticate("temp", "temp"));
    }

    @Test
    public void checkAlreadyAuthenticatedException(){
        assertThrows(AlreadyAuthenticatedException.class, () -> usersService.authenticate("admin", "admin123"));
    }


    @AfterEach
    public void end() {
        dataSource.shutdown();
    }
}
