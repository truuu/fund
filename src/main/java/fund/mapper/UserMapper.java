package fund.mapper;


import fund.dto.*;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;		
 		
 public interface UserMapper {		
     User selectById(int id);		
     User selectByLoginId(String loginId);
     List<Code> selectAuto(String input);
     Integer ceateNumber();
     List<Sponsor> sponsorManage();
     List<Sponsor> postManage(@Param("startDate")String startDate,@Param("endDate")String endDate);
     
     String sponsorTypeCheck(String codeName);
     List<Sponsor> sponsorSearch(Pagination pagination);
     List<Sponsor> selectPage(Pagination pagination);
     int selectCount();
     int searchCount(String codeName);
     
     int selectChurchCode(Sponsor sponsor);
     void sponsorInsert(Sponsor sponsor);
 }

