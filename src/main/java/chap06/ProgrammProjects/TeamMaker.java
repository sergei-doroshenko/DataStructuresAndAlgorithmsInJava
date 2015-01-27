package chap06.ProgrammProjects;

/** Creates teams of k from n-members
 * Created by Sergei Doroshenko on 27.01.2015.
 * According to theorem (n, k) = (n – 1, k – 1) + (n – 1, k)
 * so we creates two branches:
 * 1st - (n – 1, k – 1)
 * 2nd - (n – 1, k)
 *               (5,3)
 *                 |
 *        |                  |
 *      (4,2)              (4,3)
 *        |                  |
 *    |       |        |          |
 *  (3,1)   (3,2)    (3,2)      (3,3)
 *  ... and so on ...
 */
public class TeamMaker {
    public static int COUNTER;
    public static int TEAMS;

    public static void showTeams(int n, int k, String team, char next) {
        COUNTER++;
//        System.out.printf("(%d, %d) %3s '%s' RC: %2d\n", n, k, team, next, COUNTER);
        /*  Base conditions  */
        if(k == 1 && n == 1) {
            TEAMS++;
            System.out.println("team = " + team + next); // return team string with next char
            return;

        } else if (k == 0) {
            TEAMS++;
            System.out.println("team = " + team);       // return team string without next char
            return;                                     // that's mean all three chars appended to
                                                        // team string (k = 3 -> 2 -> 1 -> 0)
        } else if (n < k) {
            return;

        } else {

            showTeams(n - 1,k - 1,team + next, ++next ); // recursively calls 1st tree branch

            showTeams(n - 1, k, team, next);             // recursively calls 2nd tree branch
        }
    }

    public static void main (String[] args) {
        showTeams(5, 3, "", 'A');
        System.out.println("===============================================");
        System.out.println("Teams: " + TEAMS);
        System.out.println("Recursive calls: " + COUNTER);
    }
}
