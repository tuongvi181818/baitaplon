package cntt.k2020.sql_qlthuvien;

public class sach {
    //Danh sach thuoc tinh
    String maS;
    String tenS;

    String matl;
    //String matg;

    //Ham khoi tao
    public sach(String maS, String tenS, String matl ) {
        this.maS = maS;
        this.tenS = tenS;
        this.matl = matl;

    }

    //Thu tuc hien thi du lieu
    public String toString() {
        String msg = "Mã sách:" + this.maS + " | ";
        msg += "Tên sách :" + this.tenS + "\n";
        msg += "Mã thể loại:" + this.matl + "\n";

        //msg += "Mã tac gia" + this.matg;
        return msg;
    }
}
