package fund.service;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fund.dto.User;
import fund.mapper.UserMapper;

@Service
public class UserService {

	@Autowired UserMapper userMapper;

	static final String 시스템관리자 = "시스템관리자";

    public static String encryptPasswd(String passwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
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

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof MyAuthenticationProvider.MyAuthenticaion)
            return ((MyAuthenticationProvider.MyAuthenticaion) authentication).getUser();
        return null;
    }

    public static void setCurrentUser(User user) {
        ((MyAuthenticationProvider.MyAuthenticaion)
                SecurityContextHolder.getContext().getAuthentication()).setUser(user);
    }

    public static boolean isUserInRole(String... role) {
        User user = getCurrentUser();
        if (user == null) return false;
        for (String s : role)
            if (user.getUserType().equals(s))
                return true;
        return false;
    }

    public static boolean isCurrentUserAdmin() {
        return getCurrentUser() != null &&
                getCurrentUser().getUserType().equals(시스템관리자);
    }

    public boolean checkPassword(String s) {
        if (s.length() < 7) return false;
        int count = 0;
        if (s.matches(".*[^0-9a-zA-Z].*")) ++count;
        if (s.matches(".*[0-9].*")) ++count;
        if (s.matches(".*[a-z].*")) ++count;
        if (s.matches(".*[A-Z].*")) ++count;
        return count >= 3;
    }

    public static boolean canAccess(int menuId) {
        User user = getCurrentUser();
        if (user == null) return false;
        return user.getMenuIds().contains(menuId);
    }
}
