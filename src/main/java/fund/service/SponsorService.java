package fund.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fund.dto.Sponsor;
import fund.dto.pagination.Pagination;
import fund.mapper.SponsorMapper;

@Service
public class SponsorService {

    @Autowired SponsorMapper sponsorMapper;

    public Sponsor selectById(int id) throws Exception {
        return sponsorMapper.selectById(id);
    }

    public void update(Sponsor sponsor) throws Exception {
        sponsorMapper.update(sponsor);
    }

    public void delete(int id) {
        sponsorMapper.delete(id);
    }

    public void insert(Sponsor sponsor) throws Exception {
        sponsorMapper.insert(sponsor);
    }

    public List<Sponsor> selectPage(Pagination pagination) {
        pagination.setRecordCount(sponsorMapper.selectCount(pagination));
        return sponsorMapper.selectPage(pagination);
    }

}
