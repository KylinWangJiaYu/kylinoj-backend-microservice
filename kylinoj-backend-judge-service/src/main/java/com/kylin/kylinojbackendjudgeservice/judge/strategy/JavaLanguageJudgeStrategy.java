package com.kylin.kylinojbackendjudgeservice.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.kylin.kylinojbackendmodel.model.codesandbox.JudgeInfo;
import com.kylin.kylinojbackendmodel.model.dto.question.JudgeCase;
import com.kylin.kylinojbackendmodel.model.dto.question.JudgeConfig;
import com.kylin.kylinojbackendmodel.model.entity.Question;
import com.kylin.kylinojbackendmodel.model.enums.JudgeInfoMessageEnum;

import java.util.List;
import java.util.Optional;

public class JavaLanguageJudgeStrategy implements JudgeStrategy {
    /**
     * Java程序判题策略
     *
     * @param judgeContext
     * @return
     */
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        System.out.println(judgeInfo);

        // 题目相关的信息：运行程序输入、运行程序输出、题目信息、判题用例
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        Question question = judgeContext.getQuestion();

        // 响应判题信息
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
//        Long memory = judgeInfo.getMemory();
        Long memory = Optional.ofNullable(judgeInfo.getMemory()).orElse(0L);
//        Long time = judgeInfo.getTime();
        Long time = Optional.ofNullable(judgeInfo.getTime()).orElse(0L);

        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setTime(time);

        //1.判断题目的限制是否符合要求
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        //Java程序本身需要额外执行 10s 判断时间限制时要加上10s
        Long needMemoryLimit = judgeConfig.getMemoryLimit();
        Long needTimeLimit = judgeConfig.getTimeLimit();
        System.out.println("1");
        if (memory > needMemoryLimit) {
            System.out.println("fuck");
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        System.out.println("2");
        long JAVA_PROGRAM_TIME_COST = 10000L;
        if ((time - JAVA_PROGRAM_TIME_COST) > needTimeLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        System.out.println("3");
        //2.检查异常情况
        //3.先判断代码沙箱执行的结果输出数量是否和预期数量相等
        if (outputList.size() != inputList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        System.out.println("4");
        //4.依次判断每一项输出和预期输出是否相等
        for (int i = 0; i < judgeCaseList.size(); i++) {
            JudgeCase judgeCase = judgeCaseList.get(i);
            if (!judgeCase.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }
        System.out.println("5");
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
    }
}
