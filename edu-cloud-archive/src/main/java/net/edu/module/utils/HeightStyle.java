package net.edu.module.utils;

import com.alibaba.excel.write.style.row.AbstractRowHeightStyleStrategy;
import org.apache.poi.ss.usermodel.Row;

public class HeightStyle extends AbstractRowHeightStyleStrategy {
    private final double height;

    private final String keywords;

    public HeightStyle(double height,String keywords) {
        this.height = height;
        this.keywords = keywords;
    }

    @Override
    protected void setHeadColumnHeight(Row row, int relativeRowIndex) {
        if(relativeRowIndex == 0){
            //如果excel需要显示行高为15，那这里就要设置为15*20=300
            row.setHeight((short) (18.15*20));
        }
        if(relativeRowIndex == 1){
            //如果excel需要显示行高为15，那这里就要设置为15*20=300
            row.setHeight((short) (17.4*20));
        }
    }

    @Override
    protected void setContentColumnHeight(Row row, int relativeRowIndex) {
        if(row.getCell(0).getStringCellValue().equals(this.keywords)){
            //如果excel需要显示行高为15，那这里就要设置为15*20=300
            row.setHeight((short) (height * 20));
        }
    }



}
