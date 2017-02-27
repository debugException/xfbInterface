package cn.emagsoftware.xfb.portal;

import cn.emagsoftware.utils.ConfigCache;
import cn.emagsoftware.utils.json.JsonUtils;
import cn.emagsoftware.xfb.constants.PicUploadConstant;
import cn.emagsoftware.xfb.dto.PictureRsp;
import cn.emagsoftware.xfb.pojo.*;
import cn.emagsoftware.xfb.service.MemberInfoService;
import cn.emagsoftware.xfb.service.SysUserService;
import cn.emagsoftware.xfb.service.UserAuthinfoInfoService;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class PicHandleServlet extends HttpServlet {

    Logger logger = Logger.getLogger(PicHandleServlet.class.getName());

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private MemberInfoService memberInfoService;

    @Autowired
    private UserAuthinfoInfoService userAuthinfoInfoService;



    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.getWriter().write(this.handleRequest(request));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.getWriter().write(this.handleRequest(request));

    }

    /**
     * 根据不同的类型调用不同的认证方法进行用户认证
     * @param request
     * @return
     */

    private String  handleRequest(HttpServletRequest request) {

        PictureRsp pictureRsp = new PictureRsp();
        pictureRsp.setResultCode(PicUploadConstant.ERROR_CODE_1000);
        String return_msg = "";
//       根据请求的认证模型进行不同的认证处理
        try{
            PicUploadInfo picUploadInfo = getPicUploadInfo(request);

            int authinoTtype = picUploadInfo.getAuthinfoType()/10;
            switch (authinoTtype){
                case 4:
//                    身份证认证
                    this.userIdcardImagUpload(request, picUploadInfo);
                    break;
                case 7:
//                    结婚证认证
                      this.userMarryImagUpload(request, picUploadInfo);
                    break;
                case 8:
//                    职业资格正认证
                        this.userJobcertImagUpload(request, picUploadInfo);
                    break;
                case 9:
//                    驾照认证
                    this.userDriveImagUpload(request, picUploadInfo);
                    break;
                default:
//                    this.userIdcardImagUpload(request, picUploadInfo);
                    pictureRsp.setResultCode(PicUploadConstant.ERROR_CODE_1001);
            }
        }catch (Exception ex){
           logger.error("用户信息认证图片上传失败",ex);
            pictureRsp.setResultCode(PicUploadConstant.ERROR_CODE_2000);
        }
        try {
            pictureRsp.setResultMessage(PicUploadConstant.ERROR_MESSAGE.get(pictureRsp.getResultCode()));
            return_msg=JsonUtils.getJSONString(pictureRsp);
            logger.debug("PicHandleServlet.handleRequest.pictureRsp=="+JsonUtils.getJSONString(return_msg));
        } catch (Exception e) {
            logger.error("PicHandleServlet.handleRequest.modelput", e);
        }
        return (return_msg);
    }


    /**
     * 获取传输的图片流并存储到本地服务器
     * @param request
     * @return
     */
    public List<String> picHandle(HttpServletRequest request,PicUploadInfo picUploadInfo) throws Exception {
        List<String> list = new ArrayList<String>();

        OutputStream out = null;

        InputStream in = null;
        //获得磁盘文件条目工厂。
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //获取文件上传需要保存的路径，upload文件夹需存在。
         String picturePath = ConfigCache.getPictureRootPath() + "/" +picUploadInfo.getLoginName()+"/"+ picUploadInfo.getAuthinfoType();

         File file = new File(picturePath);
         if(!file.exists()){
             file.mkdirs();
         }
         String authinfoPath = "/" +picUploadInfo.getLoginName()+"/"+ picUploadInfo.getAuthinfoType();
        //设置暂时存放文件的存储室，这个存储室可以和最终存储文件的文件夹不同。因为当文件很大的话会占用过多内存所以设置存储室。
        factory.setRepository(new File(picturePath));
        //设置缓存的大小，当上传文件的容量超过缓存时，就放到暂时存储室。
        factory.setSizeThreshold(1024 * 1024);
        //上传处理工具类（高水平API上传处理？）
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            //调用 parseRequest（request）方法  获得上传文件 FileItem 的集合list 可实现多文件上传。
            List<FileItem> fileList = (List<FileItem>) upload.parseRequest(request);
            for (FileItem item : fileList) {
                //获取表单属性名字。
                String name = item.getFieldName();
                //如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。
                if (item.isFormField()) {
                    //获取用户具体输入的字符串，
                    String value = item.getString();
                    request.setAttribute(name, value);
                }
                //如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。
                else {
                    //获取路径名
                    String value = item.getName();
                    //取到最后一个反斜杠。
                    int start = value.lastIndexOf("\\");
                    //截取上传文件的 字符串名字。+1是去掉反斜杠。
                    String filename = value.substring(start + 1);
                    request.setAttribute(name, filename);

                    /*第三方提供的方法直接写到文件中。
                     * item.write(new File(path,filename));*/
                    //收到写到接收的文件中。
                    out = new FileOutputStream(new File(picturePath, filename));
                    in = item.getInputStream();

                    int length = 0;
                    byte[] buf = new byte[1024];
                    logger.debug("获取文件总量的容量:" + item.getSize());
                    while ((length = in.read(buf)) != -1) {
                        out.write(buf, 0, length);
                    }
                    authinfoPath = authinfoPath+"/" +filename;

                    switch (picUploadInfo.getAuthinfoType()){
                        case 41:
                            picUploadInfo.setImagePath1(authinfoPath);
                            break;
                        case 42:
                            picUploadInfo.setImagePath2(authinfoPath);
                            break;
                        case 43:
                            picUploadInfo.setImagePath3(authinfoPath);
                            break;
                        case 71:
                            picUploadInfo.setImagePath1(authinfoPath);
                            break;
                        case 81:
                            picUploadInfo.setAuthinfoType(1);
                            picUploadInfo.setImagePath1(authinfoPath);
                            break;
                        case 82:
                            picUploadInfo.setAuthinfoType(2);
                            picUploadInfo.setImagePath1(authinfoPath);
                            break;
                        case 83:
                            picUploadInfo.setAuthinfoType(3);
                            picUploadInfo.setImagePath1(authinfoPath);
                            break;
                        case 84:
                            picUploadInfo.setAuthinfoType(4);
                            picUploadInfo.setImagePath1(authinfoPath);
                            break;
                        case 85:
                            picUploadInfo.setAuthinfoType(5);
                            picUploadInfo.setImagePath1(authinfoPath);
                            break;
                        case 86:
                            picUploadInfo.setAuthinfoType(6);
                            picUploadInfo.setImagePath1(authinfoPath);
                            break;
                        case 91:
                            picUploadInfo.setImagePath1(authinfoPath);
                            break;
                        default:
                            throw new Exception("不存在的图片类型");
                    }
                }

            }
        } catch (Exception e) {
            logger.error("PicHandleServlet.handleRequest", e);
            throw e;
        }finally {
            if(in!=null){
                in.close();
            }
            if(out!=null){
                out.close();
            }
        }

        return  list;
    }



    /**
     * 用户身份证图片上传处理
     * @param request
     * @throws Exception
     */
    public void userIdcardImagUpload(HttpServletRequest request,PicUploadInfo picUploadInfo)throws Exception{
        try{
            picHandle(request,picUploadInfo);

//            SysUser sysUser =  sysUserService.getUserByLoinName(picUploadInfo.getLoginName());

            MemberInfo memberInfo = memberInfoService.getMemerByUserid(picUploadInfo.getUserId());

            if(memberInfo!=null){
//               存在 更新用户身份认证图片
                memberInfo.setSysUserid(picUploadInfo.getUserId());
                memberInfo.setImagePath1(picUploadInfo.getImagePath1());
                memberInfo.setImagePath2(picUploadInfo.getImagePath2());
                memberInfo.setImagePath3(picUploadInfo.getImagePath3());
                memberInfoService.updateImagePathByUserId(memberInfo);
            }else{
                memberInfo = new MemberInfo();
                memberInfo.setSysUserid(picUploadInfo.getUserId());
                memberInfo.setImagePath1(picUploadInfo.getImagePath1());
                memberInfo.setImagePath2(picUploadInfo.getImagePath2());
                memberInfo.setImagePath3(picUploadInfo.getImagePath3());
                memberInfoService.addMemberInfo(memberInfo);
            }


        }catch (Exception ex){
            logger.error("用户身份证图片上传失败",ex);
            throw  ex;
        }

    }


    /**
     * 用户结婚证图片上传处理
     * @param request
     * @throws Exception
     */
    public void userMarryImagUpload(HttpServletRequest request,PicUploadInfo picUploadInfo)throws Exception{
        try{
            picHandle(request,picUploadInfo);

//            SysUser sysUser =  sysUserService.getUserByLoinName(picUploadInfo.getLoginName());

            MarryAuthinfo marryAuthinfo  = userAuthinfoInfoService.getMarryAuthinfoByUserid(picUploadInfo.getUserId());

            if(marryAuthinfo!=null){
//               存在 更新用户身份认证图片
                marryAuthinfo.setSysUserid(picUploadInfo.getUserId());
                marryAuthinfo.setImagePath1(picUploadInfo.getImagePath1());
                marryAuthinfo.setImagePath2(picUploadInfo.getImagePath2());
                marryAuthinfo.setImagePath3(picUploadInfo.getImagePath3());
                userAuthinfoInfoService.updateImagePathByUserId(marryAuthinfo);
            }else{
                marryAuthinfo = new MarryAuthinfo();
                marryAuthinfo.setSysUserid(picUploadInfo.getUserId());
                marryAuthinfo.setImagePath1(picUploadInfo.getImagePath1());
                marryAuthinfo.setImagePath2(picUploadInfo.getImagePath2());
                marryAuthinfo.setImagePath3(picUploadInfo.getImagePath3());
                userAuthinfoInfoService.addMarryAuthinfo(marryAuthinfo);
            }
        }catch (Exception ex){
            logger.error("结婚证图片上传失败",ex);
            throw  ex;
        }

    }


    /**
     * 用户信用卡图片上传处理
     * @param request
     * @throws Exception
     */
    public void userBluecardImagUpload(HttpServletRequest request,PicUploadInfo picUploadInfo)throws Exception{
        try{
            picHandle(request,picUploadInfo);

//            SysUser sysUser =  sysUserService.getUserByLoinName(picUploadInfo.getLoginName());

            BluecardAuthinfo bluecardAuthinfo  = userAuthinfoInfoService.getBluecardAuthinfoByUserid(picUploadInfo.getUserId());

            if(bluecardAuthinfo!=null){
//               存在 更新用户身份认证图片
                bluecardAuthinfo.setSysUserid(picUploadInfo.getUserId());
                bluecardAuthinfo.setImagePath1(picUploadInfo.getImagePath1());
                bluecardAuthinfo.setImagePath2(picUploadInfo.getImagePath2());
                bluecardAuthinfo.setImagePath3(picUploadInfo.getImagePath3());
                userAuthinfoInfoService.updateImagePathByUserId(bluecardAuthinfo);
            }else{
                bluecardAuthinfo = new BluecardAuthinfo();
                bluecardAuthinfo.setSysUserid(picUploadInfo.getUserId());
                bluecardAuthinfo.setImagePath1(picUploadInfo.getImagePath1());
                bluecardAuthinfo.setImagePath2(picUploadInfo.getImagePath2());
                bluecardAuthinfo.setImagePath3(picUploadInfo.getImagePath3());
                userAuthinfoInfoService.addBluecardAuthinfo(bluecardAuthinfo);
            }
        }catch (Exception ex){
            logger.error("信用卡图片上传失败",ex);
            throw  ex;
        }

    }


    /**
     * 用户工作证图片上传处理
     * @param request
     * @throws Exception
     */
    public void userJobcertImagUpload(HttpServletRequest request,PicUploadInfo picUploadInfo)throws Exception{
        try{
            picHandle(request,picUploadInfo);

//            SysUser sysUser =  sysUserService.getUserByLoinName(picUploadInfo.getLoginName());

            JobcertAuthinfo jobcertAuthinfo  = userAuthinfoInfoService.getJobcertAuthinfoByUseridAndType(picUploadInfo.getUserId(),picUploadInfo.getAuthinfoType());

            if(jobcertAuthinfo!=null){
//               存在 更新用户身份认证图片
                jobcertAuthinfo.setSysUserid(picUploadInfo.getUserId());
                jobcertAuthinfo.setCardType(picUploadInfo.getAuthinfoType()+"");
                jobcertAuthinfo.setImagePath1(picUploadInfo.getImagePath1());
                jobcertAuthinfo.setImagePath2(picUploadInfo.getImagePath2());
                jobcertAuthinfo.setImagePath3(picUploadInfo.getImagePath3());
                userAuthinfoInfoService.updateImagePathByUserId(jobcertAuthinfo);
            }else{
                jobcertAuthinfo = new JobcertAuthinfo();
                jobcertAuthinfo.setSysUserid(picUploadInfo.getUserId());
                jobcertAuthinfo.setCardType(picUploadInfo.getAuthinfoType()+"");
                jobcertAuthinfo.setImagePath1(picUploadInfo.getImagePath1());
                jobcertAuthinfo.setImagePath2(picUploadInfo.getImagePath2());
                jobcertAuthinfo.setImagePath3(picUploadInfo.getImagePath3());
                userAuthinfoInfoService.addJobcertAuthinfo(jobcertAuthinfo);
            }
        }catch (Exception ex){
            logger.error("工作证图片上传失败",ex);
            throw  ex;
        }

    }

    /**
     * 用户驾照图片上传处理
     * @param request
     * @throws Exception
     */
    public void userDriveImagUpload(HttpServletRequest request,PicUploadInfo picUploadInfo)throws Exception{
        try{
            picHandle(request,picUploadInfo);

//            SysUser sysUser =  sysUserService.getUserByLoinName(picUploadInfo.getLoginName());

            DriveAuthinfo driveAuthinfo  = userAuthinfoInfoService.getDriveAuthinfoByUserid(picUploadInfo.getUserId());

            if(driveAuthinfo!=null){
//               存在 更新用户身份认证图片
                driveAuthinfo.setSysUserid(picUploadInfo.getUserId());
                driveAuthinfo.setImagePath1(picUploadInfo.getImagePath1());
                driveAuthinfo.setImagePath2(picUploadInfo.getImagePath2());
                driveAuthinfo.setImagePath3(picUploadInfo.getImagePath3());
                userAuthinfoInfoService.updateImagePathByUserId(driveAuthinfo);
            }else{
                driveAuthinfo = new DriveAuthinfo();
                driveAuthinfo.setSysUserid(picUploadInfo.getUserId());
                driveAuthinfo.setImagePath1(picUploadInfo.getImagePath1());
                driveAuthinfo.setImagePath2(picUploadInfo.getImagePath2());
                driveAuthinfo.setImagePath3(picUploadInfo.getImagePath3());
                userAuthinfoInfoService.addDriveAuthinfo(driveAuthinfo);
            }
        }catch (Exception ex){
            logger.error("驾照图片上传失败",ex);
            throw  ex;
        }

    }




    public PicUploadInfo getPicUploadInfo(HttpServletRequest request)throws Exception{
        PicUploadInfo picUploadInfo = new PicUploadInfo();

//        Enumeration enu=request.getParameterNames();
//        while(enu.hasMoreElements()){
//            String paraName=(String)enu.nextElement();
//            logger.debug("request.getParameterNames()===["+paraName+": "+request.getParameter(paraName)+"]");
//        }
//

        Enumeration enu = request.getHeaderNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            logger.debug("request.getHeaderNames()===["+paraName+": "+request.getHeader(paraName)+"]");
        }

//        String imei = request.getHeader("imei");
//        String channelCode = request.getHeader("channelCode");
//        String platform = request.getHeader("platform");
//        logger.debug("request.getHeaderNames()"+"{"+imei+";"+channelCode+";"+platform+";"+request.getHeader("type")+";"+request.getHeader("userId")+"}");
//
//        logger.debug("request.getParameter()"+"{"+imei+";"+channelCode+";"+platform+";"+request.getParameter("type")+";"+request.getParameter("userId")+"}");

        Integer authinfoType = Integer.parseInt(request.getHeader("type")==null?"0":request.getHeader("type").toString().trim());
        Integer userId = Integer.parseInt(request.getHeader("userId")==null?"":request.getHeader("userId").toString().trim());

        picUploadInfo.setAuthinfoType(authinfoType);
        picUploadInfo.setUserId(userId);
        SysUser user = new SysUser();
        user.setId(userId);
        SysUser authinoUser = sysUserService.getUserById(user);
        picUploadInfo.setLoginName(authinoUser.getLoginName());
        logger.debug("用户认证图片上传的信息为："+ JSONObject.fromObject(picUploadInfo).toString());
        return picUploadInfo;
    }

}
