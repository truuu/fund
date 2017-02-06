package fund.mapper;

import java.util.List;
import fund.dto.Log;
import fund.dto.pagination.Pagination;

public interface LogMapper {
    Log selectById(int id);
    List<Log> selectPage(Pagination pagination);
    int selectCount(Pagination pagination);
    void insert(Log log);
    void delete(int id);

}
