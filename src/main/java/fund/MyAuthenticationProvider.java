package fund;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import fund.dto.*;
import fund.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.SystemPropertyUtils;

import fund.dto.User;
import fund.mapper.UserMapper;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired UserMapper userMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginName = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("ID "+loginName+"  password "+password);
        return authenticate(loginName, password);
    }

    public Authentication authenticate(String loginName, String password) throws AuthenticationException {
       User user = userMapper.selectByLoginId(loginName);
        if (user == null) return null;
        if (user.getPassword().equals(password) == false) return null;

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_전체"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.isAdmin()));
        return new MyAuthenticaion(loginName, password, grantedAuthorities, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


    public class MyAuthenticaion extends UsernamePasswordAuthenticationToken {
        private static final long serialVersionUID = 1L;
        User user;

        public MyAuthenticaion (String loginName, String password, List<GrantedAuthority> grantedAuthorities, User user) {
            super(loginName, password, grantedAuthorities);
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}