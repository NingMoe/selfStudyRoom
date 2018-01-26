package com.ls.spt.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class MyUser implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = 5485018355764818402L;
    
    private Integer userid;
    
    private String password;

    private String name;//用户的中文姓名

    private final String username;//用户登录账号(手机号)

    private final Set<GrantedAuthority> authorities;

    private final boolean accountNonExpired;

    private final boolean accountNonLocked;

    private final boolean credentialsNonExpired;

    private final boolean enabled;

    
    /**
     * 构造方法
     * @param userid
     * @param password
     * @param name
     * @param username
     * @param authorities
     * @param accountNonExpired
     * @param accountNonLocked
     * @param credentialsNonExpired
     * @param enabled
     */
    public MyUser(Integer userid, String password, String name, final String username, final Collection<? extends GrantedAuthority> authorities, final boolean accountNonExpired,
            final boolean accountNonLocked, final boolean credentialsNonExpired, final boolean enabled) {
        if (username == null || "".equals(username) || password == null)
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        this.userid = userid;
        this.password = password;
        this.name = name;
        this.username = username;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }


    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void eraseCredentials() {
        password = null;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(new AuthorityComparator());
        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }
        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            }
            if (g1.getAuthority() == null) {
                return 1;
            }
            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof MyUser)
            return username.equals(((MyUser) rhs).username);
        return false;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("Username: ").append(this.username).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.enabled).append("; ");
        sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
        sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
        sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");
        if (!authorities.isEmpty()) {
            sb.append("Granted Authorities: ");
            boolean first = true;
            for (GrantedAuthority auth : authorities) {
                if (!first) {
                    sb.append(",");
                }
                first = false;

                sb.append(auth);
            }
        }
        else {
            sb.append("Not granted any authorities");
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }




}
