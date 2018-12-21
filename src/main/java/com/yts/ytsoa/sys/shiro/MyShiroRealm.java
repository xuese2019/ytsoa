package com.yts.ytsoa.sys.shiro;

import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.account.service.AccountService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private AccountService accountService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        // TODO Auto-generated method stub
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        ResponseResult<List<QxglModel>> result = qxglService.findQxByAcc(null);
//        if (result.isSuccess()) {
//            result.getData().forEach(k -> {
//                info.addRole(k.getQxbs().split(":")[0]);
//                info.addStringPermission(k.getQxbs());
//            });
//        }
        return info;
    }

    /**
     * 登陆验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {

        String username = (String) authcToken.getPrincipal();
        AccountModel model = new AccountModel();
        model.setAccount(username);
        ResponseResult<List<AccountModel>> result = accountService.findAll(model);
        if (!result.isSuccess())
            throw new UnknownAccountException("用户名或密码不正确!");
        return new SimpleAuthenticationInfo(result.getData().get(0), result.getData().get(0).getPassword(), getName());
    }

}
