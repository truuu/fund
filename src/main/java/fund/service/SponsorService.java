package fund.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fund.dto.Pagination;
import fund.dto.Sponsor;
import fund.mapper.SponsorMapper;

@Service
public class SponsorService {

    @Autowired SponsorMapper sponsorMapper;

    private static String[] splitAddress(String address) {
        String[] result = new String[] { "", "", "" };
        if (address == null) return result;
        String[] temp = address.split("\\*");
        for (int i = 0; i < temp.length; ++i)
            result[i] = temp[i];
        return result;
    }

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

    private static void splitHomeAddress(Sponsor sponsor) {
        String[] a = SponsorService.splitAddress(sponsor.getHomeAddress());
        sponsor.setHomeRoadAddress(a[0]);
        sponsor.setHomeDetailAddress(a[1]);
        sponsor.setHomePostCode(a[2]);
    }

    private static void joinHomeAddress(Sponsor sponsor) {
        String s0 = sponsor.getHomeRoadAddress();
        String s1 = sponsor.getHomeDetailAddress();
        String s2 = sponsor.getHomePostCode();
        String s = s0 + "*" + s1 + "*" + s2;
        sponsor.setHomeAddress(s);
    }

    private static void splitOfficeAddress(Sponsor sponsor) {
        String[] a = SponsorService.splitAddress(sponsor.getOfficeAddress());
        sponsor.setOfficeRoadAddress(a[0]);
        sponsor.setOfficeDetailAddress(a[1]);
        sponsor.setOfficePostCode(a[2]);
    }

    private static void joinOfficeAddress(Sponsor sponsor) {
        String s0 = sponsor.getOfficeRoadAddress();
        String s1 = sponsor.getOfficeDetailAddress();
        String s2 = sponsor.getOfficePostCode();
        String s = s0 + "*" + s1 + "*" + s2;
        sponsor.setOfficeAddress(s);
    }

    public Sponsor selectById(int id) throws Exception {
        Sponsor sponsor = sponsorMapper.selectById(id);
        SponsorService.decryptJuminNo(sponsor);
        SponsorService.splitHomeAddress(sponsor);
        SponsorService.splitOfficeAddress(sponsor);
        return sponsor;
    }

    public void update(Sponsor sponsor) throws Exception {
        SponsorService.encryptJuminNo(sponsor);
        SponsorService.joinHomeAddress(sponsor);
        SponsorService.joinOfficeAddress(sponsor);
        //sponsorMapper.update(sponsor);
    }

    public void delete(int id) {
        //sponsorMapper.delete(id);
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
