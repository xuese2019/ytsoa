package com.yts.ytsoa.sys.shiro;

import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.account.model.AdminModel;
import com.yts.ytsoa.business.account.service.AccountService;
import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.business.qxgl.service.ZzQxService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired(required = false)
    private AccountService accountService;

    @Autowired(required = false)
    private ZzQxService zzQxService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        // TODO Auto-generated method stub
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        AccountModel model = (AccountModel) arg0.getPrimaryPrincipal();
        switch (model.getLx()) {
            case -1:
                info.addRole("admin");
                break;
            case 1:
                info.addRole("user");
                break;
            default:
                info.addRole("user");
        }
        try {
        ResponseResult<List<QxglModel>> result = zzQxService.findByAccid(model.getUuid());
        if (result.isSuccess()) {
            result.getData().forEach(k -> {
                if (k.getQxlx().equals("1"))
                    info.addRole(k.getQxbs());
                info.addStringPermission(k.getQxbs());
            });
        }
        }catch (Exception e){
            throw new ShiroException("权限获取异常");
        }
        return info;
    }

    /**
     * 登陆验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        try {
            String username = (String) authcToken.getPrincipal();
            AccountModel model = new AccountModel();
            model.setAccount(username);
            ResponseResult<List<AccountModel>> result = accountService.findByAccount(model);
            if (!result.isSuccess()) {
                AdminModel adminModel = new AdminModel();
                adminModel.setAccount(username);
                ResponseResult<AccountModel> result1 = accountService.getAdminByAccount(adminModel);
                if (result1.isSuccess()) {
                    AccountModel model1 = result1.getData();
                    model1.setLx(-1);
                    model1.setIsLogin("Y");
                    return new SimpleAuthenticationInfo(result1.getData(), result1.getData().getPassword(), getName());
                } else
                    throw new AuthenticationException("用户名或密码不正确!");
            }
            AccountModel model1 = result.getData().get(0);
            model1.setLx(1);
            if (model1.getIsLogin() != null && model1.getIsLogin().equals("Y"))
                return new SimpleAuthenticationInfo(model1, model1.getPassword(), getName());
            else
                throw new AuthenticationException("当前账户禁止登陆!");
        } catch (Exception e) {
            throw new AuthenticationException("账号密码错误");
        }
    }
}
