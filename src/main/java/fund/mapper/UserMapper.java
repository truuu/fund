package fund.mapper;


import fund.dto.*;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;		
 		
 public interface UserMapper {		
     User selectById(int id);		
     
 }

