package com.zyyglxt.controller.govres;

import com.zyyglxt.annotation.LogAnnotation;
import com.zyyglxt.dataobject.FileDO;
import com.zyyglxt.dataobject.PostDO;
import com.zyyglxt.dataobject.PostDOKey;
import com.zyyglxt.dataobject.adviceDO;
import com.zyyglxt.dto.PostDto;
import com.zyyglxt.error.EmBusinessError;
import com.zyyglxt.response.ResponseData;
import com.zyyglxt.service.IAdviceService;
import com.zyyglxt.service.IFileService;
import com.zyyglxt.service.IPostService;
import com.zyyglxt.util.ConvertDOToDTOUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:wangzh
 * Date: 2020/12/19 22:38
 * Version: 1.0
 * 发文管理
 */
@RestController
@RequestMapping(value = "post")
@Api(tags = "发文管理")
public class PostController {
    @Resource
    IPostService postService;

    @Resource
    IFileService fileService;

    @Resource
    IAdviceService adviceService;

    @GetMapping(value = "/getPost")
    @ResponseBody
    @LogAnnotation(appCode ="",logTitle ="查询所有发文信息",logLevel ="1",creater ="",updater = "")
    public ResponseData getPost(@RequestParam(value = "postDataStatus") String postDataStatus){
        return new ResponseData(EmBusinessError.success,postService.getPost(postDataStatus));
    }

    @GetMapping(value = "/getByClerk")
    @ResponseBody
    @LogAnnotation(appCode ="",logTitle ="查询相应部门的对应科员的发文信息",logLevel ="1",creater ="",updater = "")
    public ResponseData getByClerk(@RequestParam(value = "postDepartment") String postDepartment){
        return new ResponseData(EmBusinessError.success,postService.selectClerk(postDepartment));
    }

    @GetMapping(value = "/getByDirector")
    @ResponseBody
    @LogAnnotation(appCode ="",logTitle ="查询相应部门的对应处长的发文信息",logLevel ="1",creater ="",updater = "")
    public ResponseData getByDirector(@RequestParam(value = "postDepartment") String postDepartment){
        return new ResponseData(EmBusinessError.success,postService.selectDirector(postDepartment));
    }

    @GetMapping(value = "/getSend")
    @ResponseBody
    @LogAnnotation(appCode ="",logTitle ="查询对应省市县部门或机构收到的发文信息",logLevel ="1",creater ="",updater = "")
    public ResponseData getSend(@RequestParam(value = "receiverId") String receiverId){
        return new ResponseData(EmBusinessError.success,postService.getSend(receiverId));
    }

    @GetMapping(value = "/getDeputyDirector")
    @ResponseBody
    @LogAnnotation(appCode ="",logTitle ="查询当前分管局长",logLevel ="1",creater ="",updater = "")
    public ResponseData getDeputyDirector(@RequestParam(value = "postOpinion1") String postOpinion1){
        return new ResponseData(EmBusinessError.success,postService.getDeputyDirector(postOpinion1));
    }

    @GetMapping(value = "/selectOne/{itemid}/{itemcode}")
    @ResponseBody
    @LogAnnotation(appCode ="",logTitle ="查询一个带文件的发文信息",logLevel ="1",creater ="",updater = "")
    public ResponseData getOnePost(@PathVariable Integer itemid, @PathVariable String itemcode){

        return new ResponseData(EmBusinessError.success,postService.selOneWithFile(itemid,itemcode));
    }

    @GetMapping(value = "/getPostFileForMain")
    @ResponseBody
    @LogAnnotation(appCode ="",logTitle ="查询所有发文附件信息",logLevel ="1",creater ="",updater = "")
    public ResponseData getPostFileForMain(){
        return new ResponseData(EmBusinessError.success,postService.getPostFileForMain());
    }

    @GetMapping(value = "/getPostForMainPage")
    @ResponseBody
    @LogAnnotation(appCode ="",logTitle ="查询所有发文标题信息",logLevel ="1",creater ="",updater = "")
    public ResponseData getPostForMainPage(@RequestParam String status){
        return new ResponseData(EmBusinessError.success,postService.getPostForMainPage(status));
    }

    @RequestMapping(value = "/createPost", method = RequestMethod.POST)
    @ResponseBody
    @LogAnnotation(appCode ="",logTitle ="添加发文信息",logLevel ="3",creater ="",updater = "")
    public ResponseData createPost(@RequestBody PostDO postDO){
        postService.createPost(postDO);
        return new ResponseData(EmBusinessError.success);
    }

    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    @ResponseBody
    @LogAnnotation(appCode ="",logTitle ="修改发文信息",logLevel ="2",creater ="",updater = "")
    public ResponseData updatePost(@RequestBody PostDO postDO) {
        postService.updatePost(postDO);
        return new ResponseData(EmBusinessError.success);
    }

    @RequestMapping(value = "/delPost", method = RequestMethod.DELETE)
    @ResponseBody
    @LogAnnotation(appCode ="",logTitle ="删除发文信息",logLevel ="4",creater ="",updater = "")
    public ResponseData delPost(PostDOKey key) {
        postService.delPost(key);
        return new ResponseData(EmBusinessError.success);
    }

    //查询文号的最大值
    @GetMapping(value = "/maxNum")
    @ResponseBody
    public ResponseData maxNum(){
        PostDO max = postService.maxNum();
        return new ResponseData(EmBusinessError.success,max);
    }

}
