package cn.goodata.demo.dao;

import cn.goodata.demo.entity.Role;
import cn.goodata.demo.entity.Users;
import cn.goodata.demo.vo.Pager;
import cn.goodata.demo.vo.UserBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;


public interface UsersDao extends BaseMapper<Users> {
    @Select("select t.*,tr.roleName from t_users t left join t_role tr on t.roleId = tr.id   ${ew.customSqlSegment}")
    Page<Map> searchPager(Pager pager,@Param(Constants.WRAPPER) QueryWrapper queryWrapper);


}
