

        package com.example.SunPanel;

        import android.content.Context;
        import android.widget.Toast;

        public class Check {
            public boolean ISNull(String nameClient, String SecondNameClient, String Patronymic, String PhoneClient, String PlaceClient, String MailClient, String nameService, String staff, String addressService, String phoneService, String typeGarant, String timeGarant, String s, Context context){
                boolean trus = true;
                if (nameClient.equals("")){
                    Toast.makeText(context,"Пусте поле!!\nІм'я клієнта",Toast.LENGTH_LONG).show();
                    trus = false;}
                else {
                    if (SecondNameClient.equals("")) {
                        Toast.makeText(context, "Пусте поле!!\nПрізвище клієнта", Toast.LENGTH_LONG).show();
                        trus = false;
                    } else {
                        if (Patronymic.equals("")) {
                            Toast.makeText(context, "Пусте поле!!\nПо-Батькові клієнта", Toast.LENGTH_LONG).show();
                            trus = false;
                        } else {
                            if (PhoneClient.equals("")) {
                                Toast.makeText(context, "Пусте поле!!\nТелефон клієнта", Toast.LENGTH_LONG).show();
                                trus = false;
                            } else {
                                if (PlaceClient.equals("")) {
                                    Toast.makeText(context, "Пусте поле!!\nПочаткова температура ", Toast.LENGTH_LONG).show();
                                    trus = false;
                                } else {
                                    if (MailClient.equals("")) {
                                        Toast.makeText(context, "Пусте поле!!\nКінцева температура ", Toast.LENGTH_LONG).show();
                                        trus = false;
                                    } else {
                                        if (nameService.equals("")) {
                                            Toast.makeText(context, "Пусте поле!!\nВольтаж", Toast.LENGTH_LONG).show();
                                            trus = false;
                                        } else {
                                            if (staff.equals("")) {
                                                Toast.makeText(context, "Пусте поле!!\nЧас зарядки акумулятора", Toast.LENGTH_LONG).show();
                                                trus = false;
                                            } else {
                                                if (addressService.equals("")) {
                                                    Toast.makeText(context, "Пустое поле!!\nОсвітленість", Toast.LENGTH_LONG).show();
                                                    trus = false;
                                                } else {
                                                    if (phoneService.equals("")) {
                                                        Toast.makeText(context, "Пусте поле!!\nВольтаж", Toast.LENGTH_LONG).show();
                                                        trus = false;
                                                    } else {
                                                        if (typeGarant.equals("")) {
                                                            Toast.makeText(context, "Пусте поле!!\nЧас зарядки акумулятора", Toast.LENGTH_LONG).show();
                                                            trus = false;
                                                        } else {
                                                            if (timeGarant.equals("")) {
                                                                Toast.makeText(context, "Пустое поле!!\nОсвітленість", Toast.LENGTH_LONG).show();
                                                                trus = false;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }}
                return trus;
            }


        }