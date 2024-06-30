package com.kylin.kylinojbackendjudgeservice.judge.strategy;

import com.kylin.kylinojbackendmodel.model.codesandbox.JudgeInfo;
import com.kylin.kylinojbackendmodel.model.dto.question.JudgeCase;
import com.kylin.kylinojbackendmodel.model.entity.Question;
import com.kylin.kylinojbackendmodel.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 用于定义在策略中传递的参数
 */
@Data
public class JudgeContext {
    private JudgeInfo judgeInfo;
    private List<JudgeCase> judgeCaseList;
    private List<String> inputList;
    private List<String> outputList;
    private Question question;
    private QuestionSubmit questionSubmit;
}
