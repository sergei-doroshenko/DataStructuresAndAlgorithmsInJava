package chap10.ProgrammingProjects;

import chap10.tree234.DataItem;
import chap10.tree234.TraverseHandler;
import chap10.tree234.Tree234;
import libs.AppUtils;

/**
 * Created by Sergei Doroshenko on 04.02.2015.
 */
public class SortByTree234 {
    public static void main (String[] args) {
        final long[] arr = {2, 17, 83, 22, 91, 35, 44, 50, 70, 41, 38, 89, 15};
        System.out.println(AppUtils.arrToStr("Before sorting: ", arr));
        Tree234 tree234 = new Tree234();

        for (int i = 0; i < arr.length; i++) {
            tree234.insert(arr[i]);
        }

        tree234.traverse(new TraverseHandler() {
            int i = 0;

            @Override
            public void handle(DataItem dataItem) {
                arr[i++] = dataItem.dData;
            }
        });

        System.out.println(AppUtils.arrToStr("After sorting:  ", arr));
    }

}
