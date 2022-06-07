package com.xu.server.storage.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/18 16:26
 */
@RestController
@RequestMapping("/store/fdfs")
@Api(tags = "eyi-store-fdfs")
public class FileController {
//    private final IFdfsFileService fdfsFileService;
//
//    public FileController(IFdfsFileService fdfsFileService) {
//        this.fdfsFileService = fdfsFileService;
//    }
//
//    @PostMapping("")
//    @ApiOperation("文件上传")
//    public Result<?> save(@RequestPart MultipartFile file) {
//        String path = fdfsFileService.save(file);
//        return Result.ok().data(path);
//    }
//
//    @ApiOperation("下载文件")
//    @GetMapping("/{id}")
//    public void getFileById(@PathVariable Long id, HttpServletResponse response) {
//        FileVo fileVo = fdfsFileService.downloadFile(id);
//        // 获取content-type
//        String contentType = TikaUtils.getContentTypeByExtension(fileVo.getExt());
//        String filename = fileVo.getFilename();
//        response.reset();
//        response.addHeader("Content-Disposition", "attachment;fileName="+ URLEncoder.encode(filename, StandardCharsets.UTF_8));
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType(contentType);
//        try(OutputStream os = response.getOutputStream()) {
//            os.write(fileVo.getContent());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @ApiOperation("删除文件")
//    @DeleteMapping("/{id}")
//    public Result<?> deleteFileById(@PathVariable Long id) {
//        return fdfsFileService.removeById(id)?Result.ok("删除成功"):Result.failed("删除失败");
//    }
//
//    @ApiOperation("分页查询")
//    @GetMapping("/page")
//    public Result<?> queryPage(@RequestParam int page, @RequestParam int size) {
//        return Result.ok(fdfsFileService.page(page, size));
//    }
}
