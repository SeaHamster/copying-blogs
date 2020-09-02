package top.copying.blogs.sysfunction.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.copying.blogs.properties.ReadGlobalConfigProperties;
import top.copying.blogs.model.entity.CyBlogsFile;
import top.copying.blogs.sysfunction.service.FileService;
import top.copying.blogs.util.FileUtil;
import top.copying.blogs.util.exception.ResponseCode;

import javax.annotation.Resource;

/**
 * @author copying
 * @date 2020-08-17 09:34:15
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileUtil fileUtil;
    @Resource
    private ReadGlobalConfigProperties readGlobalConfigProperties;
    /** 公共文件储存地址 */
    private static final String FILE_PATH="common-file";

    @Override
    public String upLoadFile(MultipartFile file,String ip) {
        if(file.isEmpty()){
            throw new ClassCastException(ResponseCode.COMMON_MISS_PARAMETER);
        }
        return fileUtil.fileUpLoad(file,ip, readGlobalConfigProperties.getFilePath().get(FILE_PATH));
    }

    @Override
    public CyBlogsFile downLoadFile(Integer id) {
        if(id==null){
            throw  new ClassCastException(ResponseCode.COMMON_NOT_EXIST);
        }
        return fileUtil.fileDownLoad(id);
    }


}
