package edu.school21.services;

import edu.school21.exeptions.AlreadyAuthenticatedException;
import edu.school21.exeptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsersServiceImplTest {
    @Mock
    private UsersRepository usersRepository;
    
    @InjectMocks
    private UsersServiceImpl usersService = new UsersServiceImpl(usersRepository);
    
    @Test
    public void checkAuthenticate_true() {
        User user = new User(1, "Sam", "12345");
        Mockito.when(usersRepository.findByLogin("Sam")).thenReturn(user);
        assertTrue(usersService.authenticate("Sam", "12345"));
        Mockito.verify(usersRepository, Mockito.times(1)).update(user);
    }
    
    @Test
    public void checkAuthenticate_throwEntityNotFoundException() {
        Mockito.when(usersRepository.findByLogin("Brad"))
                .thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class,
                () -> usersService.authenticate("Brad", "123"));
    }

    @Test
    public void checkAuthenticate_throwAlreadyAuthenticatedException() {
        User user = new User(2, "John", "qwerty");
        user.setAuthenticated(true);
        Mockito.when(usersRepository.findByLogin("John")).thenReturn(user);
        assertThrows(AlreadyAuthenticatedException.class,
                () -> usersService.authenticate("John", "qwerty"));
        Mockito.verify(usersRepository, Mockito.times(0)).update(user);
    }

    @Test
    public void checkAuthenticate_false() {
        User user = new User(1, "Sam", "12345");
        Mockito.when(usersRepository.findByLogin("Sam")).thenReturn(user);
        assertFalse(usersService.authenticate("Sam", "zxcv"));
        Mockito.verify(usersRepository, Mockito.times(0)).update(user);
    }
}
