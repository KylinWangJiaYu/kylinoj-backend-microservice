package com.kylin.kylinoijbackendserviceclient.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kylin.kylinojbackendmodel.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.kylin.kylinojbackendmodel.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.kylin.kylinojbackendmodel.model.entity.QuestionSubmit;
import com.kylin.kylinojbackendmodel.model.entity.User;
import com.kylin.kylinojbackendmodel.model.vo.QuestionSubmitVO;

/**
* @author 97903
* @description 针对表【question_submit(题目提交信息表)】的数据库操作Service
* @createDate 2024-06-06 18:43:48
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser
     * @return 提交记录id
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
