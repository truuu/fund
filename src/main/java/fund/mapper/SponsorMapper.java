package fund.mapper;


import java.util.List;
import fund.dto.Sponsor;
import fund.dto.pagination.Pagination;

public interface SponsorMapper {

    String selectKey1();
    Sponsor selectById(int id);
    List<Sponsor> selectPage(Pagination pagination);
    int selectCount(Pagination pagination);

    void update(Sponsor sponsor);
    void delete(int id);
    void insert(Sponsor sponsor);
    String generateSponsorNo();

    List<Sponsor> selectForDM(Pagination pagination);
    int selectCountForDM(Pagination pagination);
}

