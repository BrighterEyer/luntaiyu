package com.zhangzhao.web.service.impl;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.InvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 发票
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InvoiceServiceImpl extends BaseService implements InvoiceService {

}
