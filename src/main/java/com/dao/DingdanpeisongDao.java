package com.dao;

import com.entity.DingdanpeisongEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DingdanpeisongVO;
import com.entity.view.DingdanpeisongView;


/**
 * 订单配送
 * 
 * @author 
 * @email 
 * @date 2023-02-10 19:54:46
 */
public interface DingdanpeisongDao extends BaseMapper<DingdanpeisongEntity> {
	
	List<DingdanpeisongVO> selectListVO(@Param("ew") Wrapper<DingdanpeisongEntity> wrapper);
	
	DingdanpeisongVO selectVO(@Param("ew") Wrapper<DingdanpeisongEntity> wrapper);
	
	List<DingdanpeisongView> selectListView(@Param("ew") Wrapper<DingdanpeisongEntity> wrapper);

	List<DingdanpeisongView> selectListView(Pagination page,@Param("ew") Wrapper<DingdanpeisongEntity> wrapper);
	
	DingdanpeisongView selectView(@Param("ew") Wrapper<DingdanpeisongEntity> wrapper);
	

}
