import com.qhyu.cloud.config.StartConfig;
import com.qhyu.cloud.model.SkyworthUser;
import com.qhyu.cloud.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * All rights Reserved, Designed By http://xnky.travelsky.net/ <br>
 * Title：MybatisQhyuApplication <br>
 * Package：PACKAGE_NAME <br>
 * Copyright © 2023 xnky.travelsky.net Inc. All rights reserved. <br>
 * Company：Aviation Cares Of Southwest Chen Du LTD  <br>
 * @author 于琦海 <br>
 * date 2023年 03月24日 10:19 <br>
 * @version v1.0 <br>
 */
public class MybatisQhyuApplication {
    public static void main(String[] args) {
        // 初始化容器用的
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
            new AnnotationConfigApplicationContext(StartConfig.class);
        UserService bean = annotationConfigApplicationContext.getBean(UserService.class);
        SkyworthUser userInfoById = bean.getUserInfoById("0381321c-089b-43ef-b5d5-e4556c5670e9");
        //bean.updateId("0381321c-089b-43ef-b5d5-e4556c5670e9");
        System.out.println(userInfoById.toString());
    }
}
