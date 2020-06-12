package com.etoak;

import com.etoak.bean.User2;
import com.etoak.cxf.service.User;
import com.etoak.cxf.service.UserService;
import com.etoak.service.UserService2;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@SpringBootApplication
@EnableScheduling
@Controller
@MapperScan(basePackages = "com.etoak.mapper")
public class SpringTaskApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringTaskApp.class,args);
    }

    @Autowired
    UserService2 userService;



    @Scheduled(cron = "0/20 * * * * ?")
    public void list(){
          // JaxWsProxyFactoryBean
          JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
          factory.setAddress("http://192.168.1.211:8000/ws/user?wsdl");
          factory.setServiceClass(UserService.class);
          UserService service = (UserService) factory.create();
          List<User> list = service.list();

        if(!CollectionUtils.isEmpty(list)) {
            for (User x : list) {
                if ((!StringUtils.isEmpty(x.getName()))&&(!StringUtils.isEmpty(x.getIdentity()))&&
                        (!StringUtils.isEmpty(x.getPhone()))&&("呼吸困难腹泻".equals(x.getState())||"普通感冒".equals(x.getState())||"正常".equals(x.getState()))
                        &&(!StringUtils.isEmpty(x.getAddress()))&&(x.getAddress().length()<=20)){
                    if (x.getInflow()==1&&(!StringUtils.isEmpty(x.getFrom()))&&(!StringUtils.isEmpty(x.getReturnDate()))){
                        com.etoak.bean.User zc = new com.etoak.bean.User(x.getAddress(),x.getFrom(),x.getIdentity()
                                ,x.getInflow(),x.getName(),x.getPhone(),x.getReturnDate(),x.getState(),x.getTemperature());
                        userService.add(zc);
                    }else if(x.getInflow()==2) {
                        com.etoak.bean.User zc = new com.etoak.bean.User(x.getAddress(), x.getFrom(), x.getIdentity()
                                , x.getInflow(), x.getName(), x.getPhone(), x.getReturnDate(), x.getState(), x.getTemperature());
                        userService.add(zc);
                    }else {
                        User2 bzc = new User2(x.getAddress(), x.getFrom(), x.getIdentity()
                                , x.getInflow(), x.getName(), x.getPhone(), x.getReturnDate(), x.getState(), x.getTemperature());
                        userService.add2(bzc);
                    }
                }else {
                    User2 bzc = new User2(x.getAddress(), x.getFrom(), x.getIdentity()
                            , x.getInflow(), x.getName(), x.getPhone(), x.getReturnDate(), x.getState(), x.getTemperature());
                    userService.add2(bzc);
                }
            }
        }
         /*for(User user:list){
            if (user.getName().length()<=4 && StringUtils.isIDNumber(user.getIdentity()) && StringUtils.IsPhone(user.getPhone())
            && StringUtils.isState(user.getState()) && (user.getInflow()==1&&user.getReturnDate()!=null) && (user.getInflow()==1&&user.getFrom()!=null)){
                userService.add(user);
            }else{
                userService.add2(user);
            }
         }*/

          System.out.println(list);
          //return list;
    }


}
