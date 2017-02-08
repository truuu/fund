package fund.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import fund.dto.Commitment;
import fund.mapper.CommitmentMapper;

@Service
public class CmsService {

    @Autowired CommitmentMapper commitmentMapper;
    @Autowired @Qualifier("key1") String key1;

    public List<Commitment> selectEB13Candidate() {
        return commitmentMapper.selectEB13Candidate(key1);
    }

    public List<Commitment> selectEB21Candidate(Map<String,Object> map) {
        map.put("key1", key1);
        return commitmentMapper.selectEB21Candidate(map);
    }

    public List<Commitment> selectCmsResult(Map<String,Object> map) {
        map.put("key1", key1);
        return commitmentMapper.selectCmsResult(map);
    }


}
