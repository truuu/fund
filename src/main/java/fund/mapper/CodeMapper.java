package fund.mapper;

import java.util.HashMap;
import java.util.List;
//import org.apache.ibatis.annotations.Param;
import fund.dto.Code;
//import fund.dto.Pagination;

public interface CodeMapper {
		Code selectById(int id);
		List<Code> selectByCodeGroup(HashMap<String, Object> map);
		 int selectCount(HashMap<String, Object> map);
		void insert(Code code);
	    void update(Code code);
	    void delete(int id);
}
