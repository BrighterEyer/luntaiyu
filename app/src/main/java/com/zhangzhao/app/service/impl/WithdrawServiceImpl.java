package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.WithdrawService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 提现
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WithdrawServiceImpl extends BaseService implements WithdrawService {

}
