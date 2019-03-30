//package phase2.Operators.BankWorker.BankFunctions;
//
//import phase2.Operators.BankAccountUser.PointSystemUser;
//import phase2.Operators.BankAccountUser.RetiredPointSystemUser;
//import phase2.Operators.BankAccountUser.StudentPointSystemUser;
//import phase2.Operators.BankAccountUser.User;
//
//public enum BankAccountFactory{
//    StandardPointSystemUser("standardPS"),
//    StandardUser("standard"),
//    goldMemberPointSystem("goldMemberPS"),
//    pensionPointUser("pensionPS"),
//    studentPointUser("studentPS");
//
//    private String accountTypeDeterminer;
//
//    BankAccountFactory(String accountType) {
//        this.accountTypeDeterminer = accountType;
//    }
//
//
//    public User determineUserAccountTypeCreation(String accountType, String username, String password) {
//        User user = null;
//        switch (accountType) {
//            case StandardUser:
//                user = new PointSystemUser(username, password);
//                break;
//            case "student":
//                user = new StudentPointSystemUser(username, password);
//                break;
//            case "pension":
//                user = new RetiredPointSystemUser(username, password);
//                break;
//        } return user;
//    }
//
//
//    public User determineUserAccountTypeChangeRequest(String accountType, String username, String password) {
//        User user = null;
//        switch (accountType) {
//            case "standard":
//                user = new StudentPointSystemUser(username, password);
//                break;
//            case "student":
//                user = determineStudentType();
//                break;
//            case "pension":
//                user = new StudentPointSystemUser(username, password);
//                break;
//            case "goldMember":
//                user = determineStudentType();
//                break;
//        } return user;
//    }
//
//
//
//    public User determineStudentType() {
//
//    }
//
//    public User determinePensionUserType() {
//
//    }
//
//    public User determineStandardUserType() {
//
//    }
//}
//}
