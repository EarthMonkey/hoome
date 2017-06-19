package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by marioquer on 2017/3/11.
 */

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView welcome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("common/welcome");
        return mv;
    }

    /*
    * user view
    */
    @RequestMapping(value = "/user/account", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView userAccount(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/account");
        return mv;
    }

    @RequestMapping(value = "/user/hotel", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView userHotel(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/hotel");
        return mv;
    }

    @RequestMapping(value = "/user/order", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView userOrder(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/order");
        return mv;
    }

    @RequestMapping(value = "/user/statistics", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView userStatistics(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/statistics");
        return mv;
    }

    /*
       landlord view
    */
    @RequestMapping(value = "/landlord/check-in-out", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView landlordCheckInOut(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("landlord/check-in-out");
        return mv;
    }

    @RequestMapping(value = "/landlord/hotel-info", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView landlordHotelInfo(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("landlord/hotel-info");
        return mv;
    }

    @RequestMapping(value = "/landlord/statistics", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView landlordStatistics(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("landlord/statistics");
        return mv;
    }

    @RequestMapping(value = "/landlord/special", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView landlordSpecial(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("landlord/special");
        return mv;
    }

    /*
    * boss view
    * */
    @RequestMapping(value = "/boss/apply", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView managerApply(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("boss/apply");
        return mv;
    }

    @RequestMapping(value = "/boss/pay", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView managerPay(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("boss/pay");
        return mv;
    }

    @RequestMapping(value = "/boss/statistics", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView managerStatistics(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("boss/statistics");
        return mv;
    }

    /*
   * landlord analysis
   * */
    @RequestMapping(value = "/boss/analysis-checkin", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView analysisCheckin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("boss/analysis-checkin");
        return mv;
    }

    @RequestMapping(value = "/boss/analysis-finance", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView analysisFinance(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("boss/analysis-finance");
        return mv;
    }

    @RequestMapping(value = "/boss/analysis-market", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView analysisMarket(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("boss/analysis-market");
        return mv;
    }

    @RequestMapping(value = "/boss/analysis-member", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView analysisMember(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("boss/analysis-member");
        return mv;
    }

    @RequestMapping(value = "/boss/analysis-order", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView analysisOrder(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("boss/analysis-order");
        return mv;
    }

}
