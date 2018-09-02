package com.moekr.lightning.util.enums;

import lombok.Getter;

@Getter
public enum Properties {
    LOGO("logo", "网站LOGO", "BLOG"),
    NAME("name", "网站名称", "博客"),
    EOF("eof", "文章结束标志", "--End Of File--"),
    COPYRIGHT("copyright", "文章版权声明", "未经许可禁止转载本文内容，转载请注明文章出处。"),
    FOOTER("footer", "页脚内容", "Powered by SpringFramework"),
    EMAIL("email", "站长邮箱", ""),
    ANALYTICS("analytics", "Google Analytics标识", "");

    private String id;
    private String name;
    private String value;

    Properties(String id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
}