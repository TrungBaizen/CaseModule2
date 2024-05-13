package Service;

import Model.Account;
import Model.Cart;
import Model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataProcessing {
    public static void readMapCart(Map<String, List<Cart>> cartList) {
        cartList.clear();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Data/cartList.txt"))) {
            String line;
            String key = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                if (line.endsWith(":")) {
                    String[] parts = line.split(":");
                    key = parts[0].trim();
                    cartList.put(key, new ArrayList<>());
                } else {
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int price = Integer.parseInt(parts[2].trim());
                    int quantity = Integer.parseInt(parts[3].trim());
                    int purchaseQuantity = Integer.parseInt(parts[4].trim());
                    Product product = new Product(id, name, price, quantity);
                    Cart cart = new Cart(product, purchaseQuantity);
                    cartList.get(key).add(cart);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeMapCart(Map<String, List<Cart>> cartList) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/Data/cartList.txt"))) {
            for (Map.Entry<String, List<Cart>> listEntry : cartList.entrySet()) {
                bufferedWriter.write(listEntry.getKey());
                bufferedWriter.write(":");
                bufferedWriter.newLine();
                List<Cart> values = listEntry.getValue();
                for (int i = 0; i < values.size(); i++) {
                    bufferedWriter.write(values.get(i).getProduct().getId() + " , " + values.get(i).getProduct().getName() + " , " + values.get(i).getProduct().getPrice() + " , "
                            + values.get(i).getProduct().getQuantity() + " , " + values.get(i).getPurchaseQuantity() + " , " + values.get(i).getTotalPrice());
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readListAccount(List<Account> list) {
        list.clear();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Data/accountList.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                String userName = parts[1].trim();
                String password = parts[2].trim();
                String email = parts[3].trim();
                String securityQuestion1 = parts[4].trim();
                String securityQuestion2 = parts[5].trim();
                String securityQuestionAnswer1 = parts[6].trim();
                String securityQuestionAnswer2 = parts[7].trim();
                String role = parts[8].trim();
                list.add(new Account(name, userName, password, email, securityQuestion1,
                        securityQuestion2, securityQuestionAnswer1, securityQuestionAnswer2, role));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void writeListAccount(List<Account> list) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/Data/accountList.txt"))) {
            for (Account account : list) {
                bufferedWriter.write(account.getName() + "," + account.getUsername() + "," + account.getPassword() + "," + account.getEmail() + "," + account.getSecurityQuestion1() + "," +
                        account.getSecurityQuestion2() + "," + account.getSecurityQuestionAnswer1() + "," + account.getSecurityQuestionAnswer2() + "," + account.getRole());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readListProduct(List<Product> list) {
        list.clear();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Data/productList.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                int price = Integer.parseInt(parts[2].trim());
                int quantity = Integer.parseInt(parts[3].trim());
                list.add(new Product(id, name, price, quantity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeListProduct(List<Product> list) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/Data/productList.txt"))) {
            for (Product product : list) {
                bufferedWriter.write(product.getId() + "," + product.getName() + "," + product.getPrice() + "," + product.getQuantity());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readHistoryList(List<Object> list) {
        list.clear();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Data/historyList.txt"))) {
            String line;
            String username = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("Username:")) {
                    String[] parts = line.split(" ");
                    username = parts[0].trim();
                } else {
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0].split(":")[1].trim());
                    String name = parts[1].split(":")[1].trim();
                    int price = Integer.parseInt(parts[2].split(":")[1].trim());
                    int purchaseQuantity = Integer.parseInt(parts[3].split(":")[1].trim());
                    Product product = new Product(id, name, price);
                    list.add(username);
                    list.add(new Cart(product, purchaseQuantity));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeHistoryList(List<Object> list) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/Data/historyList.txt"))) {
            for (Object object : list) {
                bufferedWriter.write(object.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int readCountProduct() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/CountProduct.txt"))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                count = Integer.parseInt(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void writeCountProduct(int count) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/Data/CountProduct.txt"))) {
            bufferedWriter.write(String.valueOf(count));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
