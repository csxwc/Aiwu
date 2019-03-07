package com.aiwu.service;

import com.aiwu.bean.User;
import com.aiwu.bean.UserDetailsImpl;
import com.aiwu.repository.UserRepository;
import com.aiwu.utils.EmailTool;
import com.aiwu.utils.RandomCodeTool;
import com.aiwu.utils.TimeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailTool emailTool = new EmailTool();

    private TimeTool timeTool = new TimeTool();

    private RandomCodeTool randomCodeTool = new RandomCodeTool();

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public Iterable<User> getAllUsers() {
        return  userRepository.findAll();
    }

    @Transactional
    public void deleteUserById(Integer id) { userRepository.deleteById(id); }

    public User getUserById(Integer id) { return userRepository.findById(id).isPresent()? userRepository.findById(id).get() : null; }

    @Transactional
    public User getByUserName(String username) {

        User user = userRepository.findByUsername(username);
        return user;
    }

    @Transactional
    public String sendCheckCode(String email) {
        String code = randomCodeTool.getRandomCode();
        emailTool.sendCodeToEmail(email, code);

        System.out.println(email);
        System.out.println(code);
        return code;
    }

    @Transactional
    public void insertNewUser(String username, String email, String password, String desc, String date, String gender) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        // 密码加密
        String pwdEncode = bCryptPasswordEncoder.encode(password);
        user.setPassword(pwdEncode);
        user.setDescription(desc);
        date = date.replace("T16:00:00.000Z", "");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            user.setBirthday(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setSex(gender);
        user.setMoney(0);
        user.setRent_num(0);
        // setid不加会报错
        user.setId(0);

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = getByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("没有该用户");
        }

        return new UserDetailsImpl(user);
    }


    @Transactional
    public Integer checkUser(String email, String password)
    {
        if(userRepository.findByEmail(email) == null) {
            return -1;
        }
        User user  = userRepository.findByEmail(email);
        if(bCryptPasswordEncoder.matches(password, user.getPassword()))
        {
            //request.getSession().setAttribute("user",name);
            return user.getId();
        }
        else
            return -1;
    }

    @Transactional
    public User getuserinfo(int userid)
    {
        User user = userRepository.findAllById(userid);
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        List<String> amap = new ArrayList<>();
//        amap.add(user.getUsername());
//        amap.add(String.valueOf(user.getId()));
//        amap.add(user.getSex());
//        amap.add(user.getPhone());
//        amap.add(user.getEmail());
//        amap.add(user.getDescription());
//        amap.add(sdf.format(user.getBirthday()));
//        amap.add(String.valueOf(user.getMoney()));
//        amap.add(user.getIdentification());
//        amap.add(user.getRegister_time());
//        amap.add(String.valueOf(user.getRent_num()));



        return user;
    }
}
