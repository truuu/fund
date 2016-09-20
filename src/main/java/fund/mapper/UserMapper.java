package fund.mapper;


import fund.dto.User; 		
import java.util.List;		
 		
 public interface UserMapper {		
     User selectById(int id);		
     User selectByLoginId(String loginName);	
     void userInsert(User user);
 		
 }

