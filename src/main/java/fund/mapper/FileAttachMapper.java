package fund.mapper;

import java.util.List;
import fund.dto.FileAttach;

public interface FileAttachMapper {

    List<FileAttach> selectBySponsorId(int sponsorId);
    int selectCountBySponsorId(int sponsorId);
    FileAttach selectById(int id);
    void insert(FileAttach file);
    void delete(int id);
    void deleteBySponsorId(int sponsorId);

}
