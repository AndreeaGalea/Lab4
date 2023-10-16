package Lab4;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

enum Situatie{
    achizitionat,
    expus,
    vandut
}
enum Mod_tiparire{
    alb,
    negru
}
enum Format_copiere{
    A3,
    A4
}
enum Sisteme_de_operare{
    Windows,
    Linux
}
class Echipament{
    private String denumire;
    private String nr_inv;// număr de inventar
    private float pret;
    private String zona_mag;
    private Situatie situatie; //     achiziţionat/expus/vândut
    public Echipament(String denumire, String nr_inv, float pret, String zona_mag, Situatie situatie){
        this.denumire=denumire;
        this.nr_inv=nr_inv;
        this.pret=pret;
        this.zona_mag=zona_mag;
        this.situatie=situatie;
    }
    @Override
    public String toString(){
        return "Denumire"+denumire+", Numar de inventar:"+nr_inv+", Pret:"+pret+", Zona din magazin:"+zona_mag+ ", Situatie:"+situatie.toString();
    }
}


class Imprimante extends Echipament{
    private String ppm; //numărul de pagini scrise pe minut
    private int rezolutie; //număr de puncte per inch dpi
    private int p_car; //număr de pagini/cartuş
    private Mod_tiparire mod_tiparire; //color sau alb negru

    Imprimante(String denumire, String nr_inv, float pret, String zona_mag, Situatie situatie, String ppm, int rezolutie, int p_car, Mod_tiparire mod_tiparire){
        super(denumire, nr_inv, pret, zona_mag, situatie);
        this.ppm=ppm;
        this.rezolutie=rezolutie;
        this.p_car=p_car;
        this.mod_tiparire=mod_tiparire;
    }


}

class Copiatoare extends Echipament{

    private int p_ton; //numărul de pagini/toner
    private Format_copiere format_copiere; //A3 sau A4

    Copiatoare(String denumire, String nr_inv, float pret, String zona_mag, Situatie situatie, int p_ton, Format_copiere format_copiere){
        super(denumire, nr_inv, pret, zona_mag, situatie);
        this.p_ton=p_ton;
        this.format_copiere=format_copiere;
    }
    @Override
    public String toString() {
        return super.toString()+", Numarul de pagini/toner"+ p_ton+ ",Format copiere:" + format_copiere.toString();
    }
}

class Sisteme_de_calcul extends Echipament{

    private String tip_mon;
    private int vit_proc;   //viteza procesorului
    private int c_hdd;  //apacitate a HDD
    private Sisteme_de_operare so; //sistem de operare Windows sau Linux

    Sisteme_de_calcul(String denumire, String nr_inv, float pret, String zona_mag, Situatie situatie, String tip_mon, int vit_proc, int c_hdd, Sisteme_de_operare so){
        super(denumire, nr_inv, pret, zona_mag, situatie);
        this.tip_mon=tip_mon;
        this.vit_proc=vit_proc;
        this.c_hdd=c_hdd;
        this.so=so;
    }
    @Override
    public String toString() {
        return super.toString()+", Tip monitor:"+ tip_mon+ ", Viteza procesorului: " + vit_proc+", Capacitatea Hdd:"+c_hdd+ ", Sistem de operare:"+so.toString();
    }

}
public class Tema {
    public static void main(String[] args) throws IOException {
        List <Echipament> lista_echipamente= new ArrayList<>();
        String fFileName= "echipamente.txt";
        Scanner fileReader= new Scanner(new File(fFileName));
        while (fileReader.hasNext()) {
            String linie = fileReader.nextLine();
            String[] splited = linie.split(";");
            switch (splited[5].toLowerCase()){
                case "imprimanta":
                    lista_echipamente.add(new Imprimante(splited[0], splited[1], Float.parseFloat(splited[2]),splited[3],Situatie.valueOf(splited[4].toLowerCase()), splited[6], Integer.parseInt(splited[7]), Integer.parseInt(splited[8]),Mod_tiparire.valueOf(splited[9].toLowerCase()) ));
                    break;

                case "copiator":
                    lista_echipamente.add(new Copiatoare(splited[0], splited[1], Float.parseFloat(splited[2]),splited[3],Situatie.valueOf(splited[4].toLowerCase()),Integer.parseInt(splited[6]), Format_copiere.valueOf(splited[7].toLowerCase())));
                    break;

                case "sistem de calcul":
                    lista_echipamente.add(new Sisteme_de_calcul(splited[0], splited[1], Float.parseFloat(splited[2]),splited[3],Situatie.valueOf(splited[4].toLowerCase()),splited[6], Integer.parseInt(splited[7]), Integer.parseInt(splited[8]), Sisteme_de_operare.valueOf(splited[9])));
                    break;
            }


        }

        System.out.println("Alegeti optiunea dorita:");
        System.out.println("1 - Afişarea tuturor echipamentelor");
        System.out.println("2 - Afişarea imprimantelor");
        System.out.println("3 - Afişarea copiatoarelor");
        System.out.println("4 - Afişarea sistemelor de calcul");
        System.out.println("5 - Modificarea stării în care se află un echipament");
        System.out.println("6 - Setarea unui anumit mod de scriere pentru o imprimantă");
        System.out.println("7 - Setarea unui format de copiere pentru copiatoare");
        System.out.println("8 - Instalarea unui anumit sistem de operare pe un sistem de calcul");
        System.out.println("9 - Afişarea echipamentelor vândute");
        System.out.println("10 -serializarea colecției de obiecte în fișierul echip.bin ");
        System.out.println("11 -deserializarea colecției de obiecte în fișierul echip.bin ");
        Scanner scanner=new Scanner(System.in);
        int optiune = Integer.parseInt(scanner.nextLine());
        switch (optiune) {
            case 1:
                for(Echipament ec:lista_echipamente){
                    System.out.println(ec);
                }
                break;
            case 2:
                for(Echipament ec:lista_echipamente){
                    if (ec instanceof Imprimante)
                        System.out.println(ec);
                }

                break;
            case 3:

                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;


        }



    }

}
