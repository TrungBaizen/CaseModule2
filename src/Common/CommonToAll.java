package Common;

import java.util.Scanner;
import java.util.regex.Pattern;

import Model.Account;

public class CommonToAll {
    private static final String MAIL_REGEX = "^.[A-Za-z0-9]+@(codegym|gmail|yahoo)+\\.+(com|vn|com.vn)$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=])[a-zA-Z\\d@#$%^&+=]{6,32}$";
    private static final String USERNAME_REGEX = "^.{8,32}$";
    private static final String INT_GREATER_THAN_ZERO_REGEX = "^([1-9][0-9]*)";
    public static Account accountUserOnline = null;
    public static Account accountAdminOnline = null;
    public static String key;
    public static Scanner scannerInteger = new Scanner(System.in);
    public static Scanner scannerString = new Scanner(System.in);

    public static int ScannerInteger() {
        try {
            return scannerInteger.nextInt();
        } catch (Exception e) {
            System.out.println("Nhập sai định dạng.");
            System.out.println("Vui lòng nhập lại.");
            scannerInteger.nextLine();
            return ScannerInteger();
        }

    }

    public static String ScannerString() {
        try {
            return scannerString.nextLine();
        } catch (Exception e) {
            System.out.println("Nhập sai định dạng.");
            System.out.println("Vui lòng nhập lại.");
            scannerString.nextLine();
            return ScannerString();
        }
    }

    public static int ScannerIntGreaterThanZero() {
        Pattern pattern = Pattern.compile(INT_GREATER_THAN_ZERO_REGEX);
        try {
            int information = scannerInteger.nextInt();
            if (!pattern.matcher(String.valueOf(information)).matches()) {
                throw new IllegalArgumentException();
            }
            return information;
        } catch (IllegalArgumentException iae) {
            System.out.println("Hãy bắt đầu từ 1.");
            System.out.println("Vui lòng nhập lại.");
            scannerInteger.nextLine();
            return ScannerIntGreaterThanZero();
        } catch (Exception e) {
            System.out.println("Nhập sai định dạng.");
            System.out.println("Vui lòng nhập lại.");
            scannerInteger.nextLine();
            return ScannerIntGreaterThanZero();
        }
    }

    public static String ScannerUsername() {
        Pattern pattern = Pattern.compile(USERNAME_REGEX);
        try {
            String information = scannerString.nextLine();
            if (!pattern.matcher(information).matches()) {
                throw new Exception();
            }
            return information;
        } catch (Exception e) {
            System.out.println("Username có từ 8-32 kí tự");
            System.out.println("Vui lòng nhập lại.");
            return ScannerUsername();
        }
    }

    public static String ScannerPassword() {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        try {
            String information = scannerString.nextLine();
            if (!pattern.matcher(information).matches()) {
                throw new Exception();
            }
            return information;
        } catch (Exception e) {
            System.out.println("Password có 6-32 kí tự và bao gồm cả chữ và số , kí tự đặc biệt.");
            System.out.println("Vui lòng nhập lại.");
            return ScannerPassword();
        }
    }

    public static String ScannerEmail() {
        Pattern pattern = Pattern.compile(MAIL_REGEX);
        try {
            String information = scannerString.nextLine();
            if (!pattern.matcher(information).matches()) {
                throw new Exception();
            }
            return information;
        } catch (Exception e) {
            System.out.println("Sai định dạng email.");
            System.out.println("Vui lòng nhập lại.");
            return ScannerEmail();
        }
    }


}

