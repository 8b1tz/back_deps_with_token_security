package br.com.ifpb.depsback.controller;

import br.com.ifpb.depsback.config.security.TokenService;
import br.com.ifpb.depsback.controller.dto.TokenDto;
import br.com.ifpb.depsback.controller.form.LoginForm;
import br.com.ifpb.depsback.model.User;
import br.com.ifpb.depsback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AutenticacaoController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> autenticar(@RequestBody LoginForm form) {

        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            Authentication authentication = this.authManager.authenticate(dadosLogin);
            String token = this.tokenService.gerarToken(authentication);
            User user = (User) authentication.getPrincipal();
            TokenDto tokenDto = new TokenDto(token, user);
            return ResponseEntity.ok(tokenDto);
        } catch (AuthenticationException var5) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        repository.save(user);
        return ResponseEntity.ok(user);
    }

}
