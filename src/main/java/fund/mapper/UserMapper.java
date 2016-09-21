package fund.mapper;


import fund.dto.User;
import fund.dto.Sponsor;
import java.util.List;

import org.apache.ibatis.annotations.Param;		
 		
 public interface UserMapper {		
     User selectById(int id);		
     User selectByLoginId(String loginName);	
     void userInsert(User user);
     List<Sponsor> churchSum(@Param("startDate")String startDate,@Param("endDate")String endDate);
 		
 }

