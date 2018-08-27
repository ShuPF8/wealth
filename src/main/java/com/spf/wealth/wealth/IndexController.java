package com.spf.wealth.wealth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-04-12 17:17
 */
@RestController
public class IndexController {

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("index")
    public ModelAndView index(){
        logger.info("index");
        return new ModelAndView("index");
    }


    @RequestMapping("test")
    public String test(){
        logger.info("test");
        return "hello word";
    }

}
