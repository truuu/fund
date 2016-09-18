package fund.mapper;

import java.util.*;
import fund.dto.Commitment;

public interface CommitmentMapper {
	List<Commitment> selectCommitmentBySponsorNo(String sponsorNo);
}
