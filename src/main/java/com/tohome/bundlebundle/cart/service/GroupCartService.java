package com.tohome.bundlebundle.cart.service;

import java.util.List;

import com.tohome.bundlebundle.cart.vo.GroupCartVO;
import com.tohome.bundlebundle.cart.vo.GroupVO;

public interface GroupCartService {

	List<GroupCartVO> checkMember(int groupId);

}
