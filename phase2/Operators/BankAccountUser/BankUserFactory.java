package phase2.Operators.BankAccountUser;

public class BankUserFactory {
    private final static String STANDARD_POINT_USER = "standardPS";
    private final static String STANDARD_USER = "standard";
    private final static String RETIRED_POINT_USER = "retiredPS";
    private final static String STUDENT_POINT_USER = "studentPS";
    private final static String GOLD_POINT_USER = "goldPS";


    private String userAccountType;

    public BankUserFactory(String accountType) {
        this.userAccountType = accountType;
    }
//
//    public String getUserAccountType() {
//        return userAccountType;
//    }

    public User determineUserAccountTypeCreation(String username, String password) {
        User user = null;
        switch (userAccountType) {
            case STANDARD_USER:
                user = new User(username, password);
                break;
            case STUDENT_POINT_USER:
                user = new StudentPointSystemUser(username, password);
                break;
            case RETIRED_POINT_USER:
                user = new RetiredPointSystemUser(username, password);
                break;
        } return user;
    }

    public User determineOptOutUserType(PointSystemUser pointSystemUser) {
        String userAccountType = pointSystemUser.getUserType();
        User changedUserType;
        changedUserType = new User(pointSystemUser.getUsername(), pointSystemUser.getPassword());
        switch (userAccountType){
            case STANDARD_USER:
            changedUserType.setUserType(STANDARD_USER);
            break;
            case RETIRED_POINT_USER:
            changedUserType.setUserType("retired");
            break;
            case STANDARD_POINT_USER:
            changedUserType.setUserType("student");
            break;
        } return changedUserType;
    }

    public PointSystemUser determineOptInPointUserType(User user){
        PointSystemUser changedUserType = null;
        switch(user.getUserType()){
            case "standard":
                changedUserType = new PointSystemUser(user.getUsername(), user.getPassword());
                break;
            case "student":
                changedUserType = new StudentPointSystemUser(user.getUsername(), user.getPassword());
                break;
            case "gold":
                changedUserType = new GoldMemberPointSystemUser(user.getUsername(), user.getPassword());
                break;
            case "retired":
                changedUserType = new RetiredPointSystemUser(user.getUsername(), user.getPassword());
                break;
    }return changedUserType;
    }
}
