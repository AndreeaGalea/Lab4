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

    // Getter
    public String getDenumire() {
        return denumire;
    }

    public Situatie getSituatie(){
        return situatie;
    }
    // Setter
    public void setSituatie(Situatie NewSituatie) {
        this.situatie = NewSituatie;
    }
    @Override
    public String toString(){
        return "Denumire:"+denumire+", Numar de inventar:"+nr_inv+", Pret:"+pret+", Zona din magazin:"+zona_mag+ ", Situatie:"+situatie.toString();
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
    public void setModTiparire(Mod_tiparire newModTiparire) {
        this.mod_tiparire= newModTiparire;
    }
    @Override
    public String toString() {
        return super.toString()+", Pagini/minut:"+ppm + ",Rezolutie:" + rezolutie+" ,Pagini/cartus:"+p_car+", Mod tiparire:"+mod_tiparire.toString();
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
    public void setFormat_copiere(Format_copiere NewFormatCopiere) {
        this.format_copiere= NewFormatCopiere;
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
    public void setSistemOperare(Sisteme_de_operare NewSO) {
        this.so = NewSO;
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
        int setat=0;
        String cautareDenumire;
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
            case 1: //Afişarea tuturor echipamentelor
                for(Echipament ec:lista_echipamente){
                    System.out.println(ec);
                }
                break;
            case 2://Afişarea imprimantelor
                for(Echipament ec:lista_echipamente){
                    if (ec instanceof Imprimante)
                        System.out.println(ec);
                }

                break;
            case 3://Afişarea copiatoarelor
                for(Echipament ec:lista_echipamente){
                    if (ec instanceof Copiatoare)
                        System.out.println(ec);
                }
                break;
            case 4://Afişarea sistemelor de calcul
                for(Echipament ec:lista_echipamente){
                    if (ec instanceof Sisteme_de_calcul)
                        System.out.println(ec);
                }
                break;
            case 5://Modificarea stării în care se află un echipament
                setat=0;
                System.out.println("Dati numele echipamentului:");
                cautareDenumire = scanner.nextLine();
                System.out.println("Dati noua stare a echipamentului:");
                String cautareStareS =scanner.nextLine();
                Situatie situatieNoua =  Situatie.valueOf(cautareStareS);
                for(Echipament ec:lista_echipamente){
                    if (ec.getDenumire().equals(cautareDenumire))
                    {
                        ec.setSituatie(situatieNoua);
                        setat=1;
                    }

                }
                if(setat==0)
                    System.out.println("Nu s-a gasit un ehipament cu ascest nume!");
                break;
            case 6://Setarea unui anumit mod de scriere pentru o imprimantă
                setat=0;
                System.out.println("Dati numele imprimantei:");
                cautareDenumire = scanner.nextLine();
                System.out.println("Dati noul mod de tiparire  a echimprimantei:");
                String cautareTiparireS=scanner.nextLine();
                Mod_tiparire tiparireNoua =  Mod_tiparire.valueOf(cautareTiparireS);
                for(Echipament ec:lista_echipamente){
                    if (ec instanceof Imprimante)
                        if (ec.getDenumire().equals(cautareDenumire))
                        {
                            ((Imprimante) ec).setModTiparire(tiparireNoua);
                            setat=1;
                        }


                }
                if(setat==0)
                    System.out.println("Nu s-a gasit o imprimanta cu ascest nume!");


                break;
            case 7: //Setarea unui format de copiere pentru copiatoare
                setat=0;
                System.out.println("Dati numele copiatorului:");
                cautareDenumire = scanner.nextLine();
                System.out.println("Dati noul format de copiere:");
                String cautareFormatCopiereS=scanner.nextLine();
                Format_copiere formatCopiereNoua = Format_copiere.valueOf(cautareFormatCopiereS);
                for(Echipament ec:lista_echipamente){
                    if (ec instanceof Copiatoare)
                        if (ec.getDenumire().equals(cautareDenumire))
                        {
                            ((Copiatoare) ec).setFormat_copiere(formatCopiereNoua);
                            setat=1;
                        }


                }
                if(setat==0)
                    System.out.println("Nu s-a gasit un copiator cu ascest nume!");
                break;
            case 8: //Instalarea unui anumit sistem de operare pe un sistem de calcul

                setat=0;
                System.out.println("Dati numele sistemului de calcul:");
                cautareDenumire = scanner.nextLine();
                System.out.println("Dati noul sistemului de operare:");
                String cautareSistemOperare=scanner.nextLine();
                Sisteme_de_operare SistemOperareNou = Sisteme_de_operare.valueOf(cautareSistemOperare);
                for(Echipament ec:lista_echipamente){
                    if (ec instanceof Sisteme_de_calcul)
                        if (ec.getDenumire().equals(cautareDenumire))
                        {
                            ((Sisteme_de_calcul) ec).setSistemOperare(SistemOperareNou);
                            setat=1;
                        }

                }
                if(setat==0)
                    System.out.println("Nu s-a gasit un copiator cu ascest nume!");
                break;
            case 9://Afişarea echipamentelor vândute


                break;
            case 10:
                break;
            case 11:
                break;


        }



    }

}
