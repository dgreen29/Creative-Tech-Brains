package app.models;

/*
 * @author Darrell Green, Jr. (DJ Green)
 * @author Zarif Mazumder
 * @author Harman Singh
 * @author Vindhriko Chandran Cain
 * 
 * @version 11.8.23
 * 
 * Team class
 */
public final class Team {
    //String array containing names of team members.
    private static final String[] team = {"Darrel Green, Jr.", "Harman Singh", "Vindhriko Chandran Cain",
            "Zarif Mazumder"};
    /**
    * returns String array, team.
    * @return team
    */
    public static String[] getTeam() {
        return team;
    }
}
