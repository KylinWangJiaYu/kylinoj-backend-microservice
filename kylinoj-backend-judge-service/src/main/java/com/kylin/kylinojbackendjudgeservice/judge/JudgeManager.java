package com.kylin.kylinojbackendjudgeservice.judge;

import com.kylin.kylinojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.kylin.kylinojbackendjudgeservice.judge.strategy.JavaLanguageJudgeStrategy;
import com.kylin.kylinojbackendjudgeservice.judge.strategy.JudgeContext;
import com.kylin.kylinojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.kylin.kylinojbackendmodel.model.codesandbox.JudgeInfo;
import com.kylin.kylinojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理 简化调用
 */
@Service
public class JudgeManager {
    //定义JudgeManager，目的是尽量简化对判题功能的调用，让调用方最简单
    //做了一层封装

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        //修改判题策略
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
