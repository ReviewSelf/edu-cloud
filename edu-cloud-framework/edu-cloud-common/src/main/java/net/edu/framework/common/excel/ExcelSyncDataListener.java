package net.edu.framework.common.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class ExcelSyncDataListener<T> extends AnalysisEventListener<T> {
    /**
     * 定义一个保存Excel所有记录的集合
     */
    private final List<T> list = new LinkedList<>();

    public List<T> getList() {
        return list;
    }

    @Override
    public void invoke(T data, AnalysisContext analysisContext) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }
}
