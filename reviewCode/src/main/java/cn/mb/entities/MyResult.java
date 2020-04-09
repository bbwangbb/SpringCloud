package cn.mb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyResult<T> {

    //  操作代码，成功或失败等代码
    private Integer code;
    //  信息/异常信息
    private String info;
    //  需要用的值
    private T t;

    public MyResult(Integer code, String info) {
        this(code, info, null);
    }

}
