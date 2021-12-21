package br.com.ifpb.depsback.controller;

import br.com.ifpb.depsback.config.security.TokenService;
import br.com.ifpb.depsback.controller.dto.TokenDto;
import br.com.ifpb.depsback.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            Authentication authentication = this.authManager.authenticate(dadosLogin);
            String token = this.tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException var5) {
            return ResponseEntity.badRequest().build();
        }
    }

}
