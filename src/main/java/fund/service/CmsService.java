package fund.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fund.dto.Commitment;
import fund.mapper.CommitmentMapper;

@Service
public class CmsService {

    @Autowired CommitmentMapper commitmentMapper;

    public List<Commitment> selectEB13Candidate() {
        return commitmentMapper.selectEB13Candidate();
    }

    public List<Commitment> selectEB21Candidate(Map<String,Object> map) {
        return commitmentMapper.selectEB21Candidate(map);
    }

    public List<Commitment> selectCmsResult(Map<String,Object> map) {
        return commitmentMapper.selectCmsResult(map);
    }


}
