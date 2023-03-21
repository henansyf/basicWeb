package cn.goodata.demo.service;

import java.util.ArrayList;
import java.util.List;

import cn.goodata.demo.dao.ResourceDao;
import cn.goodata.demo.entity.Question;
import cn.goodata.demo.entity.Resource;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;




@Service
public class ResourceService extends ServiceImpl<ResourceDao, Resource> {
  //查询所
  public String getTreePanelList(){
		  QueryWrapper<Resource> wrapper = new QueryWrapper<Resource>();
		  wrapper.eq("parentId",0);
	      wrapper.orderByAsc("sort");
		  List<Resource>  list = getBaseMapper().selectList(wrapper);
		  return  JSONArray.toJSONString(list);
	  }

  public String getTreeNodeList(Integer id){
	  QueryWrapper<Resource> wrapper = new QueryWrapper<Resource>();
	  wrapper.eq("parentId",id);

	  wrapper.orderByAsc("sort");
	  List<Resource>  list = getBaseMapper().selectList(wrapper);
	  return  JSONArray.toJSONString(list);
  }
  
/*  public String getTree(){
	  List<Resource> all = resourceDao.getTreePanelList();
	  JSONArray jo = new JSONArray();
	  return nodes(all,"",jo).toString();
  }
  public JSONArray nodes(List<Resource> tree,String str,JSONArray jo){
	  Resource r;
	  List<Resource> nodes;
	  if(jo.size()!=0){
		  str+="------";
	  }
	  for (int i = 0; i < tree.size(); i++) {
			 r= tree.get(i);
			 r.setText(str+r.getText());
			 jo.add(r);
			 nodes=resourceDao.getTreeNodeList(r.getId());
			 if(nodes.size()>0){
				 nodes(nodes,str,jo);
			 }
			
		  }
	  return jo;
  }
  */
}