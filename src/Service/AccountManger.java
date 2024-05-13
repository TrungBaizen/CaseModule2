package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Model.Account;
import Model.Cart;

public class AccountManger {
    public static List<Account> accountList = new ArrayList<>();
    public static String question1 = "Bạn thích gì nhất ?";
    public static String question2 = "Kỉ niệm nào đẹp nhất đối với bạn là gì ?";
    public static String question3 = "Trường tiểu học đầu tiên của bạn là gì ?";
    public static String question4 = "Biệt danh thời thơ ấu cảu bạn là gì ?";
    public static String question5 = "Tên đệm của bố bạn là gì ?";
    public static String question6 = "Thành phố đẹp nhất đối với bạn là gì ?";
    public static String securityQuestion1 = null;
    public static String securityQuestion2 = null;


    public void addAccount(Account newUser) {
        DataProcessing.readListAccount(accountList);
        DataProcessing.readMapCart(CartManager.cartProduct);
        String key = newUser.getUsername();
        accountList.add(newUser);
        DataProcessing.writeListAccount(accountList);
        if (newUser.getRole().equals("User")) {
            Map<String, List<Cart>> cartProduct = CartManager.cartProduct;
            List<Cart> cartList = new ArrayList<>();
            CartManager.cartProduct.put(key, cartList);
            DataProcessing.writeMapCart(cartProduct);
        }
    }

    public Account findAccount(String username, String password) {
        DataProcessing.readListAccount(AccountManger.accountList);
        for (Account account : accountList) {
            if (username.equals(account.getUsername()) && password.equals(account.getPassword())) {
                return account;
            }
        }
        return null;
    }

    public boolean checkPassword(String password) {
        DataProcessing.readListAccount(AccountManger.accountList);
        for (Account account : accountList) {
            if (password.equals(account.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public void editPasswordAdmin(String newPassword, Account accountAdminOnline) {
        DataProcessing.readListAccount(accountList);
        for (Account account : accountList) {
            if (accountAdminOnline.getUsername().equals(account.getUsername()))
                account.setPassword(newPassword);
        }
        accountAdminOnline.setPassword(newPassword);
        DataProcessing.writeListAccount(accountList);
    }

    public void editPasswordUser(String newPassword, Account accountUserOnline) {
        DataProcessing.readListAccount(accountList);
        for (Account account : accountList) {
            if (accountUserOnline.getUsername().equals(account.getUsername()))
                account.setPassword(newPassword);
        }
        accountUserOnline.setPassword(newPassword);
        DataProcessing.writeListAccount(accountList);
    }

    public void findPasswordAgain(String newPassword, String username) {
        DataProcessing.readListAccount(accountList);
        int index = findIndexByUsername(username);
        accountList.get(index).setPassword(newPassword);
        DataProcessing.writeListAccount(accountList);
    }

    public int findIndexByUsername(String username) {
        DataProcessing.readListAccount(AccountManger.accountList);
        for (int i = 0; i < accountList.size(); i++) {
            if (username.equals(accountList.get(i).getUsername())) {
                return i;
            }
        }
        return -1;
    }

    public int findIndexByEmail(String email) {
        DataProcessing.readListAccount(AccountManger.accountList);
        for (int i = 0; i < accountList.size(); i++) {
            if (email.equals(accountList.get(i).getEmail())) {
                return i;
            }
        }
        return -1;
    }

    public boolean checkUsernameExistence(String username) {
        DataProcessing.readListAccount(AccountManger.accountList);
        int index = findIndexByUsername(username);
        if (index != -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkEmailExistence(String email) {
        DataProcessing.readListAccount(AccountManger.accountList);
        int index = findIndexByEmail(email);
        if (index != -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkSecurityQuestion1(String securityQuestion1, String securityQuestionAnswer1, String username) {
        DataProcessing.readListAccount(AccountManger.accountList);
        for (int i = 0; i < accountList.size(); i++) {
            if (username.equals(accountList.get(i).getUsername())) {
                if (securityQuestion1.equals(accountList.get(i).getSecurityQuestion1())) {
                    if (securityQuestionAnswer1.equalsIgnoreCase(accountList.get(i).getSecurityQuestionAnswer1())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkSecurityQuestion2(String securityQuestion2, String securityQuestionAnswer2, String username) {
        DataProcessing.readListAccount(AccountManger.accountList);
        for (int i = 0; i < accountList.size(); i++) {
            if (username.equals(accountList.get(i).getUsername())) {
                if (securityQuestion2.equals(accountList.get(i).getSecurityQuestion2())) {
                    if (securityQuestionAnswer2.equalsIgnoreCase(accountList.get(i).getSecurityQuestionAnswer2())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
