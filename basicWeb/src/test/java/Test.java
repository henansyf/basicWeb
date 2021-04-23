import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        String a="[\"ROLE_AA\",\"ROLE_Z\"]";
        JSONArray jo = JSONObject.parseArray(a);
        List<String> authorities=   JSONObject.parseArray(a,String.class);

        System.out.println(StringUtils.isBlank(a));
    }
}
