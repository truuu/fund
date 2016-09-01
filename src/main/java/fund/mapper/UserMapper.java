package fund.mapper;


import fund.dto.*;
import fund.dto.User;

import java.util.Date;
import java.util.List;		
 		
 public interface UserMapper {		
     User selectById(int id);		
     User selectByLoginId(String loginId);
     List<Code> selectAuto(String input);
     Integer ceateNumber();
     List<Sponsor> sponsorManage();
     List<Sponsor> postManage(String startDate,String endDate);
 		
 }

