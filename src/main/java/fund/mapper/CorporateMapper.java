package fund.mapper;

import java.util.List;

import fund.dto.Corporate;
import fund.dto.Pagination;

public interface CorporateMapper {
	Corporate selectById(int id);
	List<Corporate> selectPage(Pagination pagination);
	int selectCount();
    void insert(Corporate corporate);
    void update(Corporate corporate);
    void delete(int id);

}
