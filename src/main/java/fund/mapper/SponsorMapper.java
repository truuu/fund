package fund.mapper;


import fund.dto.EB22;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import fund.dto.Code;
import fund.dto.Pagination;
import fund.dto.Sponsor;
import fund.dto.User;

public interface SponsorMapper {
	EB22 selectSponsorName(String sponsorNo);
	User selectById(int id);		
	User selectByLoginId(String loginId);
	List<Code> selectAuto(String input);
	Integer ceateNumber();
	List<Sponsor> sponsorManage();
	List<Sponsor> postManage(Pagination pagination);
	List<Sponsor> excelDM(Pagination pagination);


	int sponsorTypeCheck(String codeName);

	int countForDM(Pagination pagination);


	int selectCount();
	int searchCount(String codeName);
	int nameCount(String nameForSearch);

	int selectChurchCode(Sponsor sponsor);
	//�썑�썝�옄�엯�젰 part
	void sponsorInsert(Sponsor sponsor);
	void removeSponsor(String sponsorNo);
	void updateSponsor(Sponsor sponsor);

	Sponsor selectBySponsorNo(int id);
	List<Sponsor> nameSearch(Pagination pagination);
	List<Sponsor> sponsorSearch(Pagination pagination);
	List<Sponsor> selectPage(Pagination pagination);
	List<Sponsor> sponsorListExcel(Pagination pagination);

	//�썑�썝�씤援щ텇2蹂� 異쒖뿰�궡�뿭
	List<Sponsor> castBySponsorType2(@Param("startDate")String startDate,@Param("endDate")String endDate);
	
	String selectBySponsorNo2(int id);


}

