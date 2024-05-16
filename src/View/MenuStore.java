package View;

import Service.ProductManager;
import Service.CartManager;
import Service.AccountManger;
import Common.CommonToAll;
import Model.Product;
import Model.Account;

import java.util.List;

public class MenuStore {
    private final ProductManager productManager;
    private final AccountManger accountManger;
    private final CartManager cartManager;

    public MenuStore() {
        accountManger = new AccountManger();
        productManager = new ProductManager();
        cartManager = new CartManager();
    }

    public void showMenuHome() {
        while (true) {
            System.out.println("-------Trang chủ-------");
            System.out.println("1.Danh sách sản phẩm.");
            System.out.println("2.Tìm kiếm sản phẩm.");
            System.out.println("3.Đăng nhập.");
            System.out.println("4.Đăng ký.");
            System.out.println("5.Quên mật khẩu.");
            System.out.println("0.Thoát khỏi chương trình");
            System.out.print("Nhập lựa chọn: ");
            int choice = CommonToAll.ScannerInteger();
            switch (choice) {
                case 1:
                    showAllProduct();
                    break;
                case 2:
                    showAllSearchProduct();
                    break;
                case 3:
                    showMenuLogin();
                    break;
                case 4:
                    showMenuSignup();
                    break;
                case 5:
                    showFindPasswordAgain();
                    break;
                case 0:
                    System.out.println("Thoát chương trình thành công");
                    return;
                default:
                    System.out.println("Lựa chọn không đúng vui lòng lựa chọn lại.");
            }
        }
    }

    public void showAllProduct() {
        System.out.println("---Danh sách sản phẩm---");
        productManager.displayAllProduct();
        while (true) {
            System.out.println("1.Dach sách theo giá giảm dần.");
            System.out.println("2.Dach sách theo giá tăng dần.");
            System.out.println("0.Trở về trang chủ.");
            System.out.println("Hãy nhập lựa chọn: ");
            int choice = CommonToAll.ScannerInteger();
            switch (choice) {
                case 1:
                    System.out.println("---Danh sách sản phẩm---");
                    productManager.displayAllProductDecrease();
                    break;
                case 2:
                    System.out.println("---Danh sách sản phẩm---");
                    productManager.displayAllProductAscending();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không đúng vui lòng lựa chọn lại.");
            }
        }

    }

    public void showAllSearchProduct() {
        productManager.displayAllProduct();
        System.out.println("---Tìm kiếm sản phẩm---");
        System.out.println("Hãy nhập tên sản phẩm muốn tìm: ");
        String name = CommonToAll.ScannerString();
        List<Product> searchProductList = productManager.findProductByName(name);
        if (searchProductList == null) {
            System.out.println("Hiện không có thông tin sản phẩm trong danh sách.");
        } else {
            System.out.println("Kết quả tìm kiếm: ");
            searchProductList.forEach(System.out::println);
        }
    }

    public void showMenuLogin() {
        System.out.println("---Đăng nhập---");
        System.out.println("Nhập tài khoản:");
        String username = CommonToAll.ScannerUsername();
        System.out.println("Nhập password:");
        String password = CommonToAll.ScannerPassword();
        Account account = accountManger.findAccount(username, password);
        if (account == null) {
            System.out.println("Đăng nhập thất bại.");
        } else if (account.getRole().equals("Admin")) {
            CommonToAll.accountAdminOnline = account;
            showMenuAdmin();
        } else if (account.getRole().equals("User")) {
            CommonToAll.accountUserOnline = account;
            showMenuUser();
        }
    }

