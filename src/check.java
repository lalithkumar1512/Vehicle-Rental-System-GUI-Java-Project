public class check {
    String[] st=new String[100];
    public static int j=0;
    public check(String[] str,int c){
        st=str;
        j=c;
    }
    public String chek(String s) {
        int p = s.length(), k = 0;
        for (int i = 0; i < j; i++) {
            for (int in = 0; in <= st[i].length() - p; in++) {
                if (st[i].substring(in, in + p ).equals(s)) {
                    k = i;
                    return st[k];
                }
            }
        }
        return null;
    }
    public static void main(String args[]){
        String[] s=new String[3];
        s[0]="wap";
        s[1]="paes";
        s[2]="hanah";
        check x=new check(s,3);
        x.chek("wa");
    }
}
