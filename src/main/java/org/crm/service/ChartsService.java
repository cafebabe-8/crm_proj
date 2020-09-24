package org.crm.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ChartsService {
    List<Map<String, Object>> queryTranCount();

}
