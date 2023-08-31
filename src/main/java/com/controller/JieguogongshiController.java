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

import com.entity.JieguogongshiEntity;
import com.entity.view.JieguogongshiView;

import com.service.JieguogongshiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 结果公示
 * 后端接口
 * @author 
 * @email 
 * @date 2023-02-10 19:54:46
 */
@RestController
@RequestMapping("/jieguogongshi")
public class JieguogongshiController {
    @Autowired
    private JieguogongshiService jieguogongshiService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JieguogongshiEntity jieguogongshi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yiyuan")) {
			jieguogongshi.setYiyuanzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("gongyingshang")) {
			jieguogongshi.setGongyingshangzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<JieguogongshiEntity> ew = new EntityWrapper<JieguogongshiEntity>();

		PageUtils page = jieguogongshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jieguogongshi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JieguogongshiEntity jieguogongshi, 
		HttpServletRequest request){
        EntityWrapper<JieguogongshiEntity> ew = new EntityWrapper<JieguogongshiEntity>();

		PageUtils page = jieguogongshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jieguogongshi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JieguogongshiEntity jieguogongshi){
       	EntityWrapper<JieguogongshiEntity> ew = new EntityWrapper<JieguogongshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jieguogongshi, "jieguogongshi")); 
        return R.ok().put("data", jieguogongshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JieguogongshiEntity jieguogongshi){
        EntityWrapper< JieguogongshiEntity> ew = new EntityWrapper< JieguogongshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jieguogongshi, "jieguogongshi")); 
		JieguogongshiView jieguogongshiView =  jieguogongshiService.selectView(ew);
		return R.ok("查询结果公示成功").put("data", jieguogongshiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JieguogongshiEntity jieguogongshi = jieguogongshiService.selectById(id);
        return R.ok().put("data", jieguogongshi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JieguogongshiEntity jieguogongshi = jieguogongshiService.selectById(id);
        return R.ok().put("data", jieguogongshi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JieguogongshiEntity jieguogongshi, HttpServletRequest request){
    	jieguogongshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jieguogongshi);
        jieguogongshiService.insert(jieguogongshi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JieguogongshiEntity jieguogongshi, HttpServletRequest request){
    	jieguogongshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jieguogongshi);
        jieguogongshiService.insert(jieguogongshi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JieguogongshiEntity jieguogongshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jieguogongshi);
        jieguogongshiService.updateById(jieguogongshi);//全部更新
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jieguogongshiService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<JieguogongshiEntity> wrapper = new EntityWrapper<JieguogongshiEntity>();
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

		int count = jieguogongshiService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	









}
