package fund.mapper;


import fund.dto.User;
import fund.dto.Pagination;
import fund.dto.Sponsor;
import java.util.List;

	
 		
 public interface UserMapper {		
     User selectById(int id);		
     User selectByLoginId(String loginName);	
     void userInsert(User user);
     List<Sponsor> churchSum2(Pagination  pagination);
     List<Sponsor> churchSum(Pagination  pagination);
     int countForChurch(Pagination  pagination);//페이징을 위한 count
 		
 }

