package cntt.k2020.sql_qlthuvien;

public class xuattl {
    String matl;
    String tentl;

    public xuattl(String matl, String tentl) {
        this.matl = matl;
        this.tentl = tentl;
    }

    public String toString() {
        String kq = "";
        kq += "Mã thể loại: " + this.matl + "\n";
        kq += "Tên thể loại: " + this.tentl + "\n";
        return kq;
    }
}
