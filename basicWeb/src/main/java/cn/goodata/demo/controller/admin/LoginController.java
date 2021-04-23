package cn.goodata.demo.controller.admin;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import cn.goodata.demo.service.ResourceService;
import cn.goodata.demo.util.ValidateCodeImage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/alogin")
public class LoginController {


    @Autowired
    private ResourceService resourceService;



    @RequestMapping(value = "/index")
    public String login(Model model) {
        return "admin/index";
    }

    @RequestMapping(value = "/head")
    public String head(Model model) {

        return "admin/head";
    }

    @RequestMapping(value = "/center")
    public String center() {
        return "admin/center";
    }



    @RequestMapping(value = "/getUrl")
    @ResponseBody
    public String getUrl(String action,Integer id){
        if (action.equals("list")) {// 获取属面板列表
            return  resourceService.getTreePanelList();
        } else if (action.equals("node")) {
            return resourceService.getTreeNodeList(id);
        }
        return "";
    }

    @RequestMapping(value = "/getImage")
    public void getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ValidateCodeImage.makeImageSb(request,response);
    }




}
