package com.kylin.kylinojbackendjudgeservice.judge.strategy;


import com.kylin.kylinojbackendmodel.model.codesandbox.JudgeInfo;

public interface JudgeStrategy {
    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
