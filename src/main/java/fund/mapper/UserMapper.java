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
    
     int selectCount();
     int searchCount(String codeName);
     int nameCount(String nameForSearch);
     
     int selectChurchCode(Sponsor sponsor);
     //후원자입력 part
     void sponsorInsert(Sponsor sponsor);
     void removeSponsor(String sponsorNo);
     Sponsor selectBySponsorNo(String sponsorNo);
     List<Sponsor> nameSearch(Pagination pagination);
     List<Sponsor> sponsorSearch(Pagination pagination);
     List<Sponsor> selectPage(Pagination pagination);
 }

