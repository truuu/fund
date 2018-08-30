package fund.mapper;

import java.util.List;

import fund.dto.SponsorEvent;

public interface SponsorEventMapper {
    List<SponsorEvent> selectBySponsorId(int sponsorId);
    SponsorEvent selectById(int id);
    void insert(SponsorEvent event);
    void update(SponsorEvent event);
    void delete(int id);

}
