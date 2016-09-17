package fund.mapper;

import java.util.*;
import fund.dto.FileAttachment;

public interface FileAttachmentMapper {
	
	List<FileAttachment> selectByArticleId(int sponsorID);
	FileAttachment selectById(int id);
    void insert(FileAttachment file);
    void deleteById(int id);
    void deleteByArticleId(int articleId);


}
