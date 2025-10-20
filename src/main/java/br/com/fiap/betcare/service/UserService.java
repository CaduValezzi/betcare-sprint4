package br.com.fiap.betcare.service;

import br.com.fiap.betcare.dto.UserDTO;
import br.com.fiap.betcare.entity.User;

public interface UserService {
    User register(String username, String password);
    String login(String username, String password);
    UserDTO findByUsername(String username);
}
