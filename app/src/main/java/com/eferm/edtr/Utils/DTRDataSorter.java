package com.eferm.edtr.Utils;

import com.eferm.edtr.Model.DTRData;
import com.eferm.edtr.Model.DTRDataSorted;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by R. R. Reyes on 3/26/2018.
 */

public class DTRDataSorter {

    List<DTRDataSorted> sortedData;

    public List<DTRDataSorted> SortData(List<DTRData> data) {
        sortedData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DTRData dtrTemp = data.get(i);
            DTRDataSorted dataTemp = new DTRDataSorted();
            if (sortedData.size() != 0) {
                int ctr = 0;
                for (int j = 0; j < sortedData.size(); j++) {
                    if ((dtrTemp.getType().equals("4")) && (BaseUtils.GetHour(dtrTemp.getTime()) <= 6)) {
                        if (sortedData.get(j).getDate().equals(dtrTemp.getDate())
                                && sortedData.get(j).getBarcode().equals(dtrTemp.getBarcode())) {
                            if (sortedData.get(j).getTimeIn() == null) {
                                for (int k = 0; k < sortedData.size(); k++) {
                                    if (sortedData.get(j).getDate().equals(BaseUtils.GetYesterday(dtrTemp.getDate()))
                                            && sortedData.get(j).getBarcode().equals(dtrTemp.getBarcode())) {
                                        if (sortedData.get(j).getTimeOut() == null) {
                                            ctr++;
                                            dataTemp = AddData(sortedData.get(j), dtrTemp);
                                            sortedData.remove(j);
                                            sortedData.add(dataTemp);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (sortedData.get(j).getDate().equals(dtrTemp.getDate())
                            && sortedData.get(j).getBarcode().equals(dtrTemp.getBarcode())) {
                        ctr++;
                        dataTemp = AddData(sortedData.get(j), dtrTemp);
                        sortedData.remove(j);
                        sortedData.add(dataTemp);
                    }
                }
                if (ctr == 0) {
                    DefaultSort(dataTemp, dtrTemp);
                }
            } else {
                DefaultSort(dataTemp, dtrTemp);
            }
        }
        return sortedData;
    }

    private DTRDataSorted AddData(DTRDataSorted dtrDataSorted, DTRData dtrData) {
        DTRDataSorted sortData = dtrDataSorted;
        switch (dtrData.getType()) {
            case "1":
                if (sortData.getTimeIn() == null) {
                    sortData.setTimeIn(dtrData.getTime());
                }
                break;
            case "2":
                if (sortData.getBreakOut() == null) {
                    sortData.setBreakOut(dtrData.getTime());
                }
                break;
            case "3":
                sortData.setBreakIn(dtrData.getTime());
                break;
            case "4":
                sortData.setTimeOut(dtrData.getTime());
                break;
        }
        return sortData;
    }

    private void DefaultSort(DTRDataSorted dataTemp, DTRData dtrTemp) {
        dataTemp.setDate(dtrTemp.getDate());
        dataTemp.setBarcode(dtrTemp.getBarcode());
        dataTemp = AddData(dataTemp, dtrTemp);
        sortedData.add(dataTemp);
    }
}
