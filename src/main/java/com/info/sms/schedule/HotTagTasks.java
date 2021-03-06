package com.info.sms.schedule;

import com.info.sms.cache.HotTagCache;
import com.info.sms.mapper.QuestionMapper;
import com.info.sms.model.Question;
import com.info.sms.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Luke 2020/7/24 10:09
 */
@Component
@Slf4j
public class HotTagTasks {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private HotTagCache hotTagCache;

    // @Scheduled(fixedRate = 100000)
    @Scheduled(fixedRate = 1000 * 60 * 60 * 3)
    // @Scheduled(cron = "0 0 1 * * *") 每日1点执行
    public  void hotTagSchedule(){
        int offset = 0;
        int limit = 5;
        log.info("hotTagSchedule start {}",new Date());

        List<Question> list = new ArrayList<>();

        Map<String,Integer> priorities = new HashMap<>();
        while (offset == 0 || list.size() == limit){
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,limit));

            for(Question question : list){

                String[] tags = StringUtils.split(question.getTag(),",");
                for(String tag : tags){
                    Integer priority = priorities.get(tag);
                    if(priority != null){
                        priorities.put(tag,priority + 5 + question.getCommentCount());
                    }else {
                        priorities.put(tag, 5 + question.getCommentCount());
                    }
                }
            }
            offset += limit;
        }

        hotTagCache.updateTags(priorities);
        log.info("hotTagSchedule stop {}",new Date());
    }
}
