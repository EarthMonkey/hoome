package controller;

import entity.BookRecord;
import entity.Room;
import entity.User;

import javax.servlet.http.HttpSession;

import entity.VipCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by marioquer on 2017/3/13.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    HttpSession session;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUser(Integer id) {
        Map<String, Object> userJson = new HashMap<String, Object>();
        User user = userService.getUser(id);
        userJson.put("state", "success");
        userJson.put("data", user);
        return userJson;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, String phone, String password) {
        Map<String, Object> result = userService.login(phone, password);
        Map<String, Object> json = new HashMap<>();
        User user = (User) result.get("user");
        session = request.getSession();
        System.out.println(result.get("state"));
        if (result.get("state").equals(Boolean.TRUE)) {
            if (session.getAttribute("user") == null)
                session.setAttribute("user", user);
            json.put("state", "success");
            json.put("role", user.getRole());
            return json;
        } else {
            json.put("state", "fail");
            return json;
        }
    }

    @RequestMapping(value = "/logup", method = RequestMethod.POST)
    @ResponseBody
    public String logup(String phone, String name, String password, byte role) {
        boolean result = userService.logup(phone, name, password, role);
        if (result)
            return "success";
        else
            return "fail";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public String logout(String id, HttpServletRequest request) {
        session = request.getSession();
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/newVip", method = RequestMethod.POST)
    @ResponseBody
    public String newVip(Integer id, HttpServletRequest request) {
        if (userService.newVip(id)) {
            User user = (User) request.getSession().getAttribute("user");
            request.getSession().removeAttribute("user");
            user.setIsVip((byte) 1);
            request.getSession().setAttribute("user", user);
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/activateVip", method = RequestMethod.POST)
    @ResponseBody
    public String activateVip(Integer id, Double balance, String bank_card, HttpServletRequest request) {
        if (userService.activateVip(id, balance, bank_card)) {
            User user = (User) request.getSession().getAttribute("user");
            request.getSession().removeAttribute("user");
            user.setIsVip((byte) 2);
            request.getSession().setAttribute("user", user);
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/topUp", method = RequestMethod.POST)
    @ResponseBody
    public String topUp(Integer id, Double money) {
        if (userService.topUp(id, money)) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/getMyVip", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getMyVip(Integer id) {
        return userService.getMyVip(id);
    }

    @RequestMapping(value = "/searchRooms", method = RequestMethod.POST)
    @ResponseBody
    public List<Room> searchRooms(String beginTime, String endTime, byte roomType) {
        return userService.searchRooms(beginTime, endTime, roomType);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    @ResponseBody
    public String bookRoom(Integer booker_id, Double price, Integer room_id, Integer hotel_id, byte room_style, byte pay_method, String target_in_time, String target_out_time, String hotel_name) {
        if (userService.bookRoom(booker_id, price, room_id, hotel_id, room_style, pay_method, target_in_time, target_out_time, hotel_name)) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/getMyOrder", method = RequestMethod.POST)
    @ResponseBody
    public List<BookRecord> getMyOrder(Integer id) {
        return userService.getMyOrder(id);
    }

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    @ResponseBody
    public String cancelOrder(Long order_id) {
        return userService.cancelOrder(order_id) ? "success" : "fail";
    }


    @RequestMapping(value = "/suspendCard", method = RequestMethod.POST)
    @ResponseBody
    public String suspendCard(Integer user_id, HttpServletRequest request) {
        if (userService.suspendCard(user_id)) {
            User user = (User) request.getSession().getAttribute("user");
            request.getSession().removeAttribute("user");
            user.setIsVip((byte) 3);
            request.getSession().setAttribute("user", user);
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/reactiveCard", method = RequestMethod.POST)
    @ResponseBody
    public String reactiveCard(Integer user_id, HttpServletRequest request) {
        if (userService.reactiveCard(user_id)) {
            User user = (User) request.getSession().getAttribute("user");
            request.getSession().removeAttribute("user");
            user.setIsVip((byte) 2);
            request.getSession().setAttribute("user", user);
            return "success";
        } else {
            return "fail";
        }
    }

    //test
    @RequestMapping(value = "/showSession", method = RequestMethod.GET)
    @ResponseBody
    public String show(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user.getPhone();
    }

}
