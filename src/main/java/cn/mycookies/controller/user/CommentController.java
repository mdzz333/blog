package cn.mycookies.controller.user;

import cn.mycookies.common.base.BaseController;
import cn.mycookies.common.base.ServerResponse;
import cn.mycookies.pojo.dto.CommentAddRequest;
import cn.mycookies.pojo.dto.CommentListRequest;
import cn.mycookies.pojo.vo.CommentListItemVO;
import cn.mycookies.service.CommentService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 评论相关接口
 *
 * @author Jann Lee
 * @date 2018-11-20 12:20
 **/
@Api(description = "用户评论模块")
@RequestMapping("comments")
@CrossOrigin
@RestController
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    @ApiOperation(value = "添加评论")
    public ServerResponse addComment(@RequestBody CommentAddRequest commentAddRequest) {
        validate(commentAddRequest);
        return commentService.addCommentInfo(commentAddRequest);
    }

    @GetMapping("/{targetId:\\d+}")
    @ApiOperation(value = "获取评论列表", responseContainer = "PageInfo", response = CommentListItemVO.class)
    public ServerResponse<PageInfo<CommentListItemVO>> getCommentListInfos(@PathVariable Integer targetId, CommentListRequest commentListRequest) {

        return commentService.getCommentInfos(targetId, commentListRequest);
    }
    @PutMapping("/like/{commentId:\\d+}")
    @ApiOperation(value = "给评论点赞", responseContainer = "PageInfo", response = Boolean.class)
    public ServerResponse<String> addLikeCount(
           @ApiParam(value ="评论的id") @PathVariable(value = "commentId") Integer id){

        return commentService.addCommentLikeCount(id);
    }

}
