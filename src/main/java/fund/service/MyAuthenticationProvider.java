package fund.service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import fund.dto.User;
import fund.mapper.SponsorMapper;
import fund.mapper.UserMapper;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired SponsorMapper sponsorMapper;
    @Autowired UserMapper userMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginName = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("ID "+loginName+"  password "+password);
        return authenticate(loginName, password);
    }

    public Authentication authenticate(String loginName, String password) throws AuthenticationException {
       User user = userMapper.selectByLoginName(loginName);
        if (user == null) return null;
        if (user.getPassword().equals(encryptPasswd(password)) == false) return null;

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_전체"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserType()));
        return new MyAuthenticaion(loginName, password, grantedAuthorities, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public static String encryptPasswd(String passwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = passwd.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++)
                sb.append(Integer.toHexString(0xff & digested[i]));
            return sb.toString();
        }
        catch (Exception e) {
            return passwd;
        }
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