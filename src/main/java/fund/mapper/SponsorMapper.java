package fund.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Param;
import fund.dto.EB22;
import fund.dto.Sponsor;
import fund.dto.pagination.Pagination;

public interface SponsorMapper {

    Sponsor selectById(int id);
    List<Sponsor> selectNotEncrypted();
    List<Sponsor> selectPage(Pagination pagination);
    int selectCount(Pagination pagination);

    void update(Sponsor sponsor);
    void updateJuminNo(Sponsor sponsor);
    void delete(int id);
    void insert(Sponsor sponsor);
    String generateSponsorNo();

    List<Sponsor> selectForDM(Pagination pagination);
    int selectCountForDM(Pagination pagination);
    List<Sponsor> selectByReceipt(@Param("whereClause") String whereClause);




	int sponsorTypeCheck(String codeName);

	int countForDM(Pagination pagination);

	int searchCount(Pagination pagination); // TODO: 삭제
	int nameCount(String nameForSearch);

	int selectChurchCode(Sponsor sponsor);
	void sponsorInsert(Sponsor sponsor);
	void sponsorInsert2(Sponsor sponsor);
	void removeSponsor(int id);
	void updateSponsor(Sponsor sponsor);
	void updateSponsor2(Sponsor sponsor);

	List<Sponsor> nameSearch(Pagination pagination);
	List<Sponsor> sponsorSearch(Pagination pagination);
	List<Sponsor> sponsorListExcel(Pagination pagination);

	List<Sponsor> castBySponsorType2(@Param("startDate")String startDate,@Param("endDate")String endDate);


    EB22 selectSponsorName(String sponsorNo);
}