    public void showMenuSignup() {
        System.out.println("---Đăng ký---");
        System.out.println("Nhập tên người dùng:");
        String name = CommonToAll.ScannerString();
        System.out.println("Nhập tài khoản:");
        String username = CommonToAll.ScannerUsername();
        while (accountManger.checkUsernameExistence(username)) {
            System.out.println("Tài khoản đã tồn tại.");
            System.out.println("Nhập tài khoản khác:");
            username = CommonToAll.ScannerUsername();
        }
        System.out.println("Nhập password:");
        String password = CommonToAll.ScannerPassword();
        System.out.println("Nhập email");
        String email = CommonToAll.ScannerEmail();
        while (accountManger.checkEmailExistence(email)) {
            System.out.println("Email đã tồn tại.");
            System.out.println("Nhập email khác:");
            email = CommonToAll.ScannerEmail();
        }
        showSecurityQuestion1();
        System.out.println("Nhập câu trả lời:");
        String securityQuestionAnswers1 = CommonToAll.ScannerString();
        showSecurityQuestion2();
        System.out.println("Nhập câu trả lời:");
        String securityQuestionAnswers2 = CommonToAll.ScannerString();
        Account newUser = new Account(name, username, password, email, AccountManger.securityQuestion1,
                AccountManger.securityQuestion2, securityQuestionAnswers1, securityQuestionAnswers2, "User");
        accountManger.addAccount(newUser);
        System.out.println("Tạo tài khoản thành công");
        AccountManger.securityQuestion1 = null;
        AccountManger.securityQuestion2 = null;
    }

    public void showSecurityQuestion1() {
        while (true) {
            System.out.println("Danh sách câu hỏi bảo mật 1:");
            System.out.println("1." + AccountManger.question1);
            System.out.println("2." + AccountManger.question2);
            System.out.println("3." + AccountManger.question3);
            System.out.println("Nhập lựa chọn:");
            int choice = CommonToAll.ScannerInteger();
            switch (choice) {
                case 1:
                    System.out.println("Câu hỏi bảo mật 1: ");
                    System.out.println(AccountManger.question1);
                    AccountManger.securityQuestion1 = AccountManger.question1;
                    return;
                case 2:
                    System.out.println("Câu hỏi bảo mật 1: ");
                    System.out.println(AccountManger.question2);
                    AccountManger.securityQuestion1 = AccountManger.question2;
                    return;
                case 3:
                    System.out.println("Câu hỏi bảo mật 1: ");
                    System.out.println(AccountManger.question3);
                    AccountManger.securityQuestion1 = AccountManger.question3;
                    return;
                default:
                    System.out.println("Lựa chọn không đúng vui lòng lựa chọn lại.");
            }
        }
    }

    public void showSecurityQuestion2() {
        while (true) {
            System.out.println("Danh sách câu hỏi bảo mật 2:");
            System.out.println("1." + AccountManger.question4);
            System.out.println("2." + AccountManger.question5);
            System.out.println("3." + AccountManger.question6);
            System.out.println("Nhập lựa chọn:");
            int choice = CommonToAll.ScannerInteger();
            switch (choice) {
                case 1:
                    System.out.println("Câu hỏi bảo mật 2: ");
                    System.out.println(AccountManger.question4);
                    AccountManger.securityQuestion2 = AccountManger.question4;
                    return;
                case 2:
                    System.out.println("Câu hỏi bảo mật 2: ");
                    System.out.println(AccountManger.question5);
                    AccountManger.securityQuestion2 = AccountManger.question5;
                    return;
                case 3:
                    System.out.println("Câu hỏi bảo mật 2: ");
                    System.out.println(AccountManger.question6);
                    AccountManger.securityQuestion2 = AccountManger.question6;
                    return;
                default:
                    System.out.println("Lựa chọn không đúng vui lòng lựa chọn lại.");
            }
        }
    }

