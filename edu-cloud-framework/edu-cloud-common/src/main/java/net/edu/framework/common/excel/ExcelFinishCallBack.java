package net.edu.framework.common.excel;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/11/15 10:30
 * @Version: 1.0
 * @Description:
 */
public interface  ExcelFinishCallBack<T>  {
    /**
     * 导出后置处理数据
     * Do after all analysed.
     *
     * @param result the result
     */
    void doAfterAllAnalysed(List<T> result);

    /**
     * Do save batch.
     *
     * @param result the result
     */
    default void doSaveBatch(List<T> result) {
    }
}
