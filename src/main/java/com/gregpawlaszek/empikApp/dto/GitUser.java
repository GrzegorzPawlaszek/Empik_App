package com.gregpawlaszek.empikapp.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class GitUser {
    private String login;
    private int id;
    private String url;
    private String html_url;
    private String followers_url;
    private String following_url;
    private String avatar_url;
    private String gists_url;
    private String starred_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private boolean site_admin;
    private String name;
    private Object company;
    private String blog;
    private String location;
    private Object email;
    private boolean hireable;
    private Object bio;
    private Object twitter_username;
    private int public_repos;
    private int public_gists;
    private int followers;
    private int following;
    private LocalDate created_at;
    private LocalDate updated_at;
}

