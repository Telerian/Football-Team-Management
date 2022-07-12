public class Takim {
    private String Isim;
    private int Yas;
    private String Mevki;
    private double Form;
    private int Mac;
    private int Gol;
    private int Asist;
    private int Maas;
    private int Sozlesme;
    private int PiyasaDegeri;

    public Takim(String Isim,int Yas,String Mevki,double Form,int Mac,int Gol,int Asist,int Maas,int Sozlesme,int PiyasaDegeri) {
        this.Isim = Isim;
        this.Yas = Yas;
        this.Mevki = Mevki;
        this.Form = Form;
        this.Mac = Mac;
        this.Gol = Gol;
        this.Asist = Asist;
        this.Maas = Maas;
        this.Sozlesme = Sozlesme;
        this.PiyasaDegeri = PiyasaDegeri;
    }
    public String getIsim() {
        return this.Isim;
    }

    public void setIsim(String Isim) {
        this.Isim = Isim;
    }

    public int getYas() {
        return this.Yas;
    }

    public void setYas(int Yas) {
        this.Yas = Yas;
    }

    public String getMevki() {
        return this.Mevki;
    }

    public void setMevki(String Mevki) {
        this.Mevki = Mevki;
    }

    public double getForm() {
        return this.Form;
    }

    public void setForm(double Form) {this.Form = Form;}

    public int getMac() {
        return this.Mac;
    }

    public void setMac(int Mac) {
        this.Mac = Mac;
    }

    public int getGol() {return this.Gol;}

    public void setGol(int Gol) {
        this.Gol = Gol;
    }

    public int getAsist() {
        return this.Asist;
    }

    public void setAsist(int Asist) {
        this.Asist = Asist;
    }

    public int getMaas() {
        return this.Maas;
    }

    public void setMaas(int Maas) {
        this.Maas = Maas;
    }

    public int getSozlesme() {
        return this.Sozlesme;
    }

    public void setSozlesme(int Sozlesme) {
        this.Sozlesme = Sozlesme;
    }

    public int getPiyasaDegeri() {
        return this.PiyasaDegeri;
    }

    public void setPiyasaDegeri(int PiyasaDegeri) {
        this.PiyasaDegeri = PiyasaDegeri;
    }




}
