public class conta{
    private double saldo;
    private int id;
    public conta(){
      saldo =0;
      id++;
    }
    public conta(int id, double saldo){
        this.saldo = saldo;
        this.id = id;
    }
    public double getSaldo(){
        return saldo;
    }
    public void setSaldo(double novosaldo){
        this.saldo=novosaldo;
    }
    public int getId(){
        return id;
    }
    @Override
    public String toString() {
        return "[Conta: " + this.id + " / Saldo: " + this.saldo + "]";
    }
}
