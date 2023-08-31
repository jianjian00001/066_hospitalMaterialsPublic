package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CaigoudingdanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CaigoudingdanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CaigoudingdanView;


/**
 * 采购订单
 *
 * @author 
 * @email 
 * @date 2023-02-10 19:54:46
 */
public interface CaigoudingdanService extends IService<CaigoudingdanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CaigoudingdanVO> selectListVO(Wrapper<CaigoudingdanEntity> wrapper);
   	
   	CaigoudingdanVO selectVO(@Param("ew") Wrapper<CaigoudingdanEntity> wrapper);
   	
   	List<CaigoudingdanView> selectListView(Wrapper<CaigoudingdanEntity> wrapper);
   	
   	CaigoudingdanView selectView(@Param("ew") Wrapper<CaigoudingdanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CaigoudingdanEntity> wrapper);
   	

}

