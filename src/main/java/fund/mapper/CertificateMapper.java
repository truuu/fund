package fund.mapper;

import java.util.List;
import fund.dto.Certificate;
import fund.dto.pagination.Pagination;

public interface CertificateMapper {

  Certificate selectById(int id);
  List<Certificate> selectPage(Pagination pagination);
  int selectCount(Pagination pagination);
  void insert(Certificate certificate);
  void delete(int id);
  String generateCertificateNo(int type);
}