    public void showFindPasswordAgain() {
        System.out.println("---Lấy lại mật khẩu---");
        System.out.println("Hãy nhật tài khoản: ");
        String username = CommonToAll.ScannerUsername();
        if (accountManger.checkUsernameExistence(username)) {
            showSecurityQuestion1();
            System.out.println("Nhập câu trả lời câu hỏi bảo mật số 1: ");
            String securityQuestionAnswer1 = CommonToAll.ScannerString();
            showSecurityQuestion2();
            System.out.println("Nhập câu trả lời câu hỏi bảo mật số 2: ");
            String securityQuestionAnswer2 = CommonToAll.ScannerString();
            boolean checkSecurityQuention1 = accountManger.checkSecurityQuestion1(AccountManger.securityQuestion1, securityQuestionAnswer1, username);
            boolean checkSecurityQuention2 = accountManger.checkSecurityQuestion2(AccountManger.securityQuestion2, securityQuestionAnswer2, username);
            if (checkSecurityQuention1) {
                if (checkSecurityQuention2) {
                    System.out.println("Bạn đã trả lời đúng.");
                    System.out.println("Nhập mật khẩu mới: ");
                    String newPassword = CommonToAll.ScannerPassword();
                    accountManger.findPasswordAgain(newPassword, username);
                    System.out.println("Lấy lại mật khẩu thành công.");
                } else {
                    System.out.println("Câu trả lời hoặc câu hỏi bảo mật số 2.");
                    System.out.println("Vui lòng trả lời lại.");
                    showFindPasswordAgain();
                }
            } else {
                System.out.println("Câu trả lời hoặc câu hỏi bảo mật số 1 sai.");
                System.out.println("Vui lòng trả lời lại.");
                showFindPasswordAgain();
            }
        } else {
            System.out.println("Tài khoản hiện chưa tồn tại.");
        }
        AccountManger.securityQuestion1 = null;
        AccountManger.securityQuestion2 = null;
    }

    public void showMenuAdmin() {
        System.out.println("Xin chào Admin " + CommonToAll.accountAdminOnline.getName());
        while (true) {
            System.out.println("-------Trang chủ-------");
            System.out.println("1.Danh sách sản phẩm.");
            System.out.println("2.Tìm kiếm sản phẩm.");
            System.out.println("3.Thêm sản phẩm.");
            System.out.println("4.Sửa sản phẩm.");
            System.out.println("5.Xóa sản phẩm.");
            System.out.println("6.Lịch sử bán hàng.");
            System.out.println("7.Thay đổi mật khẩu tài khoản.");
            System.out.println("0.Thoát tài khoản.");
            System.out.print("Nhập lựa chọn: ");
            int choice = CommonToAll.ScannerInteger();
            switch (choice) {
                case 1:
                    showAllProduct();
                    break;
                case 2:
                    showAllSearchProduct();
                    break;
                case 3:
                    showAddProduct();
                    break;
                case 4:
                    showEditProduct();
                    break;
                case 5:
                    showRemoveProduct();
                    break;
                case 6:
                    showHistory();
                    break;
                case 7:
                    showEditPasswordAdmin();
                    break;
                case 0:
                    System.out.println("Thoát tài khoaản thành công.");
                    CommonToAll.accountAdminOnline = null;
                    return;
                default:
                    System.out.println("Lựa chọn không đúng vui lòng lựa chọn lại.");
            }
        }
    }

    public void showMenuUser() {
        System.out.println("Xin chào " + CommonToAll.accountUserOnline.getName());
        while (true) {
            System.out.println("-------Trang chủ-------");
            System.out.println("1.Danh sách sản phẩm.");
            System.out.println("2.Tìm kiếm sản phẩm.");
            System.out.println("3.Thêm vào giỏ hàng.");
            System.out.println("4.Xem giỏ hàng.");
            System.out.println("5.Lịch sử giao dịch.");
            System.out.println("6.Thay đổi mật khẩu tài khoản");
            System.out.println("0.Thoát tài khoản");
            System.out.print("Nhập lựa chọn: ");
            int choice = CommonToAll.ScannerInteger();
            switch (choice) {
                case 1:
                    showAllProduct();
                    break;
                case 2:
                    showAllSearchProduct();
                    break;
                case 3:
                    showBuyProduct();
                    break;
                case 4:
                    showCart();
                    break;
                case 5:
                    showCumtomerHistoryList();
                    break;
                case 6:
                    showEditPasswordUser();
                    break;
                case 0:
                    System.out.println("Thoát tài khoản thành công.");
                    CommonToAll.accountUserOnline = null;
                    return;
                default:
                    System.out.println("Lựa chọn không đúng vui lòng lựa chọn lại.");
            }
        }
    }

