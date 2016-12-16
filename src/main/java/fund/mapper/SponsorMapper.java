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
	String ceateYear();
	List<Sponsor> sponsorManage();
	List<Sponsor> postManage(Pagination pagination);
	List<Sponsor> excelDM(Pagination pagination);


	int sponsorTypeCheck(String codeName);

	int countForDM(Pagination pagination);


	int selectCount();
	int searchCount(Pagination pagination);
	int nameCount(String nameForSearch);

	int selectChurchCode(Sponsor sponsor);
	//占쎌뜎占쎌뜚占쎌쁽占쎌뿯占쎌젾 part
	void sponsorInsert(Sponsor sponsor);
	void sponsorInsert2(Sponsor sponsor);//�냼�냽援먰쉶瑜� �엯�젰�븯吏� �븡�� 寃쎌슦 
	void removeSponsor(int id);
	void updateSponsor(Sponsor sponsor);
	void updateSponsor2(Sponsor sponsor);

	Sponsor selectBySponsorNo(int id);
	List<Sponsor> nameSearch(Pagination pagination);
	List<Sponsor> sponsorSearch(Pagination pagination);
	List<Sponsor> selectPage(Pagination pagination);
	List<Sponsor> sponsorListExcel(Pagination pagination);
	List<Sponsor> nameCheck(String name); // search name check
	List<Sponsor> codeNameCheck(String codeName);

	//占쎌뜎占쎌뜚占쎌뵥�뤃�됲뀋2癰귨옙 �빊�뮇肉곤옙沅∽옙肉�
	List<Sponsor> castBySponsorType2(@Param("startDate")String startDate,@Param("endDate")String endDate);
	
	String selectBySponsorNo2(int id);


}

