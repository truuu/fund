package fund.mapper;
import java.util.List;
import fund.dto.CodeGroup;
import org.apache.ibatis.annotations.Param;


import java.util.HashMap;
import fund.dto.Code;
import fund.dto.DonationPurpose;


public interface CodeMapper {
	List<Code> selectByBank(String bank);
	List<Code> selectByPaymentMethod(String name);
	List<DonationPurpose> selectDonationPurpose();
	List<Code> selectSponsorType2(String name);
	List<Code> selectAllPaymentMethod(@Param("name1") String name1, @Param("name2") String name2);
	List<Code> selectChurch(String name);
	String selectCodeName(int ID);

	List<Code> selectByCodeGroupName(String name);
	Code selectByID(int ID);
	List<CodeGroup> selectCodeGroup();
	String selectByName(int CodeGroupID);
	List<Code> selectByCodeGroupID(int codeGroupID);
	int selectCount(HashMap<String, Object> map);
	void insert(Code code);
	void update(Code code);
	void delete(int ID);

}
