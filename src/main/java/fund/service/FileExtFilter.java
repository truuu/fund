package fund.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileExtFilter {
	
    /**
     * 파일의 확장자를 체크하여 필터링된 확장자를 포함한 파일인 경우에 true를 리턴한다.
     * @param file
     * */
    public static boolean badFileExtIsReturnBoolean(MultipartFile file) {
        String fileName = file.getName();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1,
                fileName.length());
        final String[] CORRECT_EXTENSION = { "xlsx", "xls"};
 
        int len = CORRECT_EXTENSION.length;
        for (int i = 0; i < len; i++) {
            if (ext.equalsIgnoreCase(CORRECT_EXTENSION[i])) {
                return true; // 올바른 확장자가 존재할때..
            }
        }
        return false;
    }
}
