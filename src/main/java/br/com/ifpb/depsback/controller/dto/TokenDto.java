package br.com.ifpb.depsback.controller.dto;

import br.com.ifpb.depsback.model.User;

public class TokenDto {
    private String token;
    private User user;

    public TokenDto(String token, User user) {

        this.token = token;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
