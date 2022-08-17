package com.copying.blogs.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fzz
 * @date 2022/8/17
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {
    JPEG("jpeg","jpg"),
    JPG("jpg","jpg"),
    PNG("png","png");

    private final String type;
    private String value;

//    public static FileTypeEnum getByType(String type){
//        if(type == null || type == ""){
//            return null;
//        }
//        FileTypeEnum.values();
//
//    }

    /**
     * 根据类型获取文件后缀
     * @param type type
     * @return java.lang.String
     */
    public static String getValueForType(String type){
        return "";
    }
}
