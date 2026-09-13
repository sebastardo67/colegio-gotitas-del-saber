
package main.java.edu.jett.colegio.gotitas.service;

import java.sql.SQLException;
import main.java.edu.jett.colegio.gotitas.dto.request.LoginRequest;
import main.java.edu.jett.colegio.gotitas.dto.response.LoginResponse;
import main.java.edu.jett.colegio.gotitas.repository.AuthRepository;
import main.java.edu.jett.colegio.gotitas.security.jbcrypt.BCrypt;

public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public LoginResponse login(LoginRequest loginRequest) throws SQLException {

        if (loginRequest == null) {
            throw new RuntimeException("Credenciales vacias");
        }

        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            throw new RuntimeException("El correo o la contraseña no pueden estar vacios");
        }

        LoginResponse response = authRepository.findUserByEmail(loginRequest);

        if (response == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        String contrasenaHashed = response.getContrasena_hash();

        if (contrasenaHashed == null) {
            throw new RuntimeException("Contrasena invalida");
        }

        if (BCrypt.checkpw(loginRequest.getPassword(), contrasenaHashed)) {
            return new LoginResponse(
                    response.getNombre(),
                    response.getApellido(),
                    response.getContrasena_hash()
            );
        }

        return null;
    }

    public boolean registerUser(
            int idDocente,
            String email,
            String rawPassword,
            int idRol
    ) throws SQLException {

        if (email == null || email.trim().isEmpty()
                || rawPassword == null || rawPassword.trim().isEmpty()) {

            throw new RuntimeException(
                    "El correo y la contraseña son obligatorios"
            );
        }

        String hashedPassword = BCrypt.hashpw(
                rawPassword,
                BCrypt.gensalt()
        );

        return authRepository.saveUser(
                idDocente,
                email,
                hashedPassword,
                idRol
        );
    }
}
