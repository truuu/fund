package fund.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import fund.dto.Sponsor;
import fund.dto.pagination.Pagination;
import fund.mapper.SponsorMapper;

@Service
public class SponsorService {

    @Autowired SponsorMapper sponsorMapper;
    @Autowired @Qualifier("key1") String key1;

    public Sponsor selectById(int id) throws Exception {
        System.out.println(key1);
        return sponsorMapper.selectById(id, key1);
    }

    public void update(Sponsor sponsor) throws Exception {
        sponsor.setKey1(key1);
        sponsorMapper.update(sponsor);
    }

    public void delete(int id) {
        sponsorMapper.delete(id);
    }

    public void insert(Sponsor sponsor) throws Exception {
        sponsor.setKey1(key1);
        sponsorMapper.insert(sponsor);
    }

    public List<Sponsor> selectPage(Pagination pagination) {
        pagination.setRecordCount(sponsorMapper.selectCount(pagination));
        return sponsorMapper.selectPage(pagination);
    }

}
