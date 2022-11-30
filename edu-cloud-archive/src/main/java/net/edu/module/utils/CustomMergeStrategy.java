package net.edu.module.utils;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.xpath.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class CustomMergeStrategy extends AbstractMergeStrategy {

    /**
     * 分组，每几行合并一次
     */
    private List<List<Integer>> columnCountList;

    /**
     * 分组，每几列合并一次
     */
    private List<List<Integer>> rowCountList;

    //从第几行开始进行行合并
    private Integer rowIndex;

    //从第几列开始进行列合并
    private Integer colIndex;

    private Boolean isRow;

    private Boolean isCol;


    // exportDataList为整个二维列表
    public CustomMergeStrategy(List<List<String>> exportDataList, Integer rowIndex, Integer colIndex, Boolean isRow, Boolean isCol) {
        this.columnCountList = getGroupCountColumn(exportDataList);
        this.rowCountList = getGroupCountRow(exportDataList);
        this.rowIndex = rowIndex;
        this.isRow = isRow;//是否需要上下合并
        this.isCol = isCol;//是否需要左右合并
    }

    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {
        mergeGroup(sheet);
    }

    private void mergeGroup(Sheet sheet) {
        //合并所有的行，上下合并
        if(isRow){
            for (int i = 0; i < rowCountList.size(); i++) {
                int rowCount = 0;
                for (int j = 0; j < rowCountList.get(i).size(); j++) {
                    if (rowCountList.get(i).get(j) == 1) {
                        rowCount++;
                        continue;
                    }
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(rowCount + rowIndex, rowCount + rowIndex + rowCountList.get(i).get(j) - 1, i, i);
                    sheet.addMergedRegionUnsafe(cellRangeAddress);
                    rowCount += rowCountList.get(i).get(j);
                }
            }
        }



        //合并所有的列，左右合并
        if(isCol){
            for (int i = 0; i < columnCountList.size(); i++) {
                int rowCount = 0;
                for (int j = 0; j < columnCountList.get(i).size(); j++) {
                    int x = columnCountList.get(i).get(j);
                    if (x == 1) {
                        rowCount++;
                        continue;
                    }
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(i + rowIndex, i + rowIndex, rowCount, rowCount + x - 1);
                    sheet.addMergedRegionUnsafe(cellRangeAddress);
                    rowCount += x;
                }
            }
        }

    }

    // 该方法将整个二维数据表进行合并，返回可合并的列数，即左右合并
    private List<List<Integer>> getGroupCountColumn(List<List<String>> exportDataList) {
        if (CollectionUtils.isEmpty(exportDataList)) {
            return new ArrayList<>();
        }

        List<List<Integer>> groupCountList = new ArrayList<>();


        for (int i = 0; i < exportDataList.size(); i++) {
            int row = 1;
            List<Integer> countRow = new ArrayList<>();
            for (int j = 0; j < exportDataList.get(i).size() - 1; j++) {
                if (j == exportDataList.get(i).size() - 2 && exportDataList.get(i).get(j).equals(exportDataList.get(i).get(j + 1))) {
                    countRow.add(++row);
                    break;
                }
                //如果发现某一行中两列的数据相同
                if (exportDataList.get(i).get(j).equals(exportDataList.get(i).get(j + 1))) {
                    row++;
                } else {
                    countRow.add(row);
                    row = 1;
                }

            }
            groupCountList.add(countRow);
        }


        return groupCountList;
    }

    // 该方法将整个二维数据表进行合并，返回可合并的行数，即上下合并
    private List<List<Integer>> getGroupCountRow(List<List<String>> exportDataList) {
        if (CollectionUtils.isEmpty(exportDataList)) {
            return new ArrayList<>();
        }

        List<List<Integer>> groupCountList = new ArrayList<>();

        for (int i = 0; i < exportDataList.get(0).size(); i++) {
            int row = 1;
            List<Integer> countRow = new ArrayList<>();
            for (int j = 0; j < exportDataList.size() - 1; j++) {
                if (j == exportDataList.size() - 2) {
                    if (exportDataList.get(j).get(i).equals(exportDataList.get(j + 1).get(i))) {
                        countRow.add(++row);
                        break;
                    } else {
                        countRow.add(row);
                        countRow.add(1);
                        break;
                    }
                }

                if (exportDataList.get(j).get(i).equals(exportDataList.get(j + 1).get(i))) {
                    row++;
                } else {
                    countRow.add(row);
                    row = 1;
                }
            }
            groupCountList.add(countRow);
        }

        return groupCountList;
    }
}
