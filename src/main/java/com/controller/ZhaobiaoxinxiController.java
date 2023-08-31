package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.ZhaobiaoxinxiEntity;
import com.entity.view.ZhaobiaoxinxiView;

import com.service.ZhaobiaoxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 招标信息
 * 后端接口
 * @author 
 * @email 
 * @date 2023-02-10 19:54:46
 */
@RestController
@RequestMapping("/zhaobiaoxinxi")
public class ZhaobiaoxinxiController {
    @Autowired
    private ZhaobiaoxinxiService zhaobiaoxinxiService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhaobiaoxinxiEntity zhaobiaoxinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yiyuan")) {
			zhaobiaoxinxi.setYiyuanzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("gongyingshang")) {
			zhaobiaoxinxi.setGongyingshangzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ZhaobiaoxinxiEntity> ew = new EntityWrapper<ZhaobiaoxinxiEntity>();

		PageUtils page = zhaobiaoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhaobiaoxinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZhaobiaoxinxiEntity zhaobiaoxinxi, 
		HttpServletRequest request){
        EntityWrapper<ZhaobiaoxinxiEntity> ew = new EntityWrapper<ZhaobiaoxinxiEntity>();

		PageUtils page = zhaobiaoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhaobiaoxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhaobiaoxinxiEntity zhaobiaoxinxi){
       	EntityWrapper<ZhaobiaoxinxiEntity> ew = new EntityWrapper<ZhaobiaoxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhaobiaoxinxi, "zhaobiaoxinxi")); 
        return R.ok().put("data", zhaobiaoxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhaobiaoxinxiEntity zhaobiaoxinxi){
        EntityWrapper< ZhaobiaoxinxiEntity> ew = new EntityWrapper< ZhaobiaoxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhaobiaoxinxi, "zhaobiaoxinxi")); 
		ZhaobiaoxinxiView zhaobiaoxinxiView =  zhaobiaoxinxiService.selectView(ew);
		return R.ok("查询招标信息成功").put("data", zhaobiaoxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhaobiaoxinxiEntity zhaobiaoxinxi = zhaobiaoxinxiService.selectById(id);
        return R.ok().put("data", zhaobiaoxinxi);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhaobiaoxinxiEntity zhaobiaoxinxi = zhaobiaoxinxiService.selectById(id);
        return R.ok().put("data", zhaobiaoxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhaobiaoxinxiEntity zhaobiaoxinxi, HttpServletRequest request){
    	zhaobiaoxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zhaobiaoxinxi);
        zhaobiaoxinxiService.insert(zhaobiaoxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhaobiaoxinxiEntity zhaobiaoxinxi, HttpServletRequest request){
    	zhaobiaoxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zhaobiaoxinxi);
        zhaobiaoxinxiService.insert(zhaobiaoxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhaobiaoxinxiEntity zhaobiaoxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhaobiaoxinxi);
        zhaobiaoxinxiService.updateById(zhaobiaoxinxi);//全部更新
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhaobiaoxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<ZhaobiaoxinxiEntity> wrapper = new EntityWrapper<ZhaobiaoxinxiEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yiyuan")) {
			wrapper.eq("yiyuanzhanghao", (String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("gongyingshang")) {
			wrapper.eq("gongyingshangzhanghao", (String)request.getSession().getAttribute("username"));
		}

		int count = zhaobiaoxinxiService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	









}
