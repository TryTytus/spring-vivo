package com.example.demo.user;


import com.example.demo.channel.Channel;
import com.example.demo.comment.Comment;
import com.example.demo.video.Video;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User implements UserDetails, CredentialsContainer {

    @Id
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, length = 500)
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Video> videos = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();

    @Embedded
    private Channel channel;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void eraseCredentials() {

    }
}