    public void showCumtomerHistoryList() {
        System.out.println("---Lịch sử giao dịch---");
        ProductManager.displayCustomerHistoryList();
    }

    public void showEditPasswordAdmin() {
        System.out.println("---Thay đổi mật khẩu---");
        System.out.println("Mật khẩu cũ:");
        String password = CommonToAll.ScannerPassword();
        if (accountManger.checkPassword(password)) {
            System.out.println("Mật khẩu mới:");
            String newPassword = CommonToAll.ScannerPassword();
            accountManger.editPasswordAdmin(newPassword, CommonToAll.accountAdminOnline);
        } else {
            System.out.print("Sai mật khẩu. ");
            System.out.println("Vui lòng thử lại.");
            showEditPasswordAdmin();
        }
        System.out.println("Thay đổi mật khẩu thành công.");
    }

    public void showEditPasswordUser() {
        System.out.println("---Thay đổi mật khẩu---");
        System.out.println("Mật khẩu cũ:");
        String password = CommonToAll.ScannerPassword();
        if (accountManger.checkPassword(password)) {
            System.out.println("Mật khẩu mới:");
            String newPassword = CommonToAll.ScannerPassword();
            accountManger.editPasswordUser(newPassword, CommonToAll.accountUserOnline);
        } else {
            System.out.print("Sai mật khẩu. ");
            System.out.println("Vui lòng thử lại.");
            showEditPasswordUser();
        }
        System.out.println("Thay đổi mật khẩu thành công.");
    }

    public void showHistory() {
        System.out.println("---Lịch sử giao dịch---");
        productManager.displayHistory();
    }

    public void showAddProduct() {
        productManager.displayAllProduct();
        System.out.println("---Thêm sản phẩm---");
        System.out.println("Tên sản phẩm:");
        String nameProduct = CommonToAll.ScannerString();
        System.out.println("Giá sản phâm:");
        int priceProduct = CommonToAll.ScannerIntGreaterThanZero();
        System.out.println("Số lượng:");
        int quantityProduct = CommonToAll.ScannerIntGreaterThanZero();
        productManager.addProduct(nameProduct, priceProduct, quantityProduct);
        System.out.println("Thêm thành công.");
    }

    public void showRemoveProduct() {
        productManager.displayAllProduct();
        System.out.println("---Xóa sản phẩm---");
        System.out.println("Nhập id sản phẩm muốn xóa:");
        int id = CommonToAll.ScannerIntGreaterThanZero();
        int index = productManager.findIndexById(id);
        if (index == -1) {
            System.out.println("Id không tồn tại.");
        } else {
            productManager.removeProduct(index);
            System.out.println("Xóa thành công.");
        }
    }

    public void showEditProduct() {
        productManager.displayAllProduct();
        System.out.println("---Sửa sản phẩm---");
        System.out.println("Nhập id sản phẩm muốn sửa:");
        int id = CommonToAll.ScannerIntGreaterThanZero();
        int index = productManager.findIndexById(id);
        if (index == -1) {
            System.out.println("Id không tồn tại.");
        } else {
            Product oldProduct = productManager.getProduct(index);
            System.out.println(oldProduct);
            System.out.println("Nhập tên mới cho sản phẩm:");
            String newName = CommonToAll.ScannerString();
            System.out.println("Nhập giá:");
            int newPrice = CommonToAll.ScannerIntGreaterThanZero();
            System.out.println("Nhập số lượng:");
            int newQuantity = CommonToAll.ScannerIntGreaterThanZero();
            productManager.editProduct(newName, newPrice, newQuantity, oldProduct);
            System.out.println("Sửa thành công");
        }
    }

