
public class Car {

    private int vid;
    private String vname;
    private int price;
    private String company;
    private String status;

    public Car(int vid,String vname, int price, String company,String status) {
        this.vid=vid;
        this.vname = vname;
        this.price = price;
        this.company = company;
        this.status = status;
    }


    public Car(String str){
        String[] values = str.split(",");
        this.vid=Integer.valueOf(values[0]);
        this.vname=values[1];
        this.price= Integer.valueOf(values[2]);
        this.company= values[3];
        this.status= values[4];
    }





    public int getvid() {
        return vid;
    }


    public void setvid(int vid) {
        this.vid = vid;
    }


    public String getvname() {
        return vname;
    }

    public void setvname(String vname) {
        this.vname = vname;
    }

    public int getprice() {
        return price;
    }

    public void setprice(int price) {
        this.price = price;
    }

    public String getcompany() {
        return company;
    }

    public void setcompany(String company) {
        this.company = company;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status= status;
    }


    @Override
    public String toString(){
        return " Car[ Car id: "+this.vid+ ", Car name: " + this.vname + ", Car Price: " + this.price + ", Car company: " + this.company + "car status: "+ this.status +"]";
    }
}
