package com.project.csr;

import cn.hutool.core.date.DateUnit;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.project.csr.model.po.ScoreChannelPo;
import com.project.csr.model.po.StorePo;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: bin.tong
 * @date: 2020/11/5 12:40
 **/
@Slf4j
public class DemoTests {

    @Test
    public void testArray(){
        List<StorePo> storePoList = new ArrayList<>();
        StorePo storePo = null;
         storePo = new StorePo();
        storePo.setId("1");
        storePoList.add(storePo);
        storePo = new StorePo();
        storePo.setId("2");
        storePoList.add(storePo);
        storePo = new StorePo();
        storePo.setId("3");
        storePoList.add(storePo);
        storePo = new StorePo();
        storePo.setId("4");
        storePoList.add(storePo);
        storePo = new StorePo();
        storePo.setId("5");
        storePoList.add(storePo);
        String aa = storePoList.stream().map(StorePo::getId).collect(Collectors.joining(","));
        log.info(aa);
    }
    @Test
    public void test() {
        String period = "202001";
        int amount = -1;
        try {
            String result = DateUtils.getMonth(period, amount, "yyyyMM");
            log.info(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConvert() {
        List<ScoreChannelPo> list = new ArrayList<>();
        ScoreChannelPo po = new ScoreChannelPo();
        for (int i = 1; i <= 4; i++) {
            po.setId(Integer.toString(i));
            po.setPeriod("202011");
            po.setScopeId(1);
            po.setName("全国");
            po.setChannelId(i);
            po.setScore("800");
            list.add(po);
        }
        // for (int i = 5; i <= 8; i++) {
        //     po.setId(Integer.toString(i));
        //     po.setPeriod("202011");
        //     po.setScopeId(1);
        //     po.setName("全国");
        //     po.setChannelId(i - 4);
        //     po.setScore("800");
        //     list.add(po);
        // }
        log.info(list.toString());
        // testConvert(list);
    }
    // private Map<String, Object> testConvert(List<ScoreChannelPo> list){
    //     Map<String, Object> map = new HashMap<>();
    //     for(int i=0;i<list.size();i++){
    //         ScoreChannelPo po = list.get(i);
    //         map.put(po.getChannelId(),po.getScore());
    //     }
    // }
}
