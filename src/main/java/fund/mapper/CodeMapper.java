package fund.mapper;

import java.util.HashMap;
import java.util.List;
//import org.apache.ibatis.annotations.Param;
import fund.dto.Code;
import fund.dto.CodeGroup;
//import fund.dto.Pagination;

public interface CodeMapper {
		List<Code> selectByCodeGroupName(String name);
	    Code selectByID(int ID);
		List<CodeGroup> selectCodeGroup();
		String selectByName(int CodeGroupID);
		List<Code> selectByCodeGroupID(HashMap<String, Object> map);
		 int selectCount(HashMap<String, Object> map);
		void insert(Code code);
	    void update(Code code);
	    void delete(int ID);
}
