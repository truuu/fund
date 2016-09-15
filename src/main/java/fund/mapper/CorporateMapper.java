package fund.mapper;

import java.util.List;

import fund.dto.Corporate;
import fund.dto.Pagination;

public interface CorporateMapper {
	List<Corporate> selectCorporate();
	Corporate selectByID(int ID);
	List<Corporate> selectPage(Pagination pagination);
	int selectCount();
    void insert(Corporate corporate);
    void update(Corporate corporate);
    void delete(int ID);

}
