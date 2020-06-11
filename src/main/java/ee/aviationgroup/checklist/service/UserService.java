package ee.aviationgroup.checklist.service;

import ee.aviationgroup.checklist.dto.JwtRequestDto;
import ee.aviationgroup.checklist.dto.JwtResponseDto;
import ee.aviationgroup.checklist.model.User;
import ee.aviationgroup.checklist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public JwtResponseDto authenticate(JwtRequestDto request) throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        final User userDetails = userRepository.getUserByUsername(request.getUsername());
        final String token = jwtTokenService.generateToken(userDetails.getUsername());
        return new JwtResponseDto(userDetails.getId(), userDetails.getUsername(), token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
