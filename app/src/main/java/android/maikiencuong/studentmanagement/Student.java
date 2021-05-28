package android.maikiencuong.studentmanagement;

import java.io.Serializable;

public class Student implements Serializable {

    private int mssv;
    private String name;
    private String clazz;
    private double diem;

    public Student() {
    }

    public Student(int mssv, String name, String clazz, double diem) {
        this.mssv = mssv;
        this.name = name;
        this.clazz = clazz;
        this.diem = diem;
    }

    public Student(String name, String clazz, double diem) {
        this.name = name;
        this.clazz = clazz;
        this.diem = diem;
    }

    public int getMssv() {
        return mssv;
    }

    public void setMssv(int mssv) {
        this.mssv = mssv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mssv=" + mssv +
                ", name='" + name + '\'' +
                ", clazz='" + clazz + '\'' +
                ", diem=" + diem +
                '}';
    }
}
