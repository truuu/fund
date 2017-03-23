package fund.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import fund.dto.Log;
import fund.dto.User;
import fund.mapper.LogMapper;

@Service
public class LogService {

    static Pattern errorMsgParrern = Pattern.compile("@&([^&]+)&@");
    @Autowired LogMapper logMapper;

    public String logError(Exception e) {
        String errorMsg = this.insert(e);
        return errorMsg;
    }

    public String logErrorAndReturn(Model model, Exception e, String returnStr) {
        String errorMsg = this.insert(e);
        model.addAttribute("errorMsg", errorMsg);
        return returnStr;
    }

    static String getFullURL(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        if (queryString == null) return requestURL.toString();
        return requestURL.append('?').append(queryString).toString();
    }

    public String insert(Exception e) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            Log log = new Log();
            log.setBody(ExceptionUtils.getStackTrace(e));
            log.setUrl(getFullURL(request));
            log.setCategory(e.getClass().getName());

            String ip = request.getHeader("X-FORWARDED-FOR");
            if (ip == null)
                ip = request.getRemoteAddr();
            log.setIp(ip);

            User user = UserService.getCurrentUser();
            log.setCurrentUser(user == null ? null : user.getLoginName());
            logMapper.insert(log);

            String message = e.getMessage();
            Matcher m = errorMsgParrern.matcher(message);
            if (m.find()) return m.group(1);
        } catch (Exception e2) {
            e.printStackTrace(System.out);
            e2.printStackTrace(System.out);
        }
        return "작업 실패";
    }

}
