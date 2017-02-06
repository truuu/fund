package fund.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fund.dto.Commitment;
import fund.dto.Sponsor;
import fund.dto.pagination.Pagination;
import fund.mapper.SponsorMapper;

@Service
public class SponsorService {

    @Autowired SponsorMapper sponsorMapper;

    public static void decryptJuminNo(Sponsor sponsor) throws Exception {
        String s = sponsor.getJuminNo();
        if (s.length() < 20) throw new Exception("주민번호 에러");
        s = EncryptService.decAES(sponsor.getJuminNo());
        sponsor.setJuminNo(s);
    }

    public static void encryptJuminNo(Sponsor sponsor) throws Exception {
        String s = sponsor.getJuminNo();
        if (s == null) s = "";
        s = EncryptService.encAES(sponsor.getJuminNo());
        sponsor.setJuminNo(s);
    }

    public static  void decriptJuminNo(List<Commitment> list) throws Exception {
        for (Commitment c : list)
            c.setJuminNo(EncryptService.decAES(c.getJuminNo()));
    }

    public Sponsor selectById(int id) throws Exception {
        Sponsor sponsor = sponsorMapper.selectById(id);
        SponsorService.decryptJuminNo(sponsor);
        return sponsor;
    }

    public void update(Sponsor sponsor) throws Exception {
        SponsorService.encryptJuminNo(sponsor);
        sponsorMapper.update(sponsor);
    }

    public void delete(int id) {
        sponsorMapper.delete(id);
    }

    public void insert(Sponsor sponsor) throws Exception {
        SponsorService.encryptJuminNo(sponsor);
        sponsorMapper.insert(sponsor);
    }

    public List<Sponsor> selectPage(Pagination pagination) {
        pagination.setRecordCount(sponsorMapper.selectCount(pagination));
        return sponsorMapper.selectPage(pagination);
    }

    public List<Sponsor> encryptJuminNo() throws Exception {
        List<Sponsor> list = sponsorMapper.selectNotEncrypted();
        for (Sponsor sponsor : list) {
            encryptJuminNo(sponsor);
            sponsorMapper.updateJuminNo(sponsor);
        }
        return list;
    }

}