    public void showBuyProduct() {
        productManager.displayAllProduct();
        System.out.println("---Thêm vào giỏ hàng---");
        System.out.println("Nhập id sản phẩm muốn mua: ");
        int id = CommonToAll.ScannerIntGreaterThanZero();
        if (productManager.checkProductExistence(id)) {
            Product productAddToCart = productManager.findProductById(id);
            while (true) {
                System.out.println("Nhập số lượng muốn mua: ");
                int purchaseQuantity = CommonToAll.ScannerIntGreaterThanZero();
                if (productManager.checkProductExistence(id)) {
                    if (purchaseQuantity <= productAddToCart.getQuantity() && cartManager.checkQuantityToCart(productAddToCart, purchaseQuantity)) {
                        cartManager.addProductToCart(productAddToCart, purchaseQuantity);
                        System.out.println("Thêm vào giỏ hàng thành công");
                        return;
                    } else if (productAddToCart.getQuantity() == 0) {
                        System.out.println("Hiện tại số lượng sản phẩm đã hết.");
                        return;
                    } else {
                        System.out.println("Số lượng mua vượt quá số lượng còn lại.");
                        return;
                    }
                } else {
                    System.out.println("Sản phẩm hiện không còn.");
                    return;
                }
            }
        } else {
            System.out.println("Sản phẩm hiện không còn.");
        }
    }

    public void showCart() {
        System.out.println("---Giỏ hàng---");
        cartManager.displayCart();
        while (true) {
            System.out.println("1.Xóa sản phẩm.");
            System.out.println("2.Mua sản phẩm.");
            System.out.println("0.Thoát giỏ hàng.");
            System.out.println("Nhập lựa chọn:");
            int choice = CommonToAll.ScannerInteger();
            switch (choice) {
                case 1:
                    showRemoveProductInCart();
                    break;
                case 2:
                    showBuyProductInCart();
                    break;
                case 0:
                    System.out.println("Thoát giỏ hàng thành công.");
                    return;
                default:
                    System.out.println("Lựa chọn không đúng vui lòng lựa chọn lại.");
            }
        }
    }

    public void showRemoveProductInCart() {
        System.out.println("---Giỏ hàng---");
        cartManager.displayCart();
        System.out.println("Nhập id sản phẩm muốn xóa:");
        int id = CommonToAll.ScannerIntGreaterThanZero();
        int index = cartManager.findIndexById(id);
        if (index == -1) {
            System.out.println("Sản phẩm hiên tại không có trong giỏ hàng.");
        } else {
            cartManager.removeProduct(index);
            System.out.println("Xóa sản phẩm khỏi giỏ hàng thành công.");
        }
    }

    public void showBuyProductInCart() {
        System.out.println("---Giỏ hàng---");
        cartManager.displayCart();
        System.out.println("Nhập id sản phẩm muốn mua:");
        int id = CommonToAll.ScannerIntGreaterThanZero();
        int indexCart = cartManager.findIndexById(id);
        int indexProduct = productManager.findIndexById(id);
        if (productManager.checkProductExistence(id)) {
            if (indexCart == -1) {
                System.out.println("Sản phẩm hiện tại không có trong giỏ hàng.");
            } else {
                if (cartManager.checkProductExistence(indexCart)) {
                    cartManager.buyProduct(indexCart, indexProduct);
                    System.out.println("Mua thành công");
                } else {
                    cartManager.removeProduct(indexCart);
                    System.out.println("Số lượng trong giỏ hàng không phù hợp với số lượng thực tế.");
                }
            }
        } else {
            cartManager.removeProduct(indexCart);
            System.out.println("Sản phẩm hiện không còn");
        }
    }
}
