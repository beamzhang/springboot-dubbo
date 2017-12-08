package com.springboot.dubbo.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springboot.dubbo.user.api.entity.User;
import com.springboot.dubbo.user.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Api(value = "users", description = "用户API")
@Slf4j
public class UserController {
	@Autowired
	private UserService service;

//	/**
//	 * mode 支持id、username、email、手机号 只有管理员或自己才可以查询某用户的完整信息
//	 *
//	 * @param key
//	 * @param mode
//	 *            id、username、email、手机号
//	 * @return
//	 */
//	@RequestMapping(value = "/query/{key}", method = RequestMethod.GET)
//	@PostAuthorize("hasRole('ADMIN') or (returnObject.username ==  principal.username)")
//	@ApiOperation(value = "按某属性查询用户", notes = "属性可以是id或username或email或手机号", response = User.class, authorizations = {
//			@Authorization("登录权限") })
//	@ApiResponses(value = { @ApiResponse(code = 401, message = "未登录"), @ApiResponse(code = 404, message = "查询模式未找到"),
//			@ApiResponse(code = 403, message = "只有管理员或用户自己能查询自己的用户信息"), })
//	public User findByKey(@PathVariable("key") @ApiParam(value = "查询关键字", required = true) String key,
//			@RequestParam("mode") @ApiParam(value = "查询模式，可以是id或username或phone或email", required = true) String mode) {
//
//		QueryUserHandler handler = SpringContextUtil.getBean("QueryUserHandler", StringUtils.lowerCase(mode));
//		if (handler == null) {
//			throw new QueryUserModeNotFoundException(mode);
//		}
//		UserDO userDO = handler.handle(key);
//		if (userDO == null) {
//			throw new UserNotFoundException(key);
//		}
//		return userDO;
//	}
//
//	@ResponseStatus(HttpStatus.CREATED)
//	@RequestMapping(method = RequestMethod.POST)
//	@ApiOperation(value = "创建用户，为用户发送验证邮件，等待用户激活，若24小时内未激活需要重新注册", response = Void.class)
//	@ApiResponses(value = { @ApiResponse(code = 409, message = "用户名已存在"),
//			@ApiResponse(code = 400, message = "用户属性校验失败") })
//	public void createUser(
//			@RequestBody @Valid @ApiParam(value = "用户信息，用户的用户名、密码、昵称、邮箱不可为空", required = true) UserDO user,
//			BindingResult result) {
//		log.info("{}", user);
//		if (isUsernameDuplicated(user.getUsername())) {
//			throw new UsernameExistedException(user.getUsername());
//		} else if (result.hasErrors()) {
//			throw new ValidationException(result.getFieldErrors());
//		}
//
//		// 生成邮箱的激活码
//		String activationCode = UUIDUtil.uuid();
//		// 保存用户
//		service.save(user);
//
//		verificationManager.createVerificationCode(activationCode, String.valueOf(user.getId()),
//				authenticationProperties.getActivationCodeExpireTime());
//		log.info("{}     {}", user.getEmail(), user.getId());
//		// 发送邮件
//		Map<String, Object> params = new HashMap<>();
//		params.put("id", user.getId());
//		params.put("activationCode", activationCode);
//		emailService.sendHTML(user.getEmail(), "activation", params, null);
//	}
//
//	@RequestMapping(value = "/{id}/avatar", method = RequestMethod.GET)
//	@ApiOperation(value = "获取用户的头像图片", response = Byte.class)
//	@ApiResponses(value = { @ApiResponse(code = 404, message = "文件不存在"), @ApiResponse(code = 400, message = "文件传输失败") })
//	public void getUserAvatar(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
//		String relativePath = service.findAvatarById(id);
//		FileUtil.download(relativePath, request.getServletContext(), response);
//	}
//
//	@RequestMapping(value = "/{id}/activation", method = RequestMethod.GET)
//	@ApiOperation(value = "用户激活，前置条件是用户已注册且在24小时内", response = Void.class)
//	@ApiResponses(value = { @ApiResponse(code = 401, message = "未注册或超时或激活码错误") })
//	public void activate(@PathVariable("id") @ApiParam(value = "用户Id", required = true) Long id,
//			@RequestParam("activationCode") @ApiParam(value = "激活码", required = true) String activationCode) {
//		UserDO user = service.findById(id);
//		// 获取Redis中的验证码
//		if (!verificationManager.checkVerificationCode(activationCode, String.valueOf(id))) {
//			verificationManager.deleteVerificationCode(activationCode);
//			throw new ActivationCodeValidationException(activationCode);
//		}
//		user.setUserStatus(UserStatus.ACTIVATED);
//		verificationManager.deleteVerificationCode(activationCode);
//		service.update(user);
//	}
//
//	// 更新
//	@RequestMapping(method = RequestMethod.PUT)
//	@PreAuthorize("#user.username == principal.username or hasRole('ADMIN')")
//	@ApiOperation(value = "更新用户信息", response = Void.class, authorizations = { @Authorization("登录权限") })
//	@ApiResponses(value = { @ApiResponse(code = 401, message = "未登录"), @ApiResponse(code = 404, message = "用户属性校验失败"),
//			@ApiResponse(code = 403, message = "只有管理员或用户自己能更新用户信息"),
//
//	})
//	public void updateUser(
//			@RequestBody @Valid @ApiParam(value = "用户信息，用户的用户名、密码、昵称、邮箱不可为空", required = true) UserDO user,
//			BindingResult result) {
//		if (result.hasErrors()) {
//			throw new ValidationException(result.getFieldErrors());
//		}
//		service.update(user);
//	}
//
//	@RequestMapping(value = "/{key}/password/reset_validation", method = RequestMethod.GET)
//	@ApiOperation(value = "发送忘记密码的邮箱验证", notes = "属性可以是id,sername或email或手机号", response = UserDO.class)
//	public void forgetPassword(@PathVariable("key") @ApiParam(value = "关键字", required = true) String key,
//			@RequestParam("mode") @ApiParam(value = "验证模式，可以是username或phone或email", required = true) String mode) {
//		UserDO user = findByKey(key, mode);
//		// user 一定不为空
//		String forgetPasswordCode = UUIDUtil.uuid();
//		verificationManager.createVerificationCode(forgetPasswordCode, String.valueOf(user.getId()),
//				authenticationProperties.getForgetNameCodeExpireTime());
//		log.info("{}   {}", user.getEmail(), user.getId());
//		// 发送邮件
//		Map<String, Object> params = new HashMap<>();
//		params.put("id", user.getId());
//		params.put("forgetPasswordCode", forgetPasswordCode);
//		emailService.sendHTML(user.getEmail(), "forgetPassword", params, null);
//	}
//
//	@RequestMapping(value = "/{id}/password", method = RequestMethod.PUT)
//	@ApiOperation(value = "忘记密码后可以修改密码")
//	public void resetPassword(@PathVariable("id") Long id,
//			@RequestParam("forgetPasswordCode") @ApiParam(value = "验证码", required = true) String forgetPasswordCode,
//			@RequestParam("password") @ApiParam(value = "新密码", required = true) String password) {
//		// 获取Redis中的验证码
//		if (!verificationManager.checkVerificationCode(forgetPasswordCode, String.valueOf(id))) {
//			verificationManager.deleteVerificationCode(forgetPasswordCode);
//			throw new ActivationCodeValidationException(forgetPasswordCode);
//		}
//		verificationManager.deleteVerificationCode(forgetPasswordCode);
//		service.resetPassword(id, password);
//	}
//
//	@RequestMapping(value = "/{username}/duplication", method = RequestMethod.GET)
//	@ApiOperation(value = "查询用户名是否重复", response = Boolean.class)
//	@ApiResponses(value = { @ApiResponse(code = 401, message = "未登录") })
//	public boolean isUsernameDuplicated(@PathVariable("username") String username) {
//		if (service.findByUsername(username) == null) {
//			return false;
//		}
//		return true;
//	}

	@ApiOperation(value = "查询", notes = "分页查询用户信息",response = PageInfo.class)
	@RequestMapping(value = "/findAllUsers", method = RequestMethod.GET)
	public PageInfo<User> findAllUsers(
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") @ApiParam(value = "页码，从1开始", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") @ApiParam(value = "每页记录数", defaultValue = "10") Integer pageSize) {
		return service.findAll(pageNum, pageSize);
	}
}
