package com.litchi.cloud.iot.system.controller;


import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.litchi.cloud.iot.system.service.IUserService;
import com.litchi.cloud.iot.system.vo.UserPwdVO;
import com.litchi.cloud.iot.system.vo.UserVO;
import com.litchi.iot.common.beans.MyPage;
import com.litchi.iot.common.result.PageResult;
import com.litchi.iot.common.result.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wjhf
 * @since 2019-08-08
 */
@RestController
@RequestMapping("/user")
@Api(value = "UserController|用户操作相关的控制器", tags= {"用户操作相关接口"})
public class UserController {
	
	@Resource
	private IUserService userService;

	@ApiOperation(value="新建用户", notes="根据UserVO新建用户")
	@ApiImplicitParam(name="UserVO", value="用户类VO", required=true, dataType="UserVO")
	@PostMapping
	public Result<String> save(@RequestBody UserVO userVO) {
		return userService.save(userVO);
	}
	
	@ApiOperation(value="更新用户", notes="根据UserVO更新用户")
	@ApiImplicitParam(name="UserVO", value="用户类VO", required=true, dataType="UserVO")
	@PutMapping
	public Result<String> update(@RequestBody UserVO userVO) {
		return userService.update(userVO);
	}

	@ApiOperation(value="删除用户", notes="根据id删除用户")
	@ApiImplicitParam(name="id", value="用户id", required=true, dataType="int", paramType="path")
	@DeleteMapping("/{id}")
	public Result<String> delete(@PathVariable Integer id) {
		return userService.delete(id);
	}

	@ApiOperation(value="获取用户信息", notes="根据id获取用户信息")
	@ApiImplicitParam(name="id", value="用户id", required=true, dataType="int", paramType="path")
	@GetMapping("/{id}")
	public Result<UserVO> getById(@PathVariable Integer id) {
		return userService.getUserById(id);
	}

	@ApiOperation(value="分页获取用户信息", notes="分页获取用户信息")
	@ApiImplicitParam(name="UserVO", value="用户类VO", dataType="UserVO")
	@PostMapping("/page")
	public PageResult<UserVO> getPageList(@RequestBody MyPage search) {
		return userService.getPageList(search);
	}

    /**
       * 授权
     *
     * @param userId 用户ID
     * @param userVO
     * @return
     */
    @PutMapping(value = "/grant/{userId}")
    public Result grant(@PathVariable("userId") Integer userId, @RequestBody UserVO userVO) {
        Set<Integer> roleIdSet = userVO.getRoleIdSet();
        if (roleIdSet == null || roleIdSet.size() <= 0) {
            return Result.error("角色不能为空");
        }
        userService.grant(userId, roleIdSet);
        return Result.ok("授权成功！");
    }
    
    /**
     * 获取权限
     *
     * @param userId 用户ID
     * @return
     */
    @GetMapping(value = "/role/{userId}")
    public Result<List<Integer>> getUserRole(@PathVariable("userId") Integer userId) {
        List<Integer> userRoles = userService.getUserRoles(userId);
        return Result.ok(userRoles);
    }

    /**
     * 修改密码
     *
     * @return
     */
    @PutMapping("/modifyPwd/{userId}")
    public Result<String> modifyPassword(@PathVariable("userId") Integer userId, @RequestBody UserPwdVO vo) {
        // 修改密码
        return userService.updatePwd(userId, vo.getOldPwd(), vo.getNewPwd());
    }

    /**
     * 重置密码
     *
     * @return
     */
    @PutMapping("/resetPwd/{userId}")
    public Result<String> resetPassword(@PathVariable("userId") Integer userId) {
        return userService.resetPwd(userId);
    }
}

