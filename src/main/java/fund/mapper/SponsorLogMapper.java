package fund.mapper;

import java.util.List;

import fund.dto.SponsorLog;

public interface SponsorLogMapper {
    SponsorLog selectById(int id);
    List<SponsorLog> selectBySponsorId(int sponsorId);
    void insert(SponsorLog log);
    void delete(int id);
}
