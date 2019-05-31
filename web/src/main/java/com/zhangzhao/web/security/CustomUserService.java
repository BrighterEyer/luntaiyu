package com.zhangzhao.web.security;

import com.zhangzhao.common.entity.admin.Admin;
import com.zhangzhao.common.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author zzq
 */
@Component
@Transactional
public class CustomUserService implements UserDetailsService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AdminMpper adminMpper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Admin> optional = adminRepository.findByName(s);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        AdminUser adminUser = adminMpper.adminToAdminUser(optional.get());
        return adminUser;
    }
}
