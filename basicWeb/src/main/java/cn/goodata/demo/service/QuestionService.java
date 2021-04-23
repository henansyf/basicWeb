package cn.goodata.demo.service;

import cn.goodata.demo.dao.QuestionDao;
import cn.goodata.demo.dao.RoleDao;
import cn.goodata.demo.entity.Question;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class QuestionService extends ServiceImpl<QuestionDao,Question> implements IService<Question> {



    @Transactional
    public void saveAll(){
        Question q1 = new Question();
        Date now= new Date();
        q1.setTitle("你好");
        q1.setCreateTime(now);
        q1.setItemNo(1);
        getBaseMapper().insert(q1);
        System.out.println("==========a1="+q1.getId());
        Question q2 = new Question();
        q2.setTitle("你好2");
        getBaseMapper().insert(q2);
        System.out.println("==========a2="+q2.getId());
    }

    public Question getOne(){
        //按条件查出数量
        QueryWrapper<Question> queryWrapper = new QueryWrapper<Question>();
      //  queryWrapper.eq("item_no",3);//相等
      //  queryWrapper.ne("item_no",3);//不等于
      //  queryWrapper.lt("type",3);//小于
      // queryWrapper.gt("type",4);//大于
     //   queryWrapper.le("type",3);//小于等于
      //  queryWrapper.ge("type",3);//大于等于
     //   queryWrapper.like("title", "张").lt("type", 40); //title like ‘%张%’ and type<40
     //   queryWrapper.likeLeft("title", "张");//title like ‘%张’
     //   queryWrapper.likeRight("title", "张");//title like ‘张%’
        // queryWrapper.in("type", Arrays.asList("2","5"));
      //  queryWrapper.notIn("type",Arrays.asList("2","5"));
      //  queryWrapper.between("type",4,6);
       // queryWrapper.isNull("type");
      //   queryWrapper.isNotNull("type");
      //  queryWrapper.orderByAsc("type");
      //  queryWrapper.orderByDesc("type");
      //  Question question = getBaseMapper().selectOne(queryWrapper);
      //   List<Question> list = getBaseMapper().selectList(queryWrapper);
       //   int count = getBaseMapper().selectCount(queryWrapper);
       // System.out.println("count============"+count.getId());
        return null;
    }


    public String getPager(){
        QueryWrapper<Question> queryWrapper = new QueryWrapper<Question>();
        queryWrapper.eq("item_no",3);
        Page page = new Page(1,10);
        getBaseMapper().getPager(page,queryWrapper);
        System.out.println(page.getTotal());
        return null;
    }

}
