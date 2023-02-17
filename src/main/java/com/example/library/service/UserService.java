package com.example.library.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.library.entity.Users;
import com.example.library.mapper.UserMapper;
import com.example.library.vo.PageIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *@program: library
 *@description: 用户业务类
 *@author: yubin
 *@create: 2023-02-16 15:45
 */
@Service
public class UserService  implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加用户
     * @param users 用户
     * @return 返回添加的用户
     */
    public boolean addUser(Users users) {
        return userMapper.insert(users)>0;
    }

    /**
     * 编辑用户
     * @param users 用户对象
     * @return true or false
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(Users users) {
        return userMapper.updateById(users)>0;
    }

    /**
     * 删除用户
     * @param id 主键
     * @return true or false
     */
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }

    /**
     * 用户名查询用户信息
     * @param username 用户名
     */
    public Users findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    /**
     *  根据用户id查询用户信息
     * @param id
     * @return
     */
    public Users userDetail(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 用户搜索查询(mybatis 分页)
     * @param pageIn
     * @return
     */
    public IPage<Users> getUserList(Page<Users> page, PageIn pageIn) {
        return userMapper.findListByLike(page,pageIn.getKeyword());
    }

    /**
     * 用户鉴权（重写WebSecurity鉴权方法）
     * @param username 用户名
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查找用户
        Users user = userMapper.findByUsername(username);
        // 获得角色
        String role = String.valueOf(user.getIsAdmin());
        // 角色集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 角色必须以`ROLE_`开头，数据库中没有，则在这里加
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        // 数据库密码是明文, 需要加密进行比对
        return new User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), authorities);
    }
}