import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.qhyu.cloud.config.StartConfig;
import com.qhyu.cloud.dynamic.DynamicInterface;
import com.qhyu.cloud.mapper.AirBaseMapper;
import com.qhyu.cloud.model.AirBase;
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
        //transactionManager(annotationConfigApplicationContext);
        //dynamicTest(annotationConfigApplicationContext);
        pageTest(annotationConfigApplicationContext);
    }

    private static void pageTest(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
        UserService bean = annotationConfigApplicationContext.getBean(UserService.class);
        bean.getAll();
    }

    private static void dynamicTest(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
        DynamicInterface bean = annotationConfigApplicationContext.getBean(DynamicInterface.class);
        bean.test();
    }

    private static void transactionManager(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
        UserService bean = annotationConfigApplicationContext.getBean(UserService.class);
        AirBaseMapper airBaseMapper = annotationConfigApplicationContext.getBean(AirBaseMapper.class);
        LambdaQueryChainWrapper<AirBase> airBaseLambdaQueryChainWrapper = new LambdaQueryChainWrapper<>(airBaseMapper);
        Long one = airBaseLambdaQueryChainWrapper.count();
        SkyworthUser userInfoById = bean.getUserInfoById("0381321c-089b-43ef-b5d5-e4556c5670e9");
        if (userInfoById.getIsFirstLogin() == 0){
            bean.updateId("0381321c-089b-43ef-b5d5-e4556c5670e9",1);
        }else{
            bean.updateId("0381321c-089b-43ef-b5d5-e4556c5670e9",0);
        }

        System.out.println(userInfoById.toString());
    }
}
