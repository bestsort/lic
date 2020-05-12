package cn.bestsort.lic.controller.api;

import cn.bestsort.lic.model.entity.Files;
import cn.bestsort.lic.bridge.FileStoreBridgeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * (Files)表控制层
 *
 * @author bestsort
 * @since 2020-02-29 15:41:23
 */
@RestController
@RequestMapping("/api/file")
public class FilesController {
    /**
     * 服务对象
     */
    @Resource
    private FileStoreBridgeInterface fileInterface;
    /**
     * 通过主键查询单条数据
     *
     * @param fileId 主键
     * @return 单条数据
     */
    @GetMapping("/{fileId}")
    public boolean get(@PathVariable Long fileId,
                       HttpServletResponse response) {
        try {
            response.setContentType("application/force-download");

            Files fileInfo = fileInterface.getFile(fileId);
            response.setHeader("Content-Disposition", "attachment;fileName="
                + fileInfo.getName());
            Assert.notNull(fileInfo, "文件未找到");

            InputStream inputStream = new FileInputStream(new File(fileInfo.getRealPath()));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.flush();
            os.close();
            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件下载失败");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @PostMapping("/copy/{fileId}")
    public String copyFileTo(@PathVariable long fileId,
                             @RequestParam long sourceFileDirId,
                             @RequestParam(defaultValue = "false") boolean isMove,
                             @RequestParam(defaultValue = "true") boolean actual){
        return fileInterface.copyFileTo(sourceFileDirId, fileId, isMove,actual);
    }
    @PostMapping("/rename")
    public boolean renameFile(@RequestParam String targetName,
                              @RequestParam long fileId){
        return fileInterface.renameFile(targetName, fileId).equals(targetName);
    }

    @PutMapping("/make_dir")
    public String mkDir(String path,
                        String name){
        return fileInterface.makeDir(path,name,1);
    }


    @PutMapping
    public Files upload(@RequestPart MultipartFile file,
                            @RequestParam(defaultValue = "0") Long dirId){
        return fileInterface.uploadFile(file, dirId);
    }

    @DeleteMapping
    public boolean delete(@RequestParam long fileId,
                              long userId,
                              boolean isDir){
        return fileInterface.deleteFile(fileId,userId,isDir);
    }
}