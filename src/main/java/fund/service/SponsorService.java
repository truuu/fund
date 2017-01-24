package fund.service;

import fund.dto.Sponsor;

public class SponsorService {

    public static String[] splitAddress(String address) {
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

}
