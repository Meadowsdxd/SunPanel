package com.example.SunPanel.RozSunPanel;

public class KPDPanel implements Panel {
    @Override
    public double EVsr(int evn, int n) {
        return (evn*n)/n;
    }

    @Override
    public double Etsr(double Evsr, int k) {
        return Evsr/k;
    }

    @Override
    public double Kz(double wmax,double Uxx ,double Ikz) {
        return (wmax/(Uxx*Ikz))/10;
    }

    @Override
    public double Plosha(double a, double b) {
        return (a)*(b);
    }

    @Override
    public double Wi(double Plosha, double Etsr) {
        return Plosha*Etsr;
    }

    @Override
    public double KPD(double wmax, double Wi) {
        return (wmax/Wi)*100;
    }

    @Override
    public double Xdolya(double Unum) {
        return Unum/220;
    }

    @Override
    public double Ws(double Wi, double Xdolya) {
        return (Wi*Xdolya);
    }

    @Override
    public double KPDWithoutInvertor( double Wzarsr, double Tzar) {
        return (((11*0.166)/(Wzarsr*(Tzar/60)))*100)+20;
    }


    @Override
    public double KPDregular(double Wo) {
        return ((Wo-0.0517)/Wo)*100;
    }



    @Override
    public double KPDUstanov(double KPDWithoutInvertor,double KPDregular,double Akkum) {
        return( KPDregular/100)*(Akkum/100)*(KPDWithoutInvertor/100)*100;
    }

    @Override
    public double KPDAkkum( double Wzarsr, double Tzar) {
        return (((10*0.175)/(Wzarsr*(Tzar/60)))*100);
    }

    @Override
    public double KPDObsh(double KPDUstanov , double KPD) {
        return ((KPDUstanov/100)*(KPD/100))*100 ;
    }

}
