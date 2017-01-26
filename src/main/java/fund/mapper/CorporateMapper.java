package fund.mapper;

import java.util.List;
import fund.dto.Corporate;

public interface CorporateMapper {
	List<Corporate> selectAll();
	Corporate selectById(int ID);
    void insert(Corporate corporate);
    void update(Corporate corporate);
    void delete(int ID);
}
