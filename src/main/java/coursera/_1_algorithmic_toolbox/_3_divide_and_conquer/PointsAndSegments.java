package coursera._1_algorithmic_toolbox._3_divide_and_conquer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        // save points positions
        Map<Integer, Queue<Integer>> pointsPositions = new HashMap<>();
        for ( int i = 0; i < points.size(); i++ ) {
            Integer key = points.get( i );
            if ( pointsPositions.get( key ) == null ) {
                pointsPositions.put( key, new ArrayDeque<>() );
            }
            pointsPositions.get( key ).add( i );
        }

        class Pair {
            Integer coord;
            String mark;

            public Pair(Integer coord, String mark) {
                this.coord = coord;
                this.mark = mark;
            }

            public Integer getCoord() {
                return coord;
            }

            public String getMark() {
                return mark;
            }
        }

        // why "l", "p', "r" ? because if we have {2, "p"}, {2, "l"}, {2, "r}, after sorting by mark we'll have
        // {2, "l"}, {2, "p"}, {2, "r"} so we can count point's segments by "p".
        List<Pair> pairs0 = points.stream().map( p -> new Pair( p, "p") ).collect(Collectors.toList());
        List<Pair> pairs1 = starts.stream().map( s -> new Pair( s, "l" )).collect( Collectors.toList() );
        List<Pair> pairs2 = ends.stream().map( e -> new Pair( e, "r" )).collect( Collectors.toList() );
        List<Pair> pairsComposed = new ArrayList<>();
        pairsComposed.addAll( pairs0 );
        pairsComposed.addAll( pairs1 );
        pairsComposed.addAll( pairs2 );
        pairsComposed.sort( Comparator.comparing( Pair::getCoord ).thenComparing( Pair::getMark ) );

        int i = 0;
        for ( Pair pair : pairsComposed ) {
            if ( "l".equals( pair.mark ) ) {
                i++;
            } else if ( "r".equals( pair.mark ) ) {
                i--;
            } else {
                if ( i > 0 ) {
                    int pos = pointsPositions.get( pair.coord ).poll();
                    counts[ pos ] += i;
                }
            }
        }

        return counts;
    }

    public static void main(String[] args) {
        List<Integer> starts = new ArrayList<>();
        starts.add( -9 );
        starts.add( 14 );
        starts.add( 32 );
        List<Integer> ends = new ArrayList<>();
        ends.add( 52 );
        ends.add( 97 );
        ends.add( 77 );
        List<Integer> points = new ArrayList<>();
        points.add( -9 );
        points.add( -9 );
        points.add( -9 );
        System.out.println( Arrays.toString( countSegmentsNaive( starts, ends, points ) ) );
        System.out.println( Arrays.toString( countsSegmentsFast( starts, ends, points ) ) );
    }
}
