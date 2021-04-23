package cn.goodata.demo;

import cn.goodata.demo.entity.Question;
import cn.goodata.demo.service.QuestionService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


//@ServletComponentScan("cn.goodata.demo.filter")

@SpringBootApplication
//@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan("cn.goodata.demo.dao")
@RestController
public class DemoApplication {
	private static  Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
    private QuestionService questionService;

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		//List<Question> list = questionDao.getAll();
		//logger.info("输入了输入sdfs了！！！");
		questionService.saveAll();
		return String.format("He王vh %s!", name);
	}

	@GetMapping("/save")
	public String save() {
		Question q1 = new Question();
		q1.setTitle("abc");
		q1.setCreateTime(new Date());
		questionService.save(q1);
		return "hello save";
	}

	@GetMapping("/get")
	public String get() {
		Question q1 =questionService.getById(35);
		return "hello get";
	}
	@GetMapping("/update")
	public String update() {
		Question q  = questionService.getById(35);
		q.setTitle("girl1");
		questionService.updateById(q);
		return "hello update";
	}
	@GetMapping("/delete")
	public String delete() {
		questionService.removeById(35);
		return "hello delete";
	}
	@GetMapping("/geta")
	public String geta() {
		questionService.getOne();
		return "hello geta";
	}
	@GetMapping("/getPager")
	public String getPager() {
		questionService.getPager();
		return "hello geta";
	}
}
