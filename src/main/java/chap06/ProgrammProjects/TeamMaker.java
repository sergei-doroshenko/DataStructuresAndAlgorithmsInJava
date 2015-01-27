package chap06.ProgrammProjects;

/**
 * Created by user on 27.01.2015.
 */
public class TeamMaker {
    char[] range = new char[] {'A', 'B', 'C', 'D', 'E'};

    /*public static void showTeams(int n, int k, String team, char next) {
        if(k == 0) {
            System.out.println("team = " + team);
            return;

        } else if (n < k) {
            return;

        } else {

            showTeams(n - 1,k - 1,team + next, next++ );

            showTeams(n - 1, k, team, next++);
        }
    }*/

    public static void showTeams(int n, int k, String team, char next) {
        System.out.printf("n: %2d k: %2d %3s %s\n", n, k, team, next);

        if(k == 1 && n == 1) {
            //System.out.println("team = " + team + next);
            return;

        } else if (k == 0 || n < k) {
            return;

        } else {

            showTeams(n - 1,k - 1,team + next, next++ );

            showTeams(n - 1, k, team, next++);
        }
    }

    public static void main (String[] args) {
        showTeams(5, 3, "", 'A');
    }
}
