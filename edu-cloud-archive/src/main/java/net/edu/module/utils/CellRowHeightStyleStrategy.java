package net.edu.module.utils;

import com.alibaba.excel.write.style.row.AbstractRowHeightStyleStrategy;
import org.apache.poi.ss.usermodel.Row;

public class CellRowHeightStyleStrategy extends AbstractRowHeightStyleStrategy {


    @Override
    protected void setHeadColumnHeight(Row row, int relativeRowIndex) {
        //设置主标题行高为17.7
        if(relativeRowIndex == 0 ){
            //如果excel需要显示行高为15，那这里就要设置为15*20=300
            row.setHeight((short) (900));

        }
        else if(relativeRowIndex == 1) {
            row.setHeight((short) (600));
        }
    }

    @Override
    protected void setContentColumnHeight(Row row, int relativeRowIndex) {

    }



}
