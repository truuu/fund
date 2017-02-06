package fund.mapper;

import java.util.List;
import fund.dto.FileAttachment;

public interface FileAttachmentMapper {

	List<FileAttachment> selectBySponosrId(int sponsorId);
	FileAttachment selectById(int id);
    void insert(FileAttachment file);
    void deleteById(int id);
    void deleteByArticleId(int articleId);


}