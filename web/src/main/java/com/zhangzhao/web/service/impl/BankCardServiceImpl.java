package com.zhangzhao.web.service.impl;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.BankCardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 银行卡
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BankCardServiceImpl extends BaseService implements BankCardService {

}
