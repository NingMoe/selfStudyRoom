package cn.xdf.selfStudyRoom.service;

import java.util.List;
import cn.xdf.selfStudyRoom.domain.entity.Resources;

public interface ResourcesService {
	/**
     * 获取用户具有的权限
     * @param loginName
     * @return
     */
	List<Resources> getResourcesByLoginName(String loginName);
}
