package net.cabrasky.table2taste.backend.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.cabrasky.table2taste.backend.model.Privilage;
import net.cabrasky.table2taste.backend.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String username;

    @JsonIgnore
    private String password;

	private List<GrantedAuthority> privilages;

    public UserDetailsImpl(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        
        List<GrantedAuthority> privilages = new ArrayList<>();

        for(Privilage privilate : user.getGroups().stream().flatMap(group -> group.getPrivilages().stream()).toList()){

        	privilages.add(new SimpleGrantedAuthority(privilate.getId().toUpperCase()));
        }
        this.privilages = privilages;
        System.out.println(privilages);

    }

    public static UserDetailsImpl build(User user) {

        return new UserDetailsImpl(user);
    }
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return privilages;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(username, user.username);
    }
}