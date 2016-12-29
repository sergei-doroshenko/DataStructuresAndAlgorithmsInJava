package coursera._1_algorithmic_toolbox._3_divide_and_conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by админ on 29.12.2016.
 */
public class PointsAndSegments {

    public static int[] countSegmentsNaive( List<Integer> starts, List<Integer> ends, List<Integer> points ) {
        int[] counts = new int[ points.size() ];
        for ( int i = 0; i < counts.length; i++ ) {
            counts[i] = 0;
        }

        for ( int i = 0; i < points.size(); i++ ) {
            for ( int j = 0; j < starts.size(); j++ ) {
                if ( starts.get( j ) <= points.get( i ) && points.get( i ) <= ends.get( j ) ) {
                    counts[i]++;
                }
            }
        }

        return counts;
    }

    public static int[] countsSegmentsFast( List<Integer> starts, List<Integer> ends, List<Integer> points ) {
        int[] counts = new int[ points.size() ];
        for ( int i = 0; i < counts.length; i++ ) {
            counts[i] = 0;
        }

        return counts;
    }

    public static void main(String[] args) {
        List<Integer> starts = new ArrayList<>();
        starts.add( 0 );
        starts.add( 7 );
        List<Integer> ends = new ArrayList<>();
        ends.add( 5 );
        ends.add( 10 );
        List<Integer> points = new ArrayList<>();
        points.add( 1 );
        points.add( 6 );
        points.add( 11 );
        System.out.println( Arrays.toString( countSegmentsNaive( starts, ends, points ) ) );
    }
}
