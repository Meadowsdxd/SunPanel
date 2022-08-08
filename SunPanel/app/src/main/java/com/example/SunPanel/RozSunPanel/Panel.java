package com.example.SunPanel.RozSunPanel;

public interface Panel {
double EVsr(int evn, int n); //середня освітленість
double Etsr(double Evsr, int k); //щільність випромінювання, К
double Kz(double wmax, double Ikz, double Uxx);//коефіцієнт заповнення
double Plosha(double a, double b);//площадь солнечной батареи
double Wi(double Plosha, double Etsr);//потужність падаючого випромінювання на сонячну батарею, S
double KPD(double wmax, double Wi);//КПД
double Xdolya(double Unum);// доля от мощности при питании от начального напряжения
double Ws(double Wi, double Xdolya); //мощность падающего излучения на батарею солнечных элементов при произвольном напряжении питания ламп
double KPDWithoutInvertor(double Wzarsr, double Tzar);//КПД всей системы
double KPDregular(double Wo);//КПД регулятора заряда
double KPDObsh(double KPDUstanov, double KPD); //общий КПД всей установки, включая солнечную батарею, регулятор заряда, аккумулятор и инвертор
double KPDUstanov(double KPDWithoutInvertor, double KPDregular, double Akkum);
double KPDAkkum(double Wzarsr, double Tza);

}
