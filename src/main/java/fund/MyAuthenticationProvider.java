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
        String loginId = authentication.getName();
        String passwd = authentication.getCredentials().toString();
        System.out.println("ID "+loginId+"  password "+passwd);
        return authenticate(loginId, passwd);
    }

    public Authentication authenticate(String loginId, String passwd) throws AuthenticationException {
       User user = userMapper.selectByLoginId(loginId);
        if (user == null) return null;
        if (user.getPasswd().equals(passwd) == false) return null;

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_전체"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.isAdmin()));
        return new MyAuthenticaion(loginId, passwd, grantedAuthorities, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


    public class MyAuthenticaion extends UsernamePasswordAuthenticationToken {
        private static final long serialVersionUID = 1L;
        User user;

        public MyAuthenticaion (String loginId, String passwd, List<GrantedAuthority> grantedAuthorities, User user) {
            super(loginId, passwd, grantedAuthorities);
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