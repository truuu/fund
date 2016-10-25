package fund.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fund.mapper.EB13_CommitmentDetailMapper;

@Service
public class EB13_CommitmentDetailService {
	@Autowired EB13_CommitmentDetailMapper eb13_CommitmentDetailMapper;
}
