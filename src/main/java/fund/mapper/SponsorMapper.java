package fund.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Param;
import fund.dto.Code;
import fund.dto.EB22;
import fund.dto.Pagination;
import fund.dto.Sponsor;
import fund.dto.User;

public interface SponsorMapper {
	EB22 selectSponsorName(String sponsorNo);
	Sponsor testSelectBySponsorNo(String sponsorNo);		
	User selectByLoginId(String loginId);
	List<Code> selectAuto(String input);
	Integer ceateNumber();
	String ceateYear();
	
	List<Sponsor> postManage(Pagination pagination);
	List<Sponsor> excelDM(Pagination pagination);
	List<Sponsor> selectByReceipt(@Param("whereClause") String whereClause);
	
	int sponsorTypeCheck(String codeName);

	int countForDM(Pagination pagination);

    int selectCount(Pagination pagination);
	int searchCount(Pagination pagination); // TODO: 삭제
	int nameCount(String nameForSearch);

	int selectChurchCode(Sponsor sponsor);
	void sponsorInsert(Sponsor sponsor);
	void sponsorInsert2(Sponsor sponsor); 
	void removeSponsor(int id);
	void updateSponsor(Sponsor sponsor);
	void updateSponsor2(Sponsor sponsor);

	Sponsor selectBySponsorNo(int id);
	List<Sponsor> nameSearch(Pagination pagination);
	List<Sponsor> sponsorSearch(Pagination pagination);
	List<Sponsor> selectPage(Pagination pagination);//sponsor manage page
	List<Sponsor> sponsorListExcel(Pagination pagination);
	List<Sponsor> nameCheck(String name); // search name check
	List<Sponsor> codeNameCheck(String codeName);

	List<Sponsor> castBySponsorType2(@Param("startDate")String startDate,@Param("endDate")String endDate);
	
	String selectBySponsorNo2(int id);	
	List<Sponsor> selectNotEncrypted();
	void updateJuminNo(Sponsor sponsor);
}

