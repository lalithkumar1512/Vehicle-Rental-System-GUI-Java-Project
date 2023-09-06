import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class CarDAO {

    //  Create/Upload from CSV file
    public static int createCar(Car c){
        Connection con = DriverConnection.getConnection();

        List<Car> list= getCarByID(c.getvid());
       /* final String query1= "select 1 where vname=? and price=? and company=? and status=? and vid=?";
        try(PreparedStatement st= con.prepareStatement(query1)) {
            st.setString(1, c.getvname());
            //st.setString(2, c.getvname());
            st.setInt(2, c.getprice());
            st.setString(3, c.getcompany());
            st.setString(4, c.getstatus());
            st.setInt(5, c.getvid());
            ResultSet rs= st.executeQuery(query1);
            System.out.println(rs);
            if (rs.next() == true)
                return -1;

        }
        catch(SQLException e){
            e.printStackTrace();
        }*/
        if(list.isEmpty()){
            final String query="insert into vehicles(vid,vname,price,company,status) values(?,?,?,?,?)";

            try(PreparedStatement st= con.prepareStatement(query)){
                st.setInt(1, c.getvid());
                st.setString(2, c.getvname());
                st.setInt(3, c.getprice());
                st.setString(4, c.getcompany());
                st.setString(5,c.getstatus());
                int rowsAffected = st.executeUpdate();
                return 0;
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            final String query= "update vehicles set vname=?, price=?, company=?,status=? where vid=?";
            try(PreparedStatement st= con.prepareStatement(query)){
                st.setString(1,c.getvname());
                st.setInt(2,c.getprice());
                st.setString(3,c.getcompany());
                st.setString(4,c.getstatus());
                st.setInt(5,c.getvid());

                int rowsAffected = st.executeUpdate();
                return 1;
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }

      return 0;
    }
    public static List<Car> getCarByID(int id){
        List<Car>  c = new ArrayList<Car>();
        Connection con = DriverConnection.getConnection();
        final String query = "select * from vehicles where vid= ?";


        try(PreparedStatement st = con.prepareStatement(query)){
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Car tempcar = new Car(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5));
                c.add(tempcar);
            }

        }

        catch(SQLException e){
            e.printStackTrace();
        }

        return c;
    }
}
