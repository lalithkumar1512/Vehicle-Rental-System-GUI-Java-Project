import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.ref.Cleaner;
import java.util.Arrays;
import java.util.List;

import java.sql.*;
public class VEHICLE {
    private JPanel Main;
    private JTextField txtName;
    private JTextField txtModel;
    private JTextField txtPrice;
    private JButton saveButton;
    private JTable table1;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField txtid1;
    private JScrollPane table_1;
    private JButton searchButton;
    private JTextField txtid2;
    private JTextField txtid3;
    private JButton countButton;
    private JButton sumButton;
    private JButton averageButton;
    private JTextField txtc;
    private JTextField txtCount;
    private JTextField txtSum;
    private JTextField txtAverage;
    private JTextField txto1;
    private JTextField txto2;
    private JTextField txto3;
    private JTextField txtov;
    private JTextField txtoc;
    private JTextField txtop;
    private JTextField txtos;
    private JTextField txtvid;
    private JButton search;
    private JTextField txtid4;
   // private JScrollPane table_2;
    private JTable table2;
    private JTextField txtpf;
    private JButton pricefbutton;
    private JTextField txtsf;
    private JButton statusfButton;
    private JButton insertButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("VEHICLE");
        frame.setContentPane(new VEHICLE().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab?autoReconnect=true&useSSL=false", "root", "lalith");
            System.out.println("Successs");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    void table_load() {
        try {
            pst = con.prepareStatement("select * from vehicles");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void table_load1(int p) {
        try {
            pst = con.prepareStatement("select * from vehicles where price<?");
            pst.setInt(1, p);
            ResultSet rs = pst.executeQuery();
            table2.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void table_load2(String p) {
        try {
            pst = con.prepareStatement("select * from vehicles where status=?");
            pst.setString(1, p);
            ResultSet rs = pst.executeQuery();
            table2.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public VEHICLE() {
        connect();
        table_load();
        //table_load1(4500000);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vid, vname, company, price, status;
                vid =txtvid.getText();
                company = txtc.getText();
                vname = txtName.getText();
                price = txtPrice.getText();
                status = txtModel.getText();

                try {
                    pst = con.prepareStatement("insert into vehicles(vid,vname,company,price,status)values(?,?,?,?,?)");
                    pst.setString(1, vid);
                    pst.setString(2, vname);
                    pst.setString(3, company);
                    pst.setString(4, price);
                    pst.setString(5, status);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                    table_load();
                    txtvid.setText("");
                    txtName.setText("");
                    txtc.setText("");
                    txtPrice.setText("");
                    txtModel.setText("");
                    txtName.requestFocus();
                } catch (SQLException e1) {

                    e1.printStackTrace();
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String vid = txtid1.getText();

                    pst = con.prepareStatement("select vname,company,price,status from vehicles where vid = ?");
                    pst.setString(1, vid);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next() == true) {
                        String vname = rs.getString(1);
                        String company = rs.getString(2);
                        String price = rs.getString(3);
                        String status = rs.getString(4);
                        txtov.setText(vname);
                        txtoc.setText(company);
                        txtop.setText(price);
                        txtos.setText(status);
                        table_load();
                    } else {
                        txtov.setText("");
                        txtoc.setText("");
                        txtop.setText("");
                        txtos.setText("");
                        JOptionPane.showMessageDialog(null, "Invalid vid");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vid, vname, company, price, status;
                vid = txtid2.getText();
                vname = txtName.getText();
                company = txtc.getText();
                price = txtPrice.getText();
                status = txtModel.getText();

                try {
                    pst = con.prepareStatement("update vehicles set vname = ?,company = ?,price = ?,status = ? where vid = ?");
                    pst.setString(1, vname);
                    pst.setString(2, company);
                    pst.setString(3, price);
                    pst.setString(4, status);
                    pst.setString(5, vid);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updateed!!!!!");
                    table_load();
                    txtName.setText("");
                    txtc.setText("");
                    txtPrice.setText("");
                    txtModel.setText("");
                    txtName.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vid;
                vid = txtid3.getText();

                try {
                    pst = con.prepareStatement("delete from vehicles  where vid = ?");

                    pst.setString(1, vid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleteeeeed!!!!!");
                    table_load();
                    txtName.setText("");
                    txtPrice.setText("");
                    txtModel.setText("");
                    txtName.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String company = txtCount.getText();

                    pst = con.prepareStatement("select count(*) from vehicles where company = ?");
                    pst.setString(1, company);
                    ResultSet rs = pst.executeQuery();
                    System.out.println(rs);
                    if (rs.next() == true) {
                        String n = rs.getString(1);
                        //System.out.println(n);
                        txto1.setText(n);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        sumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String com = txtSum.getText();

                    pst1 = con.prepareStatement("select sum(price) from vehicles where company = ?");
                    pst1.setString(1, com);
                    ResultSet rs1 = pst1.executeQuery();
                    System.out.println(rs1);
                    if (rs1.next() == true) {
                        String n = rs1.getString(1);
                        //System.out.println(n);
                        txto2.setText(n);
                        if(n==null)
                            txto3.setText("0");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        averageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String company = txtAverage.getText();

                    pst = con.prepareStatement("select avg(price) from vehicles where company = ?");
                    pst.setString(1, company);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next() == true) {
                        String n = rs.getString(1);
                        //System.out.println(n);
                        txto3.setText(n);
                        if(n==null)
                            txto3.setText("0");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = txtid4.getText();
                    pst = con.prepareStatement("select count(*) from vehicles;");
                   // pst.setString(1,name);
                    ResultSet rs = pst.executeQuery();
                    System.out.println(rs);
                    int na = 0;
                    if (rs.next() == true) {
                        na = rs.getInt(1);
                        System.out.println(na);
                       // txto1.set(na);
                    }
                        //txto1.setText(n);
                    pst = con.prepareStatement("select vname from vehicles;");
                    //pst = con.prepareStatement("select avg(price) from vehicles where company = ?");
                    //pst.setString(1, company);
                    ResultSet rs2 = pst.executeQuery();

                    /*if (rs2.next() == true) {
                        String n = rs2.getString(1);
                        System.out.println(n);
                        //txto3.setText(n);
                    }*/
                    //System.out.println(n);
                    String[] vname=new String[na];
                    String vame;
                    int i=1;
                    //vame= rs2.getString(1);
                    //System.out.println(vame);
                    while(rs2.next() == true) {
                        vname[i-1] = rs2.getString(1);
                        System.out.println(vname[i-1]);
                        i++;
                        //txtov.setText(vname);
                    }
                    System.out.println(vname[0]+" "+vname[1]);
                    check c=new check(vname,na);
                    String k=c.chek(name);
                    System.out.println(k);
                    if(k!=null){
                        pst = con.prepareStatement("select vname,company,price,status from vehicles where vname=?;");
                        pst.setString(1,k);
                        ResultSet rs3 = pst.executeQuery();
                        if (rs3.next() == true) {
                        String nam = rs3.getString(1);
                        String company = rs3.getString(2);
                        String price = rs3.getString(3);
                        String status = rs3.getString(4);
                        txtov.setText(nam);
                        txtoc.setText(company);
                        txtop.setText(price);
                        txtos.setText(status);}
                    } else {
                        txtov.setText("");
                        txtoc.setText("");
                        txtop.setText("");
                        txtos.setText("");
                        JOptionPane.showMessageDialog(null, "Invalid vid");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        pricefbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String price=txtpf.getText();
                int i=Integer.parseInt(price);
                table_load1(i);
            }
        });
        statusfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String status=txtsf.getText();
                table_load2(status);
            }
        });
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int m=0,n=0,o=0;
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader("C:\\Users\\lalit\\IdeaProjects\\CRUD\\src\\info.csv"));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                String sline = null;
                while(true){
                    try {
                        if (!((sline= reader.readLine())!=null)) break;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    int k=CarDAO.createCar(new Car(sline));
                    if(k==0)
                        m++;
                    else if(k==-1)
                        o++;
                    else
                        n++;
                }
                JOptionPane.showMessageDialog(null, m+"Records created\n"+n+"records updated\n");
                try {
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}