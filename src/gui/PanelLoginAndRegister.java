package gui;

import code.PlayListFileHandling;
import swing.Button;
import swing.MyPasswordField;
import swing.MyTextField;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import code.User;
import code.UserFileHandling;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    private MyTextField userName = new MyTextField();
    private MyPasswordField password = new MyPasswordField();
    private MyTextField txtFN = new MyTextField();
    private MyTextField txtLN = new MyTextField();
    private MyTextField txtEmail = new MyTextField();
    private MyTextField txtUser = new MyTextField();
    private MyPasswordField txtPass = new MyPasswordField();
    private MyPasswordField txtConPass = new MyPasswordField();

    Button cmd = new Button();
    Button cmd1 = new Button();
    Button button1 = new Button();
    Button button2 = new Button();
    Button button3 = new Button();

    private Boolean passwordVisible = false;
    public static String loggedInUserFullName;
    public static String userN;
    //PanelLoginAndRegister Constructor

    public PanelLoginAndRegister(boolean value) {
        if (value == false) {
            setVisible(false);
        }
    }
    //PanelLoginAndRegister Constructor OVERLOADING

    public PanelLoginAndRegister() {
        initComponents();
        Register();
        Login();
        login.setVisible(false);
        register.setVisible(true);
    }

    //Register Designing Function
    private void Register() {

        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]push"));

        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(65, 62, 62));
        register.add(label);

        txtFN.setPrefixIcon(new ImageIcon(getClass().getResource("/Images/user.png")));
        txtFN.setHint("First Name");
        register.add(txtFN, "w 60%");

        txtLN.setPrefixIcon(new ImageIcon(getClass().getResource("/Images/user.png")));
        txtLN.setHint("Last Name");
        register.add(txtLN, "w 60%");

        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/Images/mail.png")));
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 60%");

        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/Images/user.png")));
        txtUser.setHint("Username");
        register.add(txtUser, "w 60%");

        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/Images/pass.png")));
        txtPass.setHint("Password");
        register.add(txtPass, "w 60%");

        button2.setText("Show Password");
        button2.setFont(new Font("sansserif", 1, 12));
        button2.setForeground(new Color(65, 62, 62));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/hide.png")));
        button2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                togglePasswordVisibility(txtPass, button2);
            }
        });
        register.add(button2, "w 40, h 10");

        txtConPass.setPrefixIcon(new ImageIcon(getClass().getResource("/Images/pass.png")));
        txtConPass.setHint("Confirm Password");
        register.add(txtConPass, "w 60%");

        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/hide.png")));
        button3.setText("Show Confirm Password");
        button3.setFont(new Font("sansserif", 1, 12));
        button3.setForeground(new Color(65, 62, 62));
        button3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                togglePasswordVisibility(txtConPass, button3);
            }
        });
        register.add(button3, "w 40, h 10");

        cmd.setBackground(new Color(65, 62, 62));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN UP");
        register.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //Calling Sign UP func =>
                signUp();

            }
        });

    }

    //Login Designing Function
    private void Login() {

        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));

        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(57, 57, 57));
        login.add(label);

        userName.setPrefixIcon(new ImageIcon(getClass().getResource("/Images/mail.png")));
        userName.setHint("Email / Username");
        login.add(userName, "w 60%");

        password.setPrefixIcon(new ImageIcon(getClass().getResource("/Images/pass.png")));
        password.setHint("Password");
        login.add(password, "w 60%");

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/hide.png")));
        button1.setText("Show Password");
        button1.setFont(new Font("sansserif", 1, 12));
        button1.setForeground(new Color(100, 100, 100));
        button1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                togglePasswordVisibility(password, button1);
            }
        });
        login.add(button1, "w 40, h 10");

        cmd1.setBackground(new Color(57, 57, 57));
        cmd1.setForeground(new Color(250, 250, 250));
        cmd1.setText("SIGN IN");
        login.add(cmd1, "w 40%, h 40");
        cmd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //Calling SignIN function
                signIn();
            }
        });
    }

    //This function is called in Main Function for showing or visible the login or register form => 
    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    //For visible password
    private void togglePasswordVisibility(MyPasswordField password1, Button button4) {
        passwordVisible = !passwordVisible;
        char echoChar = passwordVisible ? 0 : '*';
        password1.setEchoChar(echoChar);
        if (passwordVisible == true) {
            button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/unhide.png")));
            button4.setForeground(new Color(255, 51, 51));// NOI18N
        } else {
            button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/hide.png")));
            button4.setForeground(new Color(65, 62, 62));
        }
    }
    //    validations login by default false 

    public boolean authenticateUser(String userName, String password) {
        UserFileHandling h = new UserFileHandling();
        boolean value = false;
        for (User user : h.getUsers()) {
            if ((user.getUserName().equals(userName) || user.getEmail().equals(userName))
                    && user.getPassword().equals(password)) {
                loggedInUserFullName = user.getFullName();
                userN = user.getUserName();
                value = true;
            }
        }
        return value;
    }

    //sign in function(validation)
    private void signIn() {
        String username = userName.getText();
        String pass = password.getText();
        if (username.equals("admin") && pass.equals("admin")) {
            new AdminMainMenu().setVisible(true);
            userName.setText("");
            password.setText("");
        } else if (authenticateUser(username, pass)) {
            new UserMainMenu().setVisible(true);
            new PlayListFileHandling().createPlayListFile();
            userName.setText("");
            password.setText("");
            
        } else {
            JOptionPane.showMessageDialog(this, "Login Unsuccesful!", "Login Response", 0);
            userName.setText("");
            password.setText("");
        }
    }

    //ALL Validations for Sign UP / Sign Up Form 
    public void signUp() {
        //using regex .matches ^[a-zA-Z]*$ for not getting symbols and numbers in first and last name
//        String userN = txtUser.getText();
        if (txtFN.getText().length() < 12 && txtFN.getText().matches("^[a-zA-Z]*$") && txtLN.getText().length() < 12
                && txtLN.getText().matches("^[a-zA-Z]*$") && txtEmail.getText().contains("@") && txtEmail.getText().contains(".com") && txtUser.getText().length() < 12 && txtPass.getText().length() < 12 && txtPass.getText().equals(txtConPass.getText())) {
            UserFileHandling userdata = new UserFileHandling();
            User user = new User(txtUser.getText(), txtPass.getText(), txtFN.getText(), txtLN.getText(), txtEmail.getText());
            ArrayList<User> data = userdata.getUsers();
            data.add(user);
            userdata.saveUserData(data);
            ImageIcon icon = new ImageIcon("src/Images/done.png");
            JOptionPane.showMessageDialog(null, "Account Successfully Created",
                    "Response Message", JOptionPane.INFORMATION_MESSAGE, icon);
            new PlayListFileHandling().createPlayListFile();
            txtFN.setText("");
            txtLN.setText("");
            txtEmail.setText("");
            txtUser.setText("");
            txtPass.setText("");
            txtConPass.setText("");
        } else {
            //If all the Text Fields are empty then this runs =>
            if (txtFN.getText().isBlank() || txtLN.getText().isBlank() || txtEmail.getText().isBlank() || txtUser.getText().isBlank() || txtPass.getText().isBlank() || txtConPass.getText().isBlank() || txtFN.getText().isEmpty() || txtLN.getText().isEmpty() || txtEmail.getText().isEmpty() || txtUser.getText().isEmpty() || txtPass.getText().isEmpty() || txtConPass.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ERROR! The given Fields are empty", "SignUp Response", 0);
            } //If the User Put wrong Commands this will run =>
            else {

                if (txtFN.getText().length() >= 12) {
                    JOptionPane.showMessageDialog(null, "First name can't be too long", "SignUp Response", 0);
                    txtFN.setText("");
                }

                if (!(txtFN.getText().matches("^[a-zA-Z]*$"))) {
                    JOptionPane.showMessageDialog(null, "First name can't contain numbers and symbols", "SignUp Response", 0);
                    txtFN.setText("");
                }

                //Last Name
                if (txtLN.getText().length() >= 12) {
                    JOptionPane.showMessageDialog(null, "Last name can't be too long", "SignUp Response", 0);
                    txtLN.setText("");
                }
                if (!(txtLN.getText().matches("^[a-zA-Z]*$"))) {
                    JOptionPane.showMessageDialog(null, "Last name can't contain numbers and symbols", "SignUp Response", 0);
                    txtLN.setText("");
                }
                //Email
                if (!(txtEmail.getText().contains("@")) || !((txtEmail.getText().contains(".com")))) {
                    JOptionPane.showMessageDialog(null, "Email must contain @ and .com", "SignUp Response", 0);
                    txtEmail.setText("");
                }

                //Username
                if (txtUser.getText().length() >= 12) {
                    JOptionPane.showMessageDialog(null, "Username must be of 12", "SignUp Response", 0);
                    txtUser.setText("");
                }

                //Password
                if (txtPass.getText().length() >= 12) {
                    JOptionPane.showMessageDialog(null, "Password length must be 12 characters", "SignUp Response", 0);
                    txtPass.setText("");
                }
                //Confirm Password
                if (!(txtPass.getText().equals(txtConPass.getText()))) {
                    JOptionPane.showMessageDialog(null, "Password and Confirm Password must be Same", "SignUp Response", 0);
                    txtConPass.setText("");
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables

}
