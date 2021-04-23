package cn.goodata.demo.dao;


import cn.goodata.demo.entity.Question;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;


public interface QuestionDao  extends BaseMapper<Question> {

     @Select("select t.*,t2.name from t_test t left join t_group t2 on t.type=t2.id ${ew.customSqlSegment}")
     Page<Map> getPager(Page page,@Param(Constants.WRAPPER) QueryWrapper queryWrapper);

}
