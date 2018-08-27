package com.spf.wealth.wealth;

import com.spf.utils.es.ElasticSearchUtil;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-04-12 17:17
 */
@RestController
public class IndexController {

    @RequestMapping("index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }


    @RequestMapping("test")
    public String test(){
        return "hello word";
    }

}
