package controller;

import json.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tools.StringTools;
import util.VerificationCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static json.Code.Missing_Parameters;

/**
 * @program: v-code
 * @description: 验证码控制器
 * @author: jiahao
 * @create: 2018-07-04 10:14
 **/
@Controller
@RequestMapping("/vcode")
public class VcodeController {
    /**
     * 获取图片验证码
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/get")
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VerificationCode verificationCode = new VerificationCode();
        //获取验证码图片
        BufferedImage image = verificationCode.getImage();
        //获取验证码内容
        String text = verificationCode.getText();
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        randomCode.append(text);
        // 将验证码保存到Session中。
        HttpSession session = request.getSession();
        session.setAttribute("signcode", randomCode.toString());
        System.out.println("session-signcode==>" + randomCode.toString());
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.flush();
        sos.close();
    }

    /**
     * 用户登录
     */
//    @ResponseBody
    @RequestMapping(value = "/login",produces = "application/json;charset=UTF-8")
    public @ResponseBody Object login(HttpServletRequest request, String account, String password, String signcode) {
        HttpSession session = request.getSession();
        String signcodeSession = (String) session.getAttribute("signcode");
        System.out.println("signcode==>" + signcode);
        System.out.println("signcodeSession==>" + signcodeSession);
        if (StringTools.isNull(signcode)) {
            return Msg.Error("验证码为空", Missing_Parameters);
        }
        if (StringTools.isNull(signcodeSession)) {
            return Msg.Error(0);
        }
        //验证的时候不区分大小写
        if (!signcode.equalsIgnoreCase(signcodeSession)) {
            return Msg.Error("验证码错误", -1);
        }
        //这里做业务逻辑判断，调接口获取数据库数据进行账号密码对比
        return Msg.OK();
    }

}
