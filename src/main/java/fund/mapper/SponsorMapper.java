package fund.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fund.dto.Code;
import fund.dto.Pagination;
import fund.dto.Sponsor;
import fund.dto.User;

public interface SponsorMapper {

	
	 User selectById(int id);		
     User selectByLoginId(String loginId);
     List<Code> selectAuto(String input);
     Integer ceateNumber();
     List<Sponsor> sponsorManage();
     List<Sponsor> postManage(@Param("startDate")String startDate,@Param("endDate")String endDate);
     
     int sponsorTypeCheck(String codeName);
    
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
