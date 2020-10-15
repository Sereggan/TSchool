package org.tsystems.tschool.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.tsystems.tschool.entity.User;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * The type Pdf user details.
 */
public class PdfUserDetails implements UserDetails {

    private User user;

    /**
     * Instantiates a new Pdf user details.
     *
     * @param user the user
     */
    public PdfUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map(authority -> new SimpleGrantedAuthority(authority.getRole().toString())).collect(Collectors.toList());
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return user.getId();
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    /**
     * Gets user details.
     *
     * @return the user details
     */
    public User getUserDetails() {
        return user;
    }
}